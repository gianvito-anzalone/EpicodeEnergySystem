package it.epicode.beservice.security.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class LoginResponse {

	private String token;    
    // Imposta il prefisso che indica il tipo di Token
    private final String type = "Bearer";
    // Dati dell'utente
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private Date expirationTime;
}
