package it.epicode.beservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.epicode.beservice.repository.UserRepository;
import it.epicode.beservice.security.UserDetailsImpl;
import it.epicode.beservice.security.model.User;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=this.userRepo.findByUsername(username);  
		if(user.isPresent()) {
			return UserDetailsImpl.build(user.get());
		}else
			throw new  UsernameNotFoundException("Username not found"+username);
	
	}


}
