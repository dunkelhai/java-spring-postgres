package com.alysson.gestao_vagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "job")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;
    @Length(min = 3, max = 255, message = "Nome deve ter entre 3 e 255 caracteres")
    private String benefits;
    private String level;

    @ManyToOne
    @JoinColumn(name = "companyId", insertable = false, updatable = false)
    private CompanyEntity company;

    @NotNull
    @Column(name = "companyId")
    private UUID companyId;

    @CreationTimestamp
    LocalDateTime createdAt;
}
