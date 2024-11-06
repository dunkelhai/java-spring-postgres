package com.alysson.gestao_vagas.modules.candidate.useCases;

import com.alysson.gestao_vagas.modules.candidate.DTO.AuthCandidateRequestDTO;
import com.alysson.gestao_vagas.modules.candidate.DTO.AuthCandidateResponseDTO;
import com.alysson.gestao_vagas.modules.candidate.repository.CandidateRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username()).orElseThrow(() -> {
                    throw new UsernameNotFoundException("Usuário ou senha incorretos.");
                });
        var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());
        if (!passwordMatches){
            throw new UsernameNotFoundException("Usuário ou senha incorretos");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(30L));
        var token = JWT.create()
                .withIssuer("gestao_vagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("candidate"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return AuthCandidateResponseDTO.builder()
                .accessToken(token)
                .expiresIn(expiresIn.toEpochMilli())
                .build();
    }
}
