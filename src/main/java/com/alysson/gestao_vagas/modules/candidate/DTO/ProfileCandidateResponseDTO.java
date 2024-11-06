package com.alysson.gestao_vagas.modules.candidate.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDTO {
    private String id;
    private String username;
    private String name;
    private String email;
    private String description;
}
