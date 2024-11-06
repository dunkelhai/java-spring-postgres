package com.alysson.gestao_vagas.modules.candidate.controller;

import com.alysson.gestao_vagas.exceptions.UserFoundException;
import com.alysson.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.alysson.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.alysson.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;
    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;


    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok(result);
        } catch (UserFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> get(HttpServletRequest httpServletRequest) {
        var candidateId = httpServletRequest.getAttribute("candidateId");
        try {
            var profile = profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
            return ResponseEntity.ok(profile);
        } catch (UserFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
