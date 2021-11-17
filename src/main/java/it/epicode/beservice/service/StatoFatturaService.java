package it.epicode.beservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.repository.StatoFatturaRepository;

@Service
public class StatoFatturaService {

	@Autowired
	StatoFatturaRepository statoFatturaRepo; 

	public void saveStatoFattura(StatoFattura stato) {
		this.statoFatturaRepo.save(stato);
	}

	public void updateStato(StatoFattura stato) {
		StatoFattura stat=this.statoFatturaRepo.getById(stato.getId());
		stat.setNome(stato.getNome());
		stat.setId(stato.getId());
		this.statoFatturaRepo.flush();
	}

}
