package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Oferta> searchByDescription(Pageable pageable, String descripcion) {
		Page<Oferta> ofertas = new PageImpl<Oferta>(new LinkedList<Oferta>());
		ofertas = ofertasRepository.searchByDescription(pageable, descripcion);
		
		return ofertas;
	}

	public List<Oferta> getOfertasFromUser(long id) {
		return ofertasRepository.searchById(id);
	}
}
