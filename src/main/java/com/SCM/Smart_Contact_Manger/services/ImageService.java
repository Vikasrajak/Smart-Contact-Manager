package com.SCM.Smart_Contact_Manger.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	
	
	 String uploadImage(MultipartFile contactImage);
	 
	 String getUrlFromPublicId(String publicId);

}
