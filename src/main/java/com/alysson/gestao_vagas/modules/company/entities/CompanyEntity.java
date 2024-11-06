package com.alysson.gestao_vagas.modules.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
@Entity(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(min = 3, max = 255, message = "Nome deve ter entre 3 e 255 caracteres")
    private String name;

    @Pattern(regexp = "^[a-z0-9_-]{3,15}$", message = "Username deve conter apenas letras minúsculas, números, hífens e underscores")
    private String username;

    @Email(message = "Email deve ser válido")
    private String email;

    @Length(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String password;

    @Length(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
    private String description;

    private String website;
}
