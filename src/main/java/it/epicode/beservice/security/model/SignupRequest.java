package it.epicode.beservice.security.model;

import java.util.Set;

import javax.persistence.Convert;
import javax.validation.constraints.Email;

import it.epicode.beservice.security.StringAttributeConverter;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequest {
    private String username;
    @Email
    @Convert(converter = StringAttributeConverter.class)
    private String email;
    private Set<String> role;
    private String password;
    private String nome;
    private String cognome;
}   