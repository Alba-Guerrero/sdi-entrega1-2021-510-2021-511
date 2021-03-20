package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;
import com.uniovi.repository.OfertasRepository;

@Service
public class OfertaService {
	
	@Autowired
	private OfertasRepository ofertasRepository;

	@PostConstruct
	public void init() {}

	public List<Oferta> getOfertas() {
		List<Oferta> ofertas = new ArrayList<Oferta>();
		ofertasRepository.findAll().forEach(ofertas::add);
		return ofertas;
	}
	
	public List<Oferta> getOfertasComprador(String emailComprador) {
		List<Oferta> ofertas = new ArrayList<Oferta>();
		ofertas=ofertasRepository.searchByEmailOfertasCompradas(emailComprador);
		return ofertas;
	}

	public Oferta getOferta(Long id) {
		return ofertasRepository.findById(id).get();
	}

	public void addOferta(Oferta oferta) {
		ofertasRepository.save(oferta);
	}
	
	public boolean checkSaldo(String email,Double saldo) {
		return ofertasRepository.checkSaldo(email, saldo)!=null;
	}

	public void deleteOferta(Long id) {
		ofertasRepository.deleteById(id);
	}
	

	public Page<Oferta> searchByDescription(Pageable pageable, String descripcion) {
		Page<Oferta> ofertas = new PageImpl<Oferta>(new LinkedList<Oferta>());
		ofertas = ofertasRepository.searchByDescription(pageable, descripcion);
		
		return ofertas;
	}

	public boolean updateSaldo(Double saldo, String email) {
		/*
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email1= auth.getName();
		User user = ofertasRepository.findUserByEmail(email);
		if(email1.equals(user.getEmail())){*/
			ofertasRepository.updateSaldo(saldo, email);
		return true;
		//}
		//return false;
	}
	


	public List<Oferta> getOfertasFromUser(long id) {
		return ofertasRepository.searchById(id);
	}
	
	public void setComprado(String emailComprador,long id) {
	 ofertasRepository.setComprado(emailComprador,id);
	}
}
