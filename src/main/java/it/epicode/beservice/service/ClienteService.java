package it.epicode.beservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepo;

	
	
	public void saveCliente(Cliente cli) {
		this.clienteRepo.save(cli);
	}

	public void deleteCliente(Long id) {
		this.clienteRepo.deleteById(id);
	}

	public void updateCliente(Cliente cli) {
	Cliente c=this.clienteRepo.findByIdCliente(cli.getId());
	c.setCognomeContatto(cli.getCognomeContatto());
	c.setDataInserimento(cli.getDataInserimento());
	c.setDataUltimoContatto(cli.getDataUltimoContatto());
	c.setEmail(cli.getEmail());
	c.setEmailContatto(cli.getEmailContatto());
	c.setFatturatoAnnuale(cli.getFatturatoAnnuale());
	c.setFatture(cli.getFatture());
	c.setIndirizzoSedeLegale(cli.getIndirizzoSedeLegale());
	c.setIndirizzoSedeOperativa(cli.getIndirizzoSedeLegale());
	c.setNomeContatto(cli.getNomeContatto());
	c.setPartitaIva(cli.getPartitaIva());
	c.setPec(cli.getPec());
	c.setRagioneSociale(cli.getRagioneSociale());
	c.setTelefono(cli.getTelefono());
	c.setTelefonoContatto(cli.getTelefonoContatto());
	c.setTipoCliente(cli.getTipoCliente());
	c.setId(cli.getId());
	this.clienteRepo.flush();
	}

	
	public Page<Optional<Cliente>>findByOrderByRagioneSociale(Pageable page){
		return this.clienteRepo.findByOrderByRagioneSociale(page);
	}

	public Page<Optional<Cliente>>findByOrderByDataInserimento(Pageable page){
		return this.clienteRepo.findByOrderByDataInserimento(page);
	}

	public Page<Optional<Cliente>>findByOrderByFatturatoAnnuale(Pageable page){
		return this.clienteRepo.findByOrderByFatturatoAnnuale(page);
	}

	public Page<Optional<Cliente>>findByOrderByDataUltimoContatto(Pageable page){
		return this.clienteRepo.findByOrderByDataUltimoContatto(page);
	}

	public Page<Optional<Cliente>>findByOrderByIndirizzoSedeLegale(Pageable page){
		return this.clienteRepo.findByOrderByIndirizzoSedeLegaleComuneProvincia(page);
	}

	public Page<Optional<Cliente>>findByfatturatoAnnuale(Pageable page,Double fatturato){
		return this.clienteRepo.findByfatturatoAnnuale(page, fatturato);
	}

	public Page<Optional<Cliente>>findBydataInserimento(Pageable page,LocalDate data){
		return this.clienteRepo.findBydataInserimento(page, data);
	}

	public Page<Optional<Cliente>>findByRagioneSociale(Pageable page,String nome){
		return this.clienteRepo.findByRagioneSociale(page, nome);
	}

	public List<Cliente> findAll() {
		return this.clienteRepo.findAll();
	}
	public Cliente findById(Long id) {
		return this.clienteRepo.findByIdCliente(id);
		
	}
}
