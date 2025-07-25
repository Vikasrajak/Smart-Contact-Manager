package com.SCM.Smart_Contact_Manger.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SCM.Smart_Contact_Manger.entites.Contact;
import com.SCM.Smart_Contact_Manger.entites.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact,String> {

	
	//custom finder method 
	 Page<Contact>findByUser(User user,Pageable pageable);
	 
	 //custom query method	
	 @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
	 List<Contact>findByUserId(@Param("userId")String usreId);
	 
}
