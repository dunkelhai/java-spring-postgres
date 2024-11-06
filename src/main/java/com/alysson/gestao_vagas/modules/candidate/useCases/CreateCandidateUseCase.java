package com.alysson.gestao_vagas.modules.candidate.useCases;

import com.alysson.gestao_vagas.exceptions.UserFoundException;
import com.alysson.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.alysson.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        candidateRepository
                .findByEmailOrUsername(candidateEntity.getEmail(), candidateEntity.getUsername())
                .ifPresent(candidate -> {
                    throw new UserFoundException("Candidate already exists");
                });

        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

        return candidateRepository.save(candidateEntity);
    }
}
