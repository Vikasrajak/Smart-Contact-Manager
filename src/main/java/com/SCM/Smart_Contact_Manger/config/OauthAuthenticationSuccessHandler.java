package com.SCM.Smart_Contact_Manger.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.SCM.Smart_Contact_Manger.Repository.UserRepo;
import com.SCM.Smart_Contact_Manger.entites.Providers;
import com.SCM.Smart_Contact_Manger.entites.User;
import com.SCM.Smart_Contact_Manger.helper.AppConstant;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OauthAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	 @Autowired
	private  UserRepo userRepo;
	
	Logger logger =LoggerFactory.getLogger(OauthAuthenticationSuccessHandler.class);
	 
	
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response,
			Authentication authentication
			) throws IOException, ServletException {

		  logger.info("OauthenticationSuccessHandler");
		  
		 DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();
		 
			/*
			 * logger.info(user.getName());
			 * 
			 * user.getAttributes().forEach((key,value)->{ logger.info("{} =>{}",key,value);
			 * });
			 *  
			 * logger.info(user.getAttributes().toString());
			 * 
			 */
		 
		    String email =user.getAttribute("email").toString();
		    String name = user.getAttribute("name").toString();
		    String picture = user.getAttribute("picture").toString();
		    
		    //save user in data base
		    
		    User user1 = new User();
		    user1.setEmail(email);
		    user1.setName(name);
		    user1.setProfilePic(picture);
		    user1.setUserId(UUID.randomUUID().toString());
		    user1.setPassword("password");
		    user1.setProvider(Providers.GOOGLE);
		    user1.setEmailVerified(true);
		    
		    user1.setProviderUserId(user.getName());
		    user1.setRoleList(List.of(AppConstant.ROLL_USER));
		    user1.setAbout("this account created using google");
		    
		 User user2   = userRepo.findByEmail(email).orElse(null);
		    if(user2 == null) {
		    	
		    	 userRepo.save(user1);
		    	 logger.info("User saved"+email);
		    }
		    
		  
		  new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
	}

}
