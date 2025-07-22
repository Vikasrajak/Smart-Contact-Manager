package com.SCM.Smart_Contact_Manger.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SCM.Smart_Contact_Manger.entites.User;
import com.SCM.Smart_Contact_Manger.helper.Helper;
import com.SCM.Smart_Contact_Manger.services.UserService;

@Controller
@RequestMapping("/user") //set base path all url start with user
public class UserController {
	
	    @Autowired
	    private UserService userService;
	
	 private Logger logger = LoggerFactory.getLogger(UserController.class);
	 // user dashboard
	
	 @GetMapping("/dashboard") 
	 public String userDashboard() {
		 
		 return "user/dashboard";
	 }
	 
	 // user profile
	 
	 
	 @GetMapping("/profile")
	 public String userProfile(Model model, Authentication authentication) {
		 
         
		 String username = Helper.getEmailOfLoggedUser(authentication);
		 
	 User  user	 = userService.getUserByEmail(username);
		  
	   System.out.println(user.getName());
	   System.out.println(user.getEmail());	
	   
	   model.addAttribute("loggedInUser", user);
		     
		  return "user/profile";
	 }

	  @GetMapping("/testjs")
	 public String testjs() {
		 
		 return "user/NewFile";
	 }
}
