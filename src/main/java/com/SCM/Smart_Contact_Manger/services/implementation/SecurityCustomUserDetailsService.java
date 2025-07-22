package com.SCM.Smart_Contact_Manger.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SCM.Smart_Contact_Manger.Repository.UserRepo;

@Service
public class SecurityCustomUserDetailsService implements UserDetailsService {

	   @Autowired
	   private UserRepo userRepo;
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		   
		return userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
	}

}
