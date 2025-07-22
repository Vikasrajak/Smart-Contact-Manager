package com.SCM.Smart_Contact_Manger.services.implementation;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SCM.Smart_Contact_Manger.helper.AppConstant;
import com.SCM.Smart_Contact_Manger.services.ImageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageServiceImp implements ImageService{

	    
	   @Autowired
	    private Cloudinary cloudinary;
	  
	

	@Override
	public String uploadImage(MultipartFile contactImage) {
		
		  String filename = UUID.randomUUID().toString();
		
		  try {
			  
			  byte[] data= new byte[contactImage.getInputStream().available()];
			   contactImage.getInputStream().read(data);
			   cloudinary.uploader().upload(data, ObjectUtils.asMap(
					   
					   "public_id",filename));
			   
				return this.getUrlFromPublicId(filename);

			  
		  }
		   	catch (Exception e) {
				
		   		 e.printStackTrace();
		   		 
		   		 return null;
			}
		
	}



	@Override
	public String getUrlFromPublicId(String publicId) {
		
		return cloudinary
				.url()
				.transformation(
						new Transformation<Transformation>()
						  .width(AppConstant.CONTACT_IMAGE_WIDTH)
						  .height(AppConstant.CONTACT_IMAGE_HEIGHT)
						  .crop(AppConstant.CONTACT_IMAGE_CROP)
						)
				.generate(publicId);
				  
	}

}
