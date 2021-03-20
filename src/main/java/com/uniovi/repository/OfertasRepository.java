package com.uniovi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Oferta;

public interface OfertasRepository extends CrudRepository<Oferta, Long>{

	@Query("SELECT o FROM Oferta o WHERE o.user.id = ?1")
	List<Oferta> searchById(long id);
	
	@Query("SELECT o FROM Oferta o WHERE (LOWER (o.descripcion)) LIKE LOWER(?1)")
	Page<Oferta> searchByDescription(Pageable pageable, String descripcion);
}

