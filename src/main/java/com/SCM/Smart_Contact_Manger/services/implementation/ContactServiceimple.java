package com.SCM.Smart_Contact_Manger.services.implementation;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.SCM.Smart_Contact_Manger.Repository.ContactRepo;
import com.SCM.Smart_Contact_Manger.entites.Contact;
import com.SCM.Smart_Contact_Manger.entites.User;
import com.SCM.Smart_Contact_Manger.helper.ResourceNotFoundException;
import com.SCM.Smart_Contact_Manger.services.ContactService;

@Service
public class ContactServiceimple  implements ContactService{
 
	     @Autowired
	    private ContactRepo contactRepo;
	
	
	@Override
	public Contact saveContact(Contact contact) {
		
		contact.setId(UUID.randomUUID().toString());
		 
		return contactRepo.save(contact);
	}


	@Override
	public Contact update(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Contact> getAll() {
		return contactRepo.findAll();
	}


	@Override
	public Contact getById(String id) {
		
		return contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("contact not fouen"));
	}


	@Override
	public void delete(String id) {
		
	 var contactid	 = contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("contact not fouen"));
	     contactRepo.delete(contactid);
		
	}


	@Override
	public List<Contact> search(String name, String email, String phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Contact> getByUserId(String userId) {
		return contactRepo.findByUserId(userId);
	}



	@Override
	public Page<Contact> getByUser(User user, int page, int size,String sortBy, String direction) {
		
		 Sort sort =  direction.equals("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
	    Pageable pageable = PageRequest.of(page, size);
	    return contactRepo.findByUser(user, pageable); // matches repo method name
	}




	

}
