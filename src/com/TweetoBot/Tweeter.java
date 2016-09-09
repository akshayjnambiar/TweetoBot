package com.TweetoBot;

import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.Place;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Tweeter {
		
	public static void main(String[] args) {
		OauthTwitter oauthTwitter = new OauthTwitter();
		Twitter twitter = oauthTwitter.getTwitter();
		
		Paging paging = new Paging(1, 5);
		ResponseList<Status> statuses=null;
		try {
			 statuses= twitter.getUserTimeline("AmericanExpress",paging);
			System.out.println("Total: "+statuses.size());
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(444);
		}
		
		for(Status status : statuses) {
			
			GeoLocation geoLocation = status.getGeoLocation();
			Place place = status.getPlace();
			String text = status.getText();
			
			if(geoLocation != null) {
				System.out.println(geoLocation.getLatitude());
			}
			
			if(place != null) {
				String country = place.getCountry();
				String fullName = place.getFullName();
				if (country != null) { System.out.println(country); }
				if (fullName != null) { System.out.println(fullName); }
			}
			
			if(text != null) {
				System.out.println(text);
			}
		}
	}

}
