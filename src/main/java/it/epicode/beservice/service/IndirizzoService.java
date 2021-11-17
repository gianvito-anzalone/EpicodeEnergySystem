package it.epicode.beservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.repository.IndirizzoRepository;
@Service
public class IndirizzoService {
	@Autowired
	IndirizzoRepository indirizzoRepo;

	public void saveIndirizzo(Indirizzo indi) {
		this.indirizzoRepo.save(indi);
	}
	
	public void deleteIndirizzo(Long id) {
		this.indirizzoRepo.deleteById(id);
	}

	public void updateIndirizzo(Indirizzo indirizzo) {
		Indirizzo i = this.indirizzoRepo.findByIdIndirizzo(indirizzo.getId());
		i.setCap(indirizzo.getCap());
		i.setCivico(indirizzo.getCivico());
		i.setComune(indirizzo.getComune());
		i.setLocalita(indirizzo.getLocalita());
		i.setVia(indirizzo.getVia());
		this.indirizzoRepo.flush();
	}

	public Indirizzo findByid(Long indirizzo) {
		return this.indirizzoRepo.findByIdIndirizzo(indirizzo);
	}

}
