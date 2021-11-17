package it.epicode.beservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.Comune;
import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.model.Provincia;
import it.epicode.beservice.service.ComuneService;
import it.epicode.beservice.service.IndirizzoService;
import it.epicode.beservice.service.ProvinciaService;

@RestController
@RequestMapping("/apilocazione")
public class LocazioneController {

	@Autowired
	ComuneService comuneService;

	@Autowired
	IndirizzoService indirizzoService;

	@Autowired
	ProvinciaService provinciaService;

	@PostMapping("/savecomune")
	public String addComune(@RequestBody Comune comune) {
		comuneService.saveComune(comune);
		return "comune salvato";
	}

	@PostMapping("/updatecomune")
	public String updateComune(@RequestBody Comune comune) {
		comuneService.updateComune(comune);
		return "Comune aggiornato";
	}

	@GetMapping("/deletecomune")
	public String deleteComune(@RequestParam Long id) {
		comuneService.deleteComune(id);
		return "comune cancellato";
	}

	@PostMapping("/addindirizzo")
	public String addIndirizzo(@RequestBody Indirizzo indirizzo) {
		indirizzoService.saveIndirizzo(indirizzo);
		return "indirizzo salvato";
	}

	@PostMapping("/updateindirizzo")
	public String updateIndirizzo(@RequestBody Indirizzo indirizzo) {
		indirizzoService.updateIndirizzo(indirizzo);
		return "indirizzo aggiornato";
	}

	@GetMapping("/deleteindirizzo")
	public String deleteIndirizzo(@RequestParam Long id) {
		indirizzoService.deleteIndirizzo(id);
		return "indirizzo cancellato";
	}

	@PostMapping("/addprovincia")
	public String addProvincia(@RequestBody Provincia provincia) {
		provinciaService.saveProvincia(provincia);
		return "provincia salvata";
	}

	@PostMapping("/updateprovincia")
	public String updateProvincia(@RequestBody Provincia provincia) {
		provinciaService.updateProvincia(provincia);
		return "provincia aggiornata";
	}

	@GetMapping("/deleteprovincia")
	public String deleteProvincia(@RequestParam Long id) {
		provinciaService.deleteProvincia(id);
		return "provincia cancellata";

	}
}