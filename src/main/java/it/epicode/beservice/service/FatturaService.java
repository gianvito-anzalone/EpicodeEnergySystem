package it.epicode.beservice.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.repository.FatturaRepository;

@Service
public class FatturaService {
	
	@Autowired
	FatturaRepository fatturaRepo;
	
	public void saveFattura(Fattura fat) {
		this.fatturaRepo.save(fat);
	}
	
	public void deleteFattura(Long id) {
		this.fatturaRepo.deleteById(id);
	}
	
	public void updateFattura(Fattura fat) {
		Fattura fattura=this.fatturaRepo.findByIdFattura(fat.getId());
		fattura.setAnno(fat.getAnno());
		fattura.setCliente(fat.getCliente());
		fattura.setData(fat.getData());
		fattura.setId(fat.getId());
		fattura.setImporto(fat.getImporto());
		fattura.setStato(fat.getStato());
		fattura.setNumero(fat.getNumero());
		this.fatturaRepo.flush();
	}
	
	public Page<Optional<Fattura>>findByCliente(Pageable page,Cliente cliente){
		return this.fatturaRepo.findByCliente(page, cliente);
	}

	public Page<Optional<Fattura>>findByStato(Pageable page,StatoFattura stato){
		return this.fatturaRepo.findByStato(page, stato);
	}

	public Page<Optional<Fattura>>findByData(Pageable page,LocalDate data){
		return this.fatturaRepo.findByData(page, data);
	}

	public Page<Optional<Fattura>>findByAnno(Pageable page,String anno){
		return this.fatturaRepo.findByAnno(page, anno);
	}

	public Page<Optional<Fattura>>findbyImporto(Pageable page,Double min,Double max){
		return this.fatturaRepo.findByImporto(page, min, max);
	}
	
	


}
