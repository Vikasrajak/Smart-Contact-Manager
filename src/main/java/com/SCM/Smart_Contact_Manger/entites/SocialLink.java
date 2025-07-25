package com.SCM.Smart_Contact_Manger.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SocialLink {
	
	@Id
	private Long id;
	private String link;
	private String title;
	
	
	 public SocialLink() {
		 
	 }


	public SocialLink(Long id, String link, String title) {
		super();
		this.id = id;
		this.link = link;
		this.title = title;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return "SocialLink [id=" + id + ", link=" + link + ", title=" + title + "]";
	}
	 
	 
	@ManyToOne
	private Contact contact;

}
