package com.uniovi.services;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private UsersService usersService;
	
	@PostConstruct
	public void init() {
		User user1 = new User("Pedro", "Díaz", "pedro@diaz.com", "123456", "123456");
		user1.setRole(rolesService.getRoles()[0]);
		
		User user2 = new User("Admin", "Admin", "admin@email.com", "admin", "admin");
		user2.setRole(rolesService.getRoles()[1]);

		usersService.addUser(user1);
		usersService.addUser(user2);
	}
}