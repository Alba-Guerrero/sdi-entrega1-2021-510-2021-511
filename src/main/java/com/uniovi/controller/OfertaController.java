package com.uniovi.controller;

import java.security.Principal;
import java.util.LinkedList;

import javax.swing.JOptionPane;

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
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.OfertaValidator;
import com.uniovi.validators.UserValidator;

@Controller
public class OfertaController {

	@Autowired
	private OfertaService ofertasService ;

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
	public String findList(Model model, Pageable pageable,  @RequestParam(value = "", required = false) String searchText) {
		searchText = "%" + searchText + "%";
		
		Page<Oferta> ofertas = new PageImpl<Oferta>(new LinkedList<Oferta>());
		if(searchText != null || !searchText.isEmpty())
			ofertas =ofertasService.searchByDescription(pageable, searchText);
		
		model.addAttribute("ofertasList", ofertas.getContent());
		model.addAttribute("page", ofertas);
		
		return "oferta/find";
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
		
		ofertasService.addOferta(oferta);
		return "redirect:/oferta/list";

	}
	
	@RequestMapping("/oferta/buyList/{id}")
	public String comprarOferta(Model modelo,@PathVariable Long id,Principal principal) {
		
		if(ofertasService.checkSaldo(principal.getName(), ofertasService.getOferta(id).getPrecio())){//Si tiene saldo
			ofertasService.updateSaldo(ofertasService.getOferta(id).getPrecio(), principal.getName());
			ofertasService.setComprado(id);
			modelo.addAttribute("ofertasList", ofertasService.getOferta(id));
			
			return "oferta/buyList";
		}
		
			
		return "oferta/list";

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