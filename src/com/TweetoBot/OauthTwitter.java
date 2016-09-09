package com.TweetoBot;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class OauthTwitter {
	
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;
	private Twitter twitter;
	// constructor
	OauthTwitter() {
		fillDataFromProperties();
		twitterAuth();
	}
	
	// getters and setters : actaully no need of these
//	public String getConsumerKey() {
//		return consumerKey;
//	}
//	public void setConsumerKey(String consumerKey) {
//		this.consumerKey = consumerKey;
//	}
//	public String getConsumerSecret() {
//		return consumerSecret;
//	}
//	public void setConsumerSecret(String consumerSecret) {
//		this.consumerSecret = consumerSecret;
//	}
//	public String getAccessToken() {
//		return accessToken;
//	}
//	public void setAccessToken(String accessToken) {
//		this.accessToken = accessToken;
//	}
//	public String getAccessTokenSecret() {
//		return accessTokenSecret;
//	}
//	public void setAccessTokenSecret(String accessTokenSecret) {
//		this.accessTokenSecret = accessTokenSecret;
//	}
	
	public Twitter getTwitter() {
		return twitter;
	}
	public void setTwitter(Twitter twitter) {
		this.twitter=twitter;
	}
	
	// get data from application.properties file
	
	private void fillDataFromProperties() {
		
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			
			prop.load(new FileInputStream("./resources/application.passphrases"));
			
//			String filename = "application.properties";
//			input = getClass().getClassLoader().getResourceAsStream(filename);
//			if (input == null) {
//				// TODO : Use logging mechanism
//				System.out.println("Sorry, unable to find " + filename);
//				return;
//			}
//			
//			prop.load(input);
			
//			Enumeration<?> e = prop.propertyNames();
//			while (e.hasMoreElements()) {
//				String key = (String) e.nextElement();
//				String value = prop.getProperty(key);
//				System.out.println(key + " " + value);
//			}
//
//			setConsumerKey(prop.getProperty("CONSUMER_KEY"));
//			setConsumerSecret(prop.getProperty("CONSUMER_SECRET"));
//			setAccessToken(prop.getProperty("ACCESS_TOKEN"));
//			setAccessTokenSecret(prop.getProperty("ACCESS_TOKEN_SECRET"));
	
			consumerKey = prop.getProperty("CONSUMER_KEY");
			consumerSecret = prop.getProperty("CONSUMER_SECRET");
			accessToken = prop.getProperty("ACCESS_TOKEN");
			accessTokenSecret = prop.getProperty("ACCESS_TOKEN_SECRET");
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(input!=null) {
				try {
					input.close();
				} catch(IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}	
	}
	
	private Twitter twitterAuth() {
		TwitterFactory twitterFactory = new TwitterFactory();
		twitter = twitterFactory.getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
		return twitter;
	}
	
}
