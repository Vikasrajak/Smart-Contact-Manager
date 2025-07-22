package com.SCM.Smart_Contact_Manger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.SCM.Smart_Contact_Manger.entites.User;
import com.SCM.Smart_Contact_Manger.helper.Helper;
import com.SCM.Smart_Contact_Manger.services.UserService;

@ControllerAdvice
public class RootController {
	
	  @Autowired
	  private UserService userService;
	
	 @ModelAttribute
	 public void addLoggedInUserInformation(Model model, Authentication authentication) {
		  
	    if(authentication==null) {
	    	
	    	return;
	    }
         
		 String username = Helper.getEmailOfLoggedUser(authentication);
		 
	 User  user	 = userService.getUserByEmail(username);
		  
       System.out.println(user);
	   System.out.println(user.getName());
	   System.out.println(user.getEmail());	
	   
	   
	   
	   model.addAttribute("loggedInUser", user);
		     
	 }

}
