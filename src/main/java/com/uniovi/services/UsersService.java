package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repository.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;
	
/*	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	*/
	
	@PostConstruct
	public void init() {}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setPassword(user.getPassword());
		usersRepository.save(user);
	}
	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
		}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}
}