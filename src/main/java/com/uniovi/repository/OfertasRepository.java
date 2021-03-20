package com.uniovi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Oferta;

public interface OfertasRepository extends CrudRepository<Oferta, Long>{

	@Query("SELECT o FROM Oferta o WHERE o.user.email = ?1 ORDER BY o.id ASC ")
	List<Oferta> searchByEmail(String email);
	
	@Query("SELECT o FROM Oferta o WHERE (LOWER (o.descripcion)) LIKE LOWER(?1)")
	List<Oferta> searchByDescription(String descripcion);
}
