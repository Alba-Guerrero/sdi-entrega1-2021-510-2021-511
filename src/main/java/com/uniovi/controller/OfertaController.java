package com.uniovi.controller;

import java.security.Principal;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import com.uniovi.entities.*;
import com.uniovi.services.OfertaService;

import com.uniovi.services.UsersService;
import com.uniovi.validators.OfertaValidator;

@Controller
public class OfertaController {
	private static final Logger logger = LogManager.getLogger(OfertaController.class);

	@Autowired
	private OfertaService ofertasService ;
	@Autowired
	private HttpSession httpsession;
	@Autowired
	private UsersService usersService;

	@Autowired
	private OfertaValidator ofertaValidator;

	@RequestMapping("/oferta/list")
	public String getList(Model model, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		
		model.addAttribute("ofertasList", ofertasService.getOfertasFromUser(user.getId()));
		return "oferta/list";
	}

	@RequestMapping("/oferta/find")
	public String findList(Model model, Pageable pageable,Principal principal,  @RequestParam(value = "", required = false) String searchText) {
		searchText = "%" + searchText + "%";
		
		Page<Oferta> ofertas = new PageImpl<Oferta>(new LinkedList<Oferta>());
		if(searchText != null || !searchText.isEmpty())
			ofertas =ofertasService.searchByDescription(pageable, searchText,httpsession.getAttribute("email").toString());
		
		model.addAttribute("ofertasList", ofertas.getContent());
		model.addAttribute("page", ofertas);
		httpsession.setAttribute("saldo",usersService.getUserByEmail(principal.getName()).getDinero());
		
		return "oferta/find";
	}
	
	@RequestMapping("/oferta/buyList")
	public String getListComprados(Model model, Principal principal) {

		model.addAttribute("ofertasBuy", ofertasService.getOfertasComprador(principal.getName()));
		httpsession.setAttribute("saldo",usersService.getUserByEmail(principal.getName()).getDinero());
		return "oferta/buyList";
	}

	@RequestMapping(value = "/oferta/add", method = RequestMethod.POST)
	public String addUser(@Validated Oferta oferta, BindingResult result, Principal principal) {
		ofertaValidator.validate(oferta, result);
		
		if (result.hasErrors()) {
			return "oferta/add";
		}
		
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		oferta.setUser(user);
		logger.info(String.format("El usuario con email %s ha añadido correctamente una oferta",email));
		ofertasService.addOferta(oferta);
		return "redirect:/oferta/list";

	}
	
	@RequestMapping("/oferta/buyList/{id}")
	public String comprarOferta(Model modelo,@PathVariable Long id,Principal principal,@RequestParam(value = "", required = false) String error) {
		
		if(ofertasService.checkSaldo(principal.getName(), ofertasService.getOferta(id).getPrecio())){//Si tiene saldo
			ofertasService.updateSaldo(ofertasService.getOferta(id).getPrecio(), principal.getName());
			ofertasService.setComprado(principal.getName(),id);
			modelo.addAttribute("ofertasBuy", ofertasService.getOfertasComprador(principal.getName()));
			httpsession.setAttribute("saldo",usersService.getUserByEmail(principal.getName()).getDinero());
			
			logger.info(String.format("El usuario con el email %s ha comprado la oferta", principal.getName()));
			
			return "redirect:/oferta/buyList";
		}
		else
			modelo.addAttribute("error", error);
		logger.info(String.format("El usuario con el email %s no tiene saldo para comprar la oferta", principal.getName()));
			
		return "redirect:/oferta/find";

	}

	@RequestMapping(value = "/oferta/add", method = RequestMethod.GET)
	public String addOferta(Model model, Principal principal) {
		model.addAttribute("oferta", new Oferta());
		return "oferta/add";
	}

	@RequestMapping(value = "/oferta/add")
	public String getOferta(Model modelo) {
		modelo.addAttribute("usersList", usersService.getUsers());
		return "oferta/add";
	}

	@RequestMapping("/oferta/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("oferta", ofertasService.getOferta(id));
		return "oferta/details";
	}

	@RequestMapping("/oferta/delete/{id}")
	public String delete(@PathVariable Long id) {
		ofertasService.deleteOferta(id);
		logger.info(String.format("Se ha borrado la oferta con id %d",id ));
		return "redirect:/oferta/list";
	}

	@RequestMapping(value = "/oferta/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {

		model.addAttribute("usersList", usersService.getUsers());
		model.addAttribute("oferta", ofertasService.getOferta(id));
		return "oferta/edit";
	}


	@RequestMapping(value = "/oferta/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Oferta oferta) {
		//	oferta.setId(id);
		Oferta original = ofertasService.getOferta(id);
		original.setDescripcion(oferta.getDescripcion());
		ofertasService.addOferta(original);
		return "redirect:/oferta/details/" + id;
	}

}