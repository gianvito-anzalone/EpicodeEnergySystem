package it.epicode.beservice.controller.html;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.beservice.repository.RoleRepository;
import it.epicode.beservice.repository.UserRepository;
import it.epicode.beservice.security.JwtUtils;
import it.epicode.beservice.security.UserDetailsImpl;
import it.epicode.beservice.security.model.Erole;
import it.epicode.beservice.security.model.LoginRequest;
import it.epicode.beservice.security.model.Role;
import it.epicode.beservice.security.model.SignupRequest;
import it.epicode.beservice.security.model.User;

@Controller
@RequestMapping("/apihtml")
public class HtmlController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserRepository userRepository;
	
	
	@PostMapping(value="/loginhtml",consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView authenticateUserhtml(LoginRequest loginRequest) {
		// Usa l'AuthenticationManager per autenticare i parametri della request
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		// Ottiene i privilegi dell'utente
		authentication.getAuthorities();

		// Ottiene il SecurityContext
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// Genera il token
		String jwt = jwtUtils.generateJwtToken(authentication);

		// getPrincipal(), ottiene i dati dell'utente
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		ModelAndView view=new ModelAndView("menu.html");
		view.addObject("jwt",jwt);
		view.addObject("roles",roles);
		return view;

	}

	@PostMapping(value="/signuphtml",consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView registerUserhtml( SignupRequest signUpRequest) {
		// Verifica l'esistenza di Username e Email gi?? registrate
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ModelAndView("badrequest.html");
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ModelAndView("badrequest.html");
		}
		// Crea un nuovo user codificando la password
		User user = new User(null, signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getNome(), signUpRequest.getCognome());
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

// Verifica l'esistenza dei Role
		if (strRoles == null) {
			Role userRole = roleRepository.findByErole(Erole.USER)
					.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role.toLowerCase()) {
				case "admin":
					Role adminRole = roleRepository.findByErole(Erole.ADMIN)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByErole(Erole.USER)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		return new ModelAndView("menu.html");
	}


	


}
