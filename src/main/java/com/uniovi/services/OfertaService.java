package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Oferta;
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

	public Oferta getOferta(Long id) {
		return ofertasRepository.findById(id).get();
	}

	public void addOferta(Oferta oferta) {
		
		ofertasRepository.save(oferta);
	}
	
	

	public void deleteOferta(Long id) {
		ofertasRepository.deleteById(id);
	}

}
