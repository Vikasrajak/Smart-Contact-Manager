package com.SCM.Smart_Contact_Manger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SCM.Smart_Contact_Manger.entites.Contact;
import com.SCM.Smart_Contact_Manger.services.ContactService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	  @Autowired
	  private ContactService contactService;
	
	
	 @GetMapping("/contact/{contactId}")
	public Contact getContact(@PathVariable String contactId) {
		
		return contactService.getById(contactId);
	}
	  
	 
}
