package com.alysson.gestao_vagas.modules.company.useCases;

import com.alysson.gestao_vagas.modules.company.DTO.AuthCompanyDTO;
import com.alysson.gestao_vagas.modules.company.repository.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("Company not found")
        );

        var passwordMatches = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new UsernameNotFoundException("Invalid username/password");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withExpiresAt(Instant.now().plus(Duration.ofHours(1)))
                .withIssuer("GestaoVagas")
                .withSubject(company.getId().toString())
                .sign(algorithm);
    }
}