package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

@Component
public class UserValidator implements Validator {
	
	@Autowired
	private UsersService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		User userS=userService.getUserByEmail(user.getEmail());

		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "score", "Error");
		if (user.getEmail().length() < 2 || user.getEmail().length() > 25) {
			errors.rejectValue("email", "Error.email.length");
		}
		if (!user.getEmail().contains("@")) {
			errors.rejectValue("email", "Error.email.arroba");
		}
		if (userService.getUserByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Error.email.coincidence");
		}

		if (user.getNombre().length() < 2 || user.getNombre().length() > 25) {
			errors.rejectValue("nombre", "Error.name.length");
		}
		
		if (user.getApellido().length() < 2 || user.getApellido().length() > 50) {
			errors.rejectValue("apellido", "Error.lastName.length");
		}
		
		if (user.getPassword().length() < 2 || user.getPassword().length() > 50) {
			errors.rejectValue("password", "Error.password.length");
		}
		
		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Error.passwordConfirm.coincidence");
		}
		
		//if (userS.getEmail()=="admin@email.com"&& userS.getPassword()=="admin") {
		//	errors.rejectValue("email", "Error.email.admin");
	//	}

	}

}