package com.SCM.Smart_Contact_Manger.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedUser(Authentication authentication) {

        String username = "";

        // First check if it's OAuth2
        if (authentication instanceof OAuth2AuthenticationToken oauth2Token) {
            System.out.println("Getting email from OAuth2 provider");

            OAuth2User oauth2User = oauth2Token.getPrincipal();
            String clientId = oauth2Token.getAuthorizedClientRegistrationId();

            if (clientId.equalsIgnoreCase("google")) {
                username = oauth2User.getAttribute("email");
            } else if (clientId.equalsIgnoreCase("github")) {
                // GitHub might return "login" instead of email unless you request user:email scope
                username = oauth2User.getAttribute("email"); // null if not granted
                if (username == null) {
                    username = oauth2User.getAttribute("login"); // fallback
                }
            }

        } else {
            // Default: local login
            System.out.println("Getting data from local database");
            username = authentication.getName();
        }

        return username;
    }
}




/*
 * package com.SCM.Smart_Contact_Manger.helper;
 * 
 * 
 * import org.springframework.security.core.Authentication; import
 * org.springframework.security.oauth2.client.authentication.
 * OAuth2AuthenticationToken; import
 * org.springframework.security.oauth2.core.user.OAuth2User;
 * 
 * public class Helper {
 * 
 * 
 * public static String getEmailOfLoggedUser(Authentication authentication) {
 * 
 * var oauth2User = (OAuth2User)authentication.getPrincipal(); String username
 * ="";
 * 
 * if(authentication instanceof OAuth2AuthenticationToken) {
 * 
 * var aOAuth2AuthenticationToken =(OAuth2AuthenticationToken)authentication;
 * 
 * var clientId =aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
 * 
 * if(clientId.equalsIgnoreCase("google")){
 * 
 * System.out.println("Getting email from google"); username =
 * oauth2User.getAttribute("email").toString(); }
 * 
 * return username;
 * 
 * 
 * }
 * 
 * else { System.out.println("Getting data from local database"); return
 * authentication.getName(); }
 * 
 * }
 * 
 * }
 */