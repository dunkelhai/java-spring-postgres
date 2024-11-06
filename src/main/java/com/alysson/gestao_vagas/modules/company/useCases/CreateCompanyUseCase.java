package com.alysson.gestao_vagas.modules.company.useCases;

import com.alysson.gestao_vagas.exceptions.UserFoundException;
import com.alysson.gestao_vagas.modules.company.repository.CompanyRepository;
import com.alysson.gestao_vagas.modules.company.entities.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        companyRepository
                .findByEmailOrUsername(companyEntity.getEmail(), companyEntity.getUsername())
                .ifPresent(company -> {
                    throw new UserFoundException("Company already exists");
                });

        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return companyRepository.save(companyEntity);
    }
}
