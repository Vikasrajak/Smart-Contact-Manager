package com.SCM.Smart_Contact_Manger.helper;


public class ResourceNotFoundException extends RuntimeException{
	
	
	 public  ResourceNotFoundException() {
		 super("user not found");
	 }
	 
	 
	  public  ResourceNotFoundException(String message) {
		  super(message);
	  }

}
