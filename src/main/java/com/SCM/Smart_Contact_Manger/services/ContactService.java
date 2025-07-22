package com.SCM.Smart_Contact_Manger.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SCM.Smart_Contact_Manger.entites.Contact;
import com.SCM.Smart_Contact_Manger.entites.User;

public interface ContactService {

	public Contact saveContact(Contact contact);

	// update
	Contact update(Contact contact);

	// get Contact
	List<Contact> getAll();

	// get Contact by id
	Contact getById(String id);

	// delete contact
	void delete(String id);

	// serarch contact

	List<Contact> search(String name, String email, String phoneNumber);

	List<Contact> getByUserId(String userId);
	
	Page<Contact>getByUser(User user,int page ,int size, String sortFiled, String sortDirection);

}
