package it.epicode.beservice.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.service.ClienteService;


@RestController
@RequestMapping("/apicliente")
public class ClienteController {
	@Autowired
	ClienteService clienteService;


 @PostMapping("/savecliente")
 public String saveCliente(@RequestBody Cliente cli) {
	 this.clienteService.saveCliente(cli);
	 return "cliente salvato";
 }

 	@PostMapping("/updatecliente")
 	public String updateCliente(@RequestBody Cliente cli) {
	this.clienteService.updateCliente(cli);
	return "cliente aggiornato";
}

 	@GetMapping("/deletecliente")
 	public String  deleteCliente(@RequestParam Long id) {
 		this.clienteService.deleteCliente(id);
 		return "cliente cancellato";
 	}

 	@GetMapping("/ragionesocialeordinato")
 	public ResponseEntity<?> findByOrderByRagioneSociale(Pageable page){
 		Page<Optional<Cliente>> find=this.clienteService.findByOrderByRagioneSociale(page);
 		if(find.hasContent()) {
 			return new ResponseEntity<>(find,HttpStatus.OK);
 		}else
 			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
 	}

 	@GetMapping("/datainserimento")
 	public ResponseEntity<?> findByOrderByDataInserimento(Pageable page,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data){
 		Page<Optional<Cliente>> find=this.clienteService.findBydataInserimento(page, data);
 		if(find.hasContent()) {
 			return new ResponseEntity<>(find,HttpStatus.OK);
 		}else
 			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
 	}

 	@GetMapping("/ordinefatturatoannuale")
 	public ResponseEntity<?> findByOrderByFatturatoAnnuale(Pageable page){
 		Page<Optional<Cliente>> find=this.clienteService.findByOrderByFatturatoAnnuale(page);
 		if(find.hasContent()) {
 			return new ResponseEntity<>(find,HttpStatus.OK);
 		}else
 			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
 	}

 	@GetMapping("/dataultimocontatto")
 	public ResponseEntity<?> findByOrderByDataUltimoContatto(Pageable page){
 		Page<Optional<Cliente>> find=this.clienteService.findByOrderByDataUltimoContatto(page);
 		if(find.hasContent()) {
 			return new ResponseEntity<>(find,HttpStatus.OK);
 		}else
 			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
 	}

	@GetMapping("/ordineprovincia")
 	public ResponseEntity<?> findByOrderByIndirizzoSedeLegale(Pageable page){
 		Page<Optional<Cliente>> find=this.clienteService.findByOrderByIndirizzoSedeLegale(page);
 		if(find.hasContent()) {
 			return new ResponseEntity<>(find,HttpStatus.OK);
 		}else
 			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
 	}

	@GetMapping("/fatturatoannuale")
 	public ResponseEntity<?> findByfatturatoAnnuale(Pageable page,@RequestParam String fatturato){
 		Double fatt=Double.parseDouble(fatturato);
		Page<Optional<Cliente>> find=this.clienteService.findByfatturatoAnnuale(page, fatt);
 		if(find.hasContent()) {
 			return new ResponseEntity<>(find,HttpStatus.OK);
 		}else
 			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
 	}
	
	@GetMapping("/searchdatainserimento")
 	public ResponseEntity<?>findBydataInserimento(Pageable page,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate data){
 		Page<Optional<Cliente>> find=this.clienteService.findBydataInserimento(page,data);
 		if(find.hasContent()) {
 			return new ResponseEntity<>(find,HttpStatus.OK);
 		}else
 			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
 	}

	@GetMapping("/ragionesociale")
 	public ResponseEntity<?>findByRagioneSociale(Pageable page,@RequestParam String nome){
 		Page<Optional<Cliente>> find=this.clienteService.findByRagioneSociale(page, nome);
 		if(find.hasContent()) {
 			return new ResponseEntity<>(find,HttpStatus.OK);
 		}else
 			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
 	}

}
