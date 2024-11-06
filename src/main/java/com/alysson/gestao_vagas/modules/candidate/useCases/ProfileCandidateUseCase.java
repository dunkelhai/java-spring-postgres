package com.alysson.gestao_vagas.modules.candidate.useCases;

import com.alysson.gestao_vagas.modules.candidate.DTO.ProfileCandidateResponseDTO;
import com.alysson.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {
    @Autowired
    CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID uuid) {
        var candidate = candidateRepository.findById(uuid).orElseThrow(() -> {
                    throw new UsernameNotFoundException("Not found!");
                }
        );
        return ProfileCandidateResponseDTO.builder()
                .id(candidate.getId().toString())
                .email(candidate.getEmail())
                .description(candidate.getDescription())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .build();
    }

}
