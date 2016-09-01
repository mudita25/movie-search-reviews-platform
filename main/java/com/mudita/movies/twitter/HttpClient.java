package com.mudita.movies.twitter;

import twitter4j.*;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

import java.util.ArrayList;
import java.util.List;

public class HttpClient {

    private final static String CONSUMER_KEY = "32IKlJo5vf1cuzIcBVrSyiX0G";
    private final static String CONSUMER_KEY_SECRET = "Lw0e9HeRmRdM1SZ81b4kRydWK34VmHFPKlQkFWISJKTpHxAtzk";
    private final static String ACCESS_TOKEN = "704896008346537984-ER0O7IxIoNFYfixw2ahBZ75S6h2n1sk";
    private final static String ACCESS_TOKEN_SECRET = "hd2siuBuyp9P8HDQQEfHXCf1NkwaFsMyPERyzl4EfhseC";
    private static Twitter twitter;

    public static void init(){
         twitter = new TwitterFactory().getInstance();
         twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
         AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
         twitter.setOAuthAccessToken(accessToken);
     }

      public static List<String> getTweet(String movieName){
         List<String> movieTweets =  new ArrayList<>();

        try{
            Query query = new Query(movieName);
            query.setCount(10);
            QueryResult result;
            result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            SentimentAnalysis.init();
            for (Status tweet : tweets) {
                if (tweet.getLang().equals("en")) {
                    movieTweets.add("@" + tweet.getUser().getScreenName() + " - " + tweet.getText()
                          + " - " + SentimentAnalysis.findSentiment(tweet.getText()));
                }
            }
        }
        catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
          return movieTweets;
    }
}