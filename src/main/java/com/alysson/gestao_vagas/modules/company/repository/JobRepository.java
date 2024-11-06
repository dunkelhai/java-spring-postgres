package com.alysson.gestao_vagas.modules.company.repository;

import com.alysson.gestao_vagas.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    Optional<JobEntity> findByCompanyId(UUID companyId);
}
