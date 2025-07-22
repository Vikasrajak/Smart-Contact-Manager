package com.SCM.Smart_Contact_Manger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.SCM.Smart_Contact_Manger.entites.Providers;
import com.SCM.Smart_Contact_Manger.entites.User;
import com.SCM.Smart_Contact_Manger.forms.UserForm;
import com.SCM.Smart_Contact_Manger.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

	@Autowired
	private UserService userService;

	@GetMapping("/home")
	public String homepage() {

		return "home";
	}

	@GetMapping("/service")
	public String servicepage() {
		return "service";
	}

	@GetMapping("/contact")
	public String contactpage() {

		return "contact";
	}

	@GetMapping("/about")
	public String aboutpage() {
		return "about";
	}

	@GetMapping("/login")
	public String loginpage() {
		return "login";
	}

	@GetMapping("/signup")
	public String registerpage(Model model) {

		UserForm userForm = new UserForm();

		model.addAttribute("userForm", userForm);
		return "signup";
	}

	@PostMapping("/do-register")
	public String processRegister(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult rBindingResult) {

		if (rBindingResult.hasErrors()) {
			return "signup";
		}

		User user = new User();

		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setPassword(userForm.getPassword());
		user.setAbout(userForm.getAbout());
		user.setPhoneNumber(userForm.getPhoneNumber());

		/*
		 * session.setAttribute("message", "Registration Successfull!");
		 */
		user.setRoleList(List.of("ROLE_USER"));

		userService.saveUser(user);

		return "redirect:/login";
	}

	@GetMapping("showdata")
	public String Showdata(Model model) {

		model.addAttribute("users", userService.getAllUsers());
		return "ShowData";
	}
	
	
	  @GetMapping("/testadmin")
	  public String testAdminpage() {
		  
		  return "adminpage";
	  }
}
