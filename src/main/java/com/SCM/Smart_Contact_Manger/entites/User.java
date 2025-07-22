package com.SCM.Smart_Contact_Manger.entites;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	private String userId;

	private String name;
	private String email;
	private String password;
	private String about;
	private String profilePic;
	private String phoneNumber;

	private boolean enabled = true;
	private boolean emailVerified = false;
	private boolean phoneVerified = false;

	@Enumerated(EnumType.STRING)
	private Providers provider = Providers.SELF;

	private String providerUserId; 

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Contact> contacts = new ArrayList<>();

	public User() {
	}

	public User(String userId, String name, String email, String password, String about, String profilePic,
			String phoneNumber, boolean enabled, boolean emailVerified, boolean phoneVerified, Providers provider,
			String providerUserId) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.profilePic = profilePic;
		this.phoneNumber = phoneNumber;
		this.enabled = enabled;
		this.emailVerified = emailVerified;
		this.phoneVerified = phoneVerified;
		this.provider = provider;
		this.providerUserId = providerUserId;
	}

	// Getters and setters...

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public boolean isPhoneVerified() {
		return phoneVerified;
	}

	public void setPhoneVerified(boolean phoneVerified) {
		this.phoneVerified = phoneVerified;
	}

	public Providers getProvider() {
		return provider;
	}

	public void setProvider(Providers provider) {
		this.provider = provider;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> rolelist = new ArrayList<>();

	public void setRoleList(List<String> roleList) {
	    this.rolelist = roleList;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return rolelist.stream()
	        .map(SimpleGrantedAuthority::new)
	        .collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public String toString() {
		return "User{" + "userId='" + userId + '\'' + ", name='" + name + '\'' + ", email='" + email + '\''
				+ ", about='" + about + '\'' + ", profilePic='" + profilePic + '\'' + ", phoneNumber='" + phoneNumber
				+ '\'' + ", enabled=" + enabled + ", emailVerified=" + emailVerified + ", phoneVerified="
				+ phoneVerified + ", provider=" + provider + ", providerUserId='" + providerUserId + '\'' + '}';
	}

}

/*
 * package com.SCM.Smart_Contact_Manger.entites;
 * 
 * import java.util.ArrayList; import java.util.Collection; import
 * java.util.List;
 * 
 * import org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 * import jakarta.persistence.CascadeType; import jakarta.persistence.Entity;
 * import jakarta.persistence.EnumType; import jakarta.persistence.Enumerated;
 * import jakarta.persistence.FetchType; import
 * jakarta.persistence.GeneratedValue; import
 * jakarta.persistence.GenerationType; import jakarta.persistence.Id; import
 * jakarta.persistence.OneToMany; import jakarta.persistence.Table; import
 * lombok.Builder;
 * 
 * 
 * @Builder
 * 
 * @Entity
 * 
 * @Table(name="users") public class User implements UserDetails{
 * 
 * @Id private String userId; private String name; private String email; private
 * String password; private String about; private String profilePic; private
 * String phoneNumber;
 * 
 * //information private boolean enabled = true; private boolean emailVerified =
 * false; private boolean phoneVerified = false;
 * 
 * @Enumerated(EnumType.STRING) private Providers provider = Providers.SELF;
 * 
 * private String providerUserId;
 * 
 * 
 * public User() {}
 * 
 * 
 * public User(String userId, String name, String email, String password, String
 * about, String profilePic, String phoneNumber, boolean enabled, boolean
 * emailVerified, boolean phoneVerified, Providers provider, String
 * providerUserId) { super(); this.userId = userId; this.name = name; this.email
 * = email; this.password = password; this.about = about; this.profilePic =
 * profilePic; this.phoneNumber = phoneNumber; this.enabled = enabled;
 * this.emailVerified = emailVerified; this.phoneVerified = phoneVerified;
 * this.provider = provider; this.providerUserId = providerUserId; }
 * 
 * //getter and setter
 * 
 * 
 * 
 * public String getUserId() { return userId; }
 * 
 * 
 * public void setUserId(String userId) { this.userId = userId; }
 * 
 * 
 * public String getName() { return name; }
 * 
 * 
 * public void setName(String name) { this.name = name; }
 * 
 * 
 * public String getEmail() { return email; }
 * 
 * 
 * public void setEmail(String email) { this.email = email; }
 * 
 * 
 * public String getPassword() { return password; }
 * 
 * 
 * public void setPassword(String password) { this.password = password; }
 * 
 * 
 * public String getAbout() { return about; }
 * 
 * 
 * public void setAbout(String about) { this.about = about; }
 * 
 * 
 * public String getProfilePic() { return profilePic; }
 * 
 * 
 * public void setProfilePic(String profilePic) { this.profilePic = profilePic;
 * }
 * 
 * 
 * public String getPhoneNumber() { return phoneNumber; }
 * 
 * 
 * public void setPhoneNumber(String phoneNumber) { this.phoneNumber =
 * phoneNumber; }
 * 
 * 
 * 
 * public boolean isEnabled() { return enabled; }
 * 
 * 
 * 
 * public void setEnabled(boolean enabled) { this.enabled = enabled; }
 * 
 * 
 * public boolean isEmailVerified() { return emailVerified; }
 * 
 * 
 * public void setEmailVerified(boolean emailVerified) { this.emailVerified =
 * emailVerified; }
 * 
 * 
 * public boolean isPhoneVerified() { return phoneVerified; }
 * 
 * 
 * public void setPhoneVerified(boolean phoneVerified) { this.phoneVerified =
 * phoneVerified; }
 * 
 * 
 * public Providers getProvider() { return provider; }
 * 
 * 
 * public void setProvider(Providers provider) { this.provider = provider; }
 * 
 * 
 * public String getPorviderUserId() { return providerUserId; }
 * 
 * 
 * public void setPorviderUserId(String porviderUserId) { this.providerUserId =
 * porviderUserId; }
 * 
 * 
 * @Override public String toString() { return "User [userId=" + userId +
 * ", name=" + name + ", email=" + email + ", password=" + password + ", about="
 * + about + ", profilePic=" + profilePic + ", phoneNumber=" + phoneNumber +
 * ", enabled=" + enabled + ", emailVerified=" + emailVerified +
 * ", phoneVerified=" + phoneVerified + ", provider=" + provider +
 * ", porviderUserId=" + providerUserId + "]"; }
 * 
 * 
 * @OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch
 * =FetchType.LAZY,orphanRemoval = true) private List<Contact> contacts = new
 * ArrayList<>();
 * 
 * 
 * 
 * 
 * 
 * @Override public Collection<? extends GrantedAuthority> getAuthorities() { //
 * TODO Auto-generated method stub return null; }
 * 
 * 
 * @Override public String getUsername() { return this.email; }
 * 
 * 
 * 
 * @Override public boolean isAccountNonExpired() { return true; }
 * 
 * @Override public boolean isAccountNonLocked() {
 * 
 * return true; }
 * 
 * 
 * public boolean isCredentialsNonExpird() { return true; }
 * 
 * @Override public boolean isEnable() { return this.enabled; }
 * 
 * }
 */