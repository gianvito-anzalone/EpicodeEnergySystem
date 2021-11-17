package it.epicode.beservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Comune;
import it.epicode.beservice.repository.ComuneRepository;

@Service
public class ComuneService {
	
	@Autowired
	ComuneRepository comuneRepo;

	public void saveComune(Comune c) {
		this.comuneRepo.save(c);
	}

	public void deleteComune(Long id) {
		this.comuneRepo.deleteById(id);
	}

	public void updateComune(Comune com) {
		Comune c=this.comuneRepo.findByIdComune(com.getId());
		c.setId(com.getId());
		c.setNome(com.getNome());
		c.setProvincia(com.getProvincia());
		this.comuneRepo.flush();
	}

}
