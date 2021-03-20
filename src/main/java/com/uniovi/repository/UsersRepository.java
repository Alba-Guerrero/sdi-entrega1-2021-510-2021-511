package com.uniovi.repository;

import org.springframework.data.repository.CrudRepository;


import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
	
	
}
