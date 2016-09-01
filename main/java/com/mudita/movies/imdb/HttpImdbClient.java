package com.mudita.movies.imdb;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.mudita.movies.http.exception.HttpError;
import com.mudita.movies.service.exception.ErrorCode;
import com.mudita.movies.service.exception.MovieException;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpImdbClient {

      public static HttpIMDB getRating(String movieName){
          try {
              URL myURL = new URL("http://www.omdbapi.com/?t=" + URLEncoder.encode(movieName, "UTF-8") + "&tomatoes=true");
              URLConnection myurl = myURL.openConnection();
              myurl.connect();
              Map<String, String> map = new Gson().fromJson(new InputStreamReader(myurl.getInputStream(), "UTF-8"), new TypeToken<Map<String, String>>(){}.getType());
              String imdbRating = map.get("imdbRating");
              String rottenTomatoes = map.get("tomatoRating");
              HttpIMDB imdb = new HttpIMDB(imdbRating, rottenTomatoes);
              return imdb;
          }
          catch (IOException e) {
            throw new MovieException(ErrorCode.MISSING_DATA, "Error while calling IMDB client");
          }
      }

    public static void main(String[] args) {
        HttpIMDB response = getRating("airlift");
    }
}