package com.SCM.Smart_Contact_Manger.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SCM.Smart_Contact_Manger.Repository.UserRepo;
import com.SCM.Smart_Contact_Manger.entites.User;
import com.SCM.Smart_Contact_Manger.helper.AppConstant;
import com.SCM.Smart_Contact_Manger.helper.ResourceNotFoundException;
import com.SCM.Smart_Contact_Manger.services.UserService;


@Service
public class Serviceimple implements UserService {
  
	   @Autowired
	   UserRepo userRepo;
	   
	   @Autowired
	   private PasswordEncoder passwordEncoder;
	 
	  private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Override
	public User saveUser(User user) {
		
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		 
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
		 
		 user.setRoleList(List.of(AppConstant.ROLL_USER));
		  
		return userRepo.save(user);
	}

	@Override
	public Optional<User> getUserById(String id) {
		
		return userRepo.findById(id);
				
	}

	@Override
	public Optional<User> updateUser(User user) {
	
	User user2 =userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("user not found"));
	  
	   user2.setName(user.getName());
	   user2.setEmail(user.getEmail());;
	   user2.setPassword(user.getPassword());
	   user2.setAbout(user.getAbout());
	   user2.setPhoneNumber(user.getPhoneNumber());
	   user2.setProfilePic(user.getProfilePic());
		/*
		 * user2.setEnabled(user.isEnabled());
		 */	   user2.setEmailVerified(user.isEmailVerified());
	   user2.setPhoneVerified(user.isPhoneVerified());
	   user2.setProvider(user.getProvider());
	   user2.setProviderUserId(user.getProviderUserId());
	   
	 User save =  userRepo.save(user2);
	  return Optional.ofNullable(save);
	   
	   
	   
	   
	     
	}

	@Override
	public void deleteUser(String id) {
		
		User user2 =userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("user not found"));
		
           userRepo.delete(user2);
		
	}

	@Override
	public boolean isUserExist(String userId) {
		
		User user2=userRepo.findById(userId).orElse(null);
		return user2 !=null ? true : false;
		

	}

	@Override
	public boolean isUserExistByEmail(String email) {
		  
		  User user = userRepo.findByEmail(email).orElse(null);
		  return user !=null ? true : false;
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepo.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		
		return userRepo.findByEmail(email).orElse(null);
	}

	

}
