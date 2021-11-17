package it.epicode.beservice.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.epicode.beservice.security.model.User;
import lombok.Data;
@Data
public class UserDetailsImpl implements UserDetails{

	
	private static final long serialVersionUID = -3929533990763506024L;
	private Long id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    
   
    private boolean accountNonLocked = true;    
    private boolean accountNonExpired = false;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    private Date expirationTime;
    /* GrantedAuthority, rappresenta un'autorizzazione concessa a un oggetto di autenticazione (il token) */
    private Collection<? extends GrantedAuthority> authorities;
	
    public UserDetailsImpl(Long id, String username, String email, String password, boolean enabled,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.authorities = authorities;
		this.accountNonLocked = enabled;
        this.accountNonExpired = enabled;
        this.credentialsNonExpired = enabled;
    }
    
    public static UserDetailsImpl build(User user) {
		
    	List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getErole().name())).collect(Collectors.toList());
		
		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
				user.getActive(), authorities);
    }
	
	
	
}
