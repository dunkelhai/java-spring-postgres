package com.alysson.gestao_vagas.modules.company.controller;

import com.alysson.gestao_vagas.modules.company.DTO.CreateJobDTO;
import com.alysson.gestao_vagas.modules.company.entities.JobEntity;
import com.alysson.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody CreateJobDTO jobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");
        var jobEntity = JobEntity.builder()
                .companyId(UUID.fromString(companyId.toString()))
                .description(jobDTO.getDescription())
                .benefits(jobDTO.getBenefits())
                .level(jobDTO.getLevel())
                .build();
        return this.createJobUseCase.execute(jobEntity);
    }
}