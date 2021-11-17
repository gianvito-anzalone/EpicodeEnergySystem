package it.epicode.beservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Provincia;
import it.epicode.beservice.repository.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepo;

	public void saveProvincia(Provincia prov) {
		this.provinciaRepo.save(prov);
	}


	public void deleteProvincia(Long id) {
		this.provinciaRepo.deleteById(id);
	}

	public void updateProvincia(Provincia prov) {
	Provincia provincia=this.provinciaRepo.findByidProvincia(prov.getId());
	provincia.setId(prov.getId());
	provincia.setNome(prov.getNome());
	provincia.setSigla(prov.getSigla());
	this.provinciaRepo.flush();
	}

	public Provincia findByNome(String nome) {
		return this.provinciaRepo.findByNome(nome);
	}

}
