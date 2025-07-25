package com.SCM.Smart_Contact_Manger.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserForm {
  
	 @NotBlank(message="Username is required")
	 @Size(min =3, message="Min 3 Characters is required")
	 private String name;
	 
	 @Email(message="Invalid Email Address")
	 @NotBlank(message="Email is required")
	 private String email;
	 
	 @NotBlank(message="Password is required")
	 @Size(min =6, message="Min 6 Character is required")
	 private String password;
	 
	 @NotBlank(message="PhoneNumber is required")
	 @Size(min=8,max=10, message="Invalid Number")
	 private String phoneNumber;
	 
	 @NotBlank(message="message is required")
	 private String about;
	 
	 
	  public UserForm() {
		  
	  }


	public UserForm(String name, String email, String password, String phoneNumber, String about) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.about = about;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	@Override
	public String toString() {
		return "UserForm [name=" + name + ", email=" + email + ", password=" + password + ", phoneNumber=" + phoneNumber
				+ ", about=" + about + "]";
	}
	  
	  
}
