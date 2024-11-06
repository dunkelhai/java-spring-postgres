package com.alysson.gestao_vagas.modules.company.repository;

import com.alysson.gestao_vagas.modules.company.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByEmailOrUsername(String email, String username);
    Optional<CompanyEntity> findByUsername(String username);
}
