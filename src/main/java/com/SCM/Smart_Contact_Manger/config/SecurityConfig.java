package com.SCM.Smart_Contact_Manger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.SCM.Smart_Contact_Manger.services.implementation.SecurityCustomUserDetailsService;

@Configuration
public class SecurityConfig {

	@Autowired
	private SecurityCustomUserDetailsService userDetailService;
	
	@Autowired
	private OauthAuthenticationSuccessHandler handler;

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

		daoAuthenticationProvider.setUserDetailsService(userDetailService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

		return daoAuthenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain sucurityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.authorizeHttpRequests(athorize -> {

			athorize.requestMatchers("/user/**").authenticated();
			athorize.anyRequest().permitAll();
		});

		httpSecurity.formLogin(formLogin -> {

			formLogin.loginPage("/login");
			formLogin.loginProcessingUrl("/authenticate");
			formLogin.defaultSuccessUrl("/user/profile");
			/*
			 * formLogin.failureForwardUrl("/login?error=true");
			 */ formLogin.usernameParameter("email");
			formLogin.passwordParameter("password");
		});

		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		httpSecurity.logout(logoutForm -> {

			logoutForm.logoutUrl("/do-logout");
		});
		
		
        httpSecurity.oauth2Login(oauth->{
        	 
        	oauth.loginPage("/login");
			
        	
         oauth.successHandler(handler);
         
        	
        });
           

		return httpSecurity.build();

	}

}
