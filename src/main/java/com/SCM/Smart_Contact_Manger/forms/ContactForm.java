package com.SCM.Smart_Contact_Manger.forms;

import org.springframework.web.multipart.MultipartFile;

import com.SCM.Smart_Contact_Manger.validator.ValidFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ContactForm {
	
  
	 @NotBlank(message="Name is required")
	 private String name;
	 
	 @NotBlank(message="email is required")
	 @Email(message="Invalid EmailAddress")
	 private String email;
	 
	 @NotBlank(message="number is required")
	 @Pattern(regexp = "^[0-9]{10}$",message="Invalid PhoneNumber")
	 private String phoneNumber;
	 
	 @NotBlank(message="address is required")
	 private String address;
	 
	 private String description;
	 
	 private boolean favorite;
	 
	 private String websiteLink;
	 
	 private String linkedInLink;
	 
	 @ValidFile(message="Invalid File")
	 private MultipartFile contactImage;
	 
	 
	    public ContactForm(){  
	   }


		public ContactForm(String name, String email, String phoneNumber, String address, String description,
				boolean favorite, String websiteLink, String linkedInLink, MultipartFile contactImage) {
			super();
			this.name = name;
			this.email = email;
			this.phoneNumber = phoneNumber;
			this.address = address;
			this.description = description;
			this.favorite = favorite;
			this.websiteLink = websiteLink;
			this.linkedInLink = linkedInLink;
			this.contactImage = contactImage;
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


		public String getPhoneNumber() {
			return phoneNumber;
		}


		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}


		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		/*
		 * public String getFavorite() { return favorite; }
		 * 
		 * 
		 * public void setFavorite(String favorite) { this.favorite = favorite;
		 */
		


		public String getWebsiteLink() {
			return websiteLink;
		}


		public boolean isFavorite() {
			return favorite;
		}


		public void setFavorite(boolean favorite) {
			this.favorite = favorite;
		}


		public void setWebsiteLink(String websiteLink) {
			this.websiteLink = websiteLink;
		}


		public String getLinkedInLink() {
			return linkedInLink;
		}


		public void setLinkedInLink(String linkedInLink) {
			this.linkedInLink = linkedInLink;
		}



		public MultipartFile getContactImage() {
			return contactImage;
		}


		public void setContactImage(MultipartFile contactImage) {
			this.contactImage = contactImage;
		}


		@Override
		public String toString() {
			return "ContactForm [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address="
					+ address + ", description=" + description + ", favorite=" + favorite + ", websiteLink="
					+ websiteLink + ", linkedInLink=" + linkedInLink + ", profileImage=" + contactImage + "]";
		}


	    
	    

}
