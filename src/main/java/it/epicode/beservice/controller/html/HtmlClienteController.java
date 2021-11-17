package it.epicode.beservice.controller.html;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.service.ClienteService;
import it.epicode.beservice.service.IndirizzoService;

@Controller
@RequestMapping("/apiclientehtml")
public class HtmlClienteController {
	@Autowired
	ClienteService clienteService;

	@Autowired
	IndirizzoService indirizzoService;

	@PostMapping("/savecliente")
	public String saveCliente(@ModelAttribute("cliente") Cliente cliente) {
		this.clienteService.saveCliente(cliente);
		return "cliente salvato";
	}

	@PostMapping("/updatecliente")
	public String updateCliente(Cliente cli) {
		this.clienteService.updateCliente(cli);
		return "cliente aggiornato";
	}

	@GetMapping("/deletecliente/{id}")
	public ModelAndView deleteCliente(@PathVariable long id) {
		this.clienteService.deleteCliente(id);
		return findAll();
	}

	@GetMapping("/ragionesocialeordinato/{pageNo}")
	public ModelAndView findByOrderByRagioneSociale(Pageable page,
			@RequestParam(required = false, defaultValue = "0") int pageNo) {
		Page<Optional<Cliente>> find = this.clienteService.findByOrderByRagioneSociale(page);
		List<Optional<Cliente>> list = find.getContent();
		if (find.hasContent()) {
			ModelAndView view = new ModelAndView("listordinata.html");
			view.addObject("list", list);
			view.addObject("currentPage", pageNo);
			view.addObject("totalPages", find.getTotalPages());
			return view;
		} else {
			ModelAndView badrequest = new ModelAndView("badrequest.html");
			return badrequest;
		}
	}

	@GetMapping("/findall")
	public ModelAndView findAll() {
		List<Cliente> find = this.clienteService.findAll();
		if (!(find.isEmpty())) {
			ModelAndView view = new ModelAndView("list.html");
			view.addObject("find", find);
			return view;
		} else {
			ModelAndView badrequest = new ModelAndView("badrequest.html");
			return badrequest;
		}
	}

	@GetMapping("showindirizzo/{indirizzo}")
	public ModelAndView showIndirizzo(@PathVariable String indirizzo, ModelAndView model) {
		Long idconverter=Long.parseLong(indirizzo);
		Indirizzo i=this.indirizzoService.findByid(idconverter);
		model.setViewName("showindirizzo");
		model.addObject("indirizzo", i);
		return model;
	}
	
	@PostMapping(value="/updateindirizzo",consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView updateindirizzo(Indirizzo indirizzo) {
		Indirizzo i=new Indirizzo();
		i.setCap(indirizzo.getCap());
		i.setCivico(indirizzo.getCivico());
		i.setId(indirizzo.getId());
		i.setLocalita(indirizzo.getLocalita());
		this.indirizzoService.updateIndirizzo(i);
		return findAll();
	}

 	@GetMapping("/ordinefatturatoannuale/{pageNo}")
 	public ModelAndView findByOrderByFatturatoAnnuale(Pageable page,
 			@RequestParam(required = false, defaultValue = "0") int pageNo){
 		Page<Optional<Cliente>> find=this.clienteService.findByOrderByFatturatoAnnuale(page);
 	List<Optional<Cliente>> list = find.getContent();
 		if (find.hasContent()) {
			ModelAndView view = new ModelAndView("listordinata.html");
			view.addObject("list", list);
			view.addObject("currentPage", pageNo);
			view.addObject("totalPages", find.getTotalPages());
			return view;
		} else {
			ModelAndView badrequest = new ModelAndView("badrequest.html");
			return badrequest;
		}
 	}

 	@GetMapping("/dataultimocontatto/{pageNo}")
 	public ModelAndView findByOrderByDataUltimoContatto(Pageable page,
 			@RequestParam(required = false, defaultValue = "0") int pageNo){
 		Page<Optional<Cliente>> find=this.clienteService.findByOrderByDataUltimoContatto(page);
 		List<Optional<Cliente>> list = find.getContent();
 		if (find.hasContent()) {
			ModelAndView view = new ModelAndView("listordinata.html");
			view.addObject("list", list);
			view.addObject("currentPage", pageNo);
			view.addObject("totalPages", find.getTotalPages());
			return view;
		} else {
			ModelAndView badrequest = new ModelAndView("badrequest.html");
			return badrequest;
		}
 	}

//	@GetMapping("/ordineprovincia")
// 	public ModelAndView findByOrderByIndirizzoSedeLegale(Pageable page){
// 		Page<Optional<Cliente>> find=this.clienteService.findByOrderByIndirizzoSedeLegale(page);
// 		if(find.hasContent()) {
// 			ModelAndView view=new ModelAndView("list.html");
// 			view.addObject("find",find);
// 			return  view;
// 		}else {
// 			ModelAndView badrequest=new ModelAndView("badrequest.html");
// 			return  badrequest;}
//
// 	}
//
//	@GetMapping("/fatturatoannuale")
// 	public ResponseEntity<?> findByfatturatoAnnuale(Pageable page,@RequestParam String fatturato){
// 		Double fatt=Double.parseDouble(fatturato);
//		Page<Optional<Cliente>> find=this.clienteService.findByfatturatoAnnuale(page, fatt);
// 		if(find.hasContent()) {
// 			return new ResponseEntity<>(find,HttpStatus.OK);
// 		}else
// 			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
// 	}
//	
//	@GetMapping("/searchdatainserimento")
// 	public ResponseEntity<?>findBydataInserimento(Pageable page,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate data){
// 		Page<Optional<Cliente>> find=this.clienteService.findBydataInserimento(page,data);
// 		if(find.hasContent()) {
// 			return new ResponseEntity<>(find,HttpStatus.OK);
// 		}else
// 			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
// 	}
//
//	@GetMapping("/ragionesociale")
// 	public ModelAndView findByRagioneSociale(Pageable page,@RequestParam String nome){
// 		Page<Optional<Cliente>> find=this.clienteService.findByRagioneSociale(page, nome);
// 		if(find.hasContent()) {
// 			ModelAndView view=new ModelAndView("list.html");
// 			view.addObject("find",find);
// 			return  view;
// 		}else {
// 			ModelAndView badrequest=new ModelAndView("badrequest.html");
// 			return  badrequest;}
//
// 	}

}
