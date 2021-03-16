package com.uniovi.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.uniovi.entities.*;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.UserValidator;

@Controller
public class UsersController {

	@Autowired
	private RolesService rolesService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	//REGISTRO USUARIOS
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		userValidator.validate(user, result);

		if(result.hasErrors())
			return "signup";

		user.setRole(rolesService.getRoles()[0]);

		usersService.addUser(user);

		//Al registrarse inicia sesion automaticamente con el email y la contraseña
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

	@RequestMapping("/user/list")
	public String getList(Model model, @RequestParam(value = "", required = false) String searchText) {
		model.addAttribute("usersList", usersService.getUsers());
		return "user/list";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String addUser(@Validated User user, BindingResult result) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {

			return "user/add";
		}
		usersService.addUser(user);
		return "redirect:/user/list";

	}

	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		return "user/add";
	}

	@RequestMapping(value = "/user/add")
	public String getUser() {
		return "user/add";
	}

	@RequestMapping("/user/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("user", usersService.getUser(id));
		return "user/details";
	}

	@RequestMapping("/user/delete/{id}")
	public String delete(@PathVariable Long id) {
		usersService.deleteUser(id);
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/user/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		User user = usersService.getUser(id);
		model.addAttribute("user", user);
		return "user/edit";
	}

	@RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute User user) {
		//	user.setId(id);
		User original = usersService.getUser(id);
		original.setNombre(user.getNombre());
		original.setApellido(user.getApellido());
		usersService.addUser(original);
		return "redirect:/user/details/" + id;
	}

}