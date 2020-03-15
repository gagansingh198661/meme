package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FbService {
	 @Value("${spring.social.facebook.appId}")
	 String facebookAppId;
	 
	 @Value("${spring.social.facebook.appSecret}")
	 String facebookSecret;
	 
	 public String createFacebookAuthorizationURL(){
		    FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
		    OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		    OAuth2Parameters params = new OAuth2Parameters();
		    params.setRedirectUri("http://localhost:8080/facebook");
		    params.setScope("public_profile,email");
		    return oauthOperations.buildAuthorizeUrl(params);
		}
	 
	 public String createFacebookAccessToken(String code) {
		    FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
		    AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, "http://localhost:8080/facebook", null);
		    return  accessGrant.getAccessToken();
		}
	 
	 public Object getName(String accessToken) {
		    Facebook facebook = new FacebookTemplate(accessToken);
		    String[] fields = {"id", "name","email"};
		    return facebook.fetchObject("me", String.class, fields);
		}
}