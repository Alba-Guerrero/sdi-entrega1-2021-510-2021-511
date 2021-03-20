package com.uniovi.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;

public interface OfertasRepository extends CrudRepository<Oferta, Long>{

	@Query("SELECT o FROM Oferta o WHERE o.user.id = ?1")
	List<Oferta> searchById(long id);
	
	@Query("SELECT o FROM Oferta o WHERE (LOWER (o.descripcion)) LIKE LOWER(?1)")
	Page<Oferta> searchByDescription(Pageable pageable, String descripcion);

	
	@Modifying
	@Transactional
	@Query("UPDATE  User  SET  dinero = dinero - ?1 where  email= ?2")
	void updateSaldo(Double saldo,String email);
	
	@Modifying
	@Transactional
	@Query("UPDATE  Oferta  SET  comprada=true, emailComprador= ?1 where id=?2")
	void setComprado(String emailComprador,long id);
	
	//@Query("Select u form oferta where ")
	//User findUserByEmail(String email);
	
	@Query("Select u  from User u  where u.email=?1 and u.dinero>=?2")
	User checkSaldo(String email,Double saldo);
	
	
	@Query("SELECT o FROM Oferta o WHERE o.emailComprador = ?1")
	List<Oferta> searchByEmailOfertasCompradas(String email);
}
