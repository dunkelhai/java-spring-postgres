package com.alysson.gestao_vagas.modules.company.useCases;

import com.alysson.gestao_vagas.modules.company.entities.JobEntity;
import com.alysson.gestao_vagas.modules.company.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {
    @Autowired
    JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
        return jobRepository.save(jobEntity);
    }
}
