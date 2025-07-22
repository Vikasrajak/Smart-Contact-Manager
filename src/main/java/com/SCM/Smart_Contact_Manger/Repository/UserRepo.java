package com.SCM.Smart_Contact_Manger.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SCM.Smart_Contact_Manger.entites.User;

@Repository
public interface UserRepo extends JpaRepository<User, String>{
	
// we can write custome finder method
	
	Optional<User> findByEmail(String email);
}
