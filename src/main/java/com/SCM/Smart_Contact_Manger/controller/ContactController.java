package com.SCM.Smart_Contact_Manger.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SCM.Smart_Contact_Manger.entites.Contact;
import com.SCM.Smart_Contact_Manger.entites.User;
import com.SCM.Smart_Contact_Manger.forms.ContactForm;
import com.SCM.Smart_Contact_Manger.helper.AppConstant;
import com.SCM.Smart_Contact_Manger.helper.Helper;
import com.SCM.Smart_Contact_Manger.services.ContactService;
import com.SCM.Smart_Contact_Manger.services.ImageService;
import com.SCM.Smart_Contact_Manger.services.UserService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/user/contact")
public class ContactController {

	  @Autowired
	  private ContactService contactService;
	  
	  @Autowired
	  private UserService userService;
	  
	  
	  @Autowired
	  private ImageService imageService;
	  
	  
	  Logger logger = LoggerFactory.getLogger(ContactController.class);
	
	   @GetMapping("/add")
	   public String addContact(Model model) {
		    
		    ContactForm contactForm = new ContactForm();
		    
		   model.addAttribute("contactForm", contactForm);
		   return "user/add-contact";
	   }
	   
	   
		/*
		 * @PostMapping("/addcontact") public String
		 * addContactProcess(@Valid @ModelAttribute("contactForm") ContactForm
		 * contactForm,BindingResult rBindingResult,Authentication authentication) {
		 * 
		 * if(rBindingResult.hasErrors()) { return "user/add-contact"; }
		 * 
		 * String username = Helper.getEmailOfLoggedUser(authentication);
		 * 
		 * User user = userService.getUserByEmail(username);
		 * 
		 * 
		 * return "redirect:/user/contact/add";
		 * 
		 * }
		 */
	   
	    
	    @PostMapping("/addcontact")
	    public String contactFormProcess(@Valid @ModelAttribute("contactForm") ContactForm contactForm,BindingResult rBindingResult,Authentication authenticatin) {
	    	
   
	    	 if(rBindingResult.hasErrors()) {
	    		 
	    		 return "user/add-contact";
	    	 }
	    	 
	    	// convert form cotnact to contactForm
	    	
	    	    String username = Helper.getEmailOfLoggedUser(authenticatin);
	    	    
	   		  User user = userService.getUserByEmail(username);
 
	   		  
				
				/*
				 * String fileUrl= imageService.uploadImage(contactForm.getContactImage());
				 */				
				     	
	    	    Contact contact = new Contact();
	    	    
	    	    contact.setName(contactForm.getName());
	    	    contact.setFavorite(contactForm.isFavorite());
	    	    contact.setEmail(contactForm.getEmail());
	    	    contact.setPhoneNumber(contactForm.getPhoneNumber());
	    	    contact.setAddress(contactForm.getAddress());
	    	    contact.setDescription(contactForm.getDescription());
	    	    contact.setUser(user);
	    	    
	    	    contact.setLinkedInLink(contactForm.getLinkedInLink());
	    	    contact.setWebsiteLink(contactForm.getWebsiteLink());
	    	    
	    	    
	    	    String fileUrl = imageService.uploadImage(contactForm.getContactImage());
	    	    System.out.println("Uploaded fileUrl = " + fileUrl); // Check this is NOT null or empty

	    	    contact.setPicture(fileUrl);
	    	    System.out.println("Contact picture before save: " + contact.getPicture());

	    	    contactService.saveContact(contact);

	    
				/*
				 * contact.setPicture(fileUrl); contactService.saveContact(contact);
				 */		   	
	    	return "redirect:/user/contact/add";
	    }
	    
	    
	    @GetMapping
	    public String viewContact(
	    		@RequestParam(value="page",defaultValue = "0") int page,
	    		@RequestParam(value ="size",defaultValue = "10") int size,
	    		@RequestParam(value="sortBy",defaultValue = "name") String sortBy,
	    		@RequestParam(value="direction",defaultValue = "asc")String direction,
	    		Model model,Authentication authentication) {
	    	 
	    	 
	     String username =  Helper.getEmailOfLoggedUser(authentication);
	     
	    User user =    userService.getUserByEmail(username);
	       
	   Page<Contact>pageContact = contactService.getByUser(user,page,size,sortBy,direction);
	      
	     model.addAttribute("pageContact", pageContact);
	     
	      model.addAttribute("pageSize",AppConstant.PAGE_SIZE);
	      
	    	 return "user/contact"; 
	    }
	    
}
