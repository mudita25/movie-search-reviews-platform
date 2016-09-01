package com.mudita.movies.http;

import com.mudita.movies.entity.Movie;
import com.mudita.movies.http.entity.HttpMovie;
import com.mudita.movies.http.utils.StringUtils;
import com.mudita.movies.imdb.HttpImdbClient;
import com.mudita.movies.service.MovieService;
import com.mudita.movies.twitter.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/movies")
@Component
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class MovieResource {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MovieService movieService;

    @GET
    @Path("/all")
    public List<HttpMovie> getAllMovies(@QueryParam("limit") String pLimit,
                                        @QueryParam("offset") String pOffset){

            logger.info("user search = all");

            int limit = 10;
            int offset = 0;

            if(!StringUtils.isNullOrEmpty(pLimit)){
                limit = Integer.parseInt(pLimit);
            }
            if(!StringUtils.isNullOrEmpty(pOffset)){
                offset = Integer.parseInt(pOffset);
            }
            List<Movie> found = movieService.getAllMoviesInSystem(limit, offset);
            List<HttpMovie> returnList = new ArrayList<>(found.size());
            HttpClient.init();
            for(Movie movie:found){
                List<String> tweets = HttpClient.getTweet(movie.getMovieTitle());
                returnList.add(new HttpMovie(movie, tweets, HttpImdbClient.getRating(movie.getMovieTitle())));
            }
            return returnList;
    }

    @GET
    @Path("/")
    public List<HttpMovie> getMovieListings(@QueryParam("name") String name,
                                            @QueryParam("year") String year,
                                            @QueryParam("genre") String genre,
                                            @QueryParam("limit") String pLimit,
                                            @QueryParam("offset") String pOffset){

        logger.info("user search =" + name + year + genre);

        int limit = 10;
        int offset = 0;

        if (!StringUtils.isNullOrEmpty(pLimit)) {
            limit = Integer.parseInt(pLimit);
        }
        if (!StringUtils.isNullOrEmpty(pOffset)) {
            offset = Integer.parseInt(pOffset);
        }

        List<Movie> found = movieService.getMoviesByParameters(name, year, genre, limit, offset);
        List<HttpMovie> returnList = new ArrayList<>(found.size());
        HttpClient.init();
        for (Movie movie : found) {
            List<String> tweets = HttpClient.getTweet(movie.getMovieTitle());
            returnList.add(new HttpMovie(movie,tweets, HttpImdbClient.getRating(movie.getMovieTitle())));
            }
        return returnList;
    }

    @GET
    @Path("/upcoming")
    public List<HttpMovie> getAllUpcomingMovies(@QueryParam("released") String isReleased,
                                                @QueryParam("limit") String pLimit,
                                                @QueryParam("offset") String pOffset){

        logger.info("user search = upcoming");

        //default behaviour is to show upcoming movies, not released yet
        boolean isMovieReleased = false;
        int limit = 10;
        int offset = 0;

        if(!StringUtils.isNullOrEmpty(pLimit)){
            limit = Integer.parseInt(pLimit);
        }
        if(!StringUtils.isNullOrEmpty(pOffset)){
            offset = Integer.parseInt(pOffset);
        }

        if(!StringUtils.isNullOrEmpty(isReleased)){
            isMovieReleased = Boolean.parseBoolean(isReleased);
        }
        List<Movie> found = movieService.getAllUpcomingMoviesInSystem(isMovieReleased,limit, offset);
        List<HttpMovie> returnList = new ArrayList<>(found.size());
        HttpClient.init();
        for(Movie movie:found){
            List<String> tweets = HttpClient.getTweet(movie.getMovieTitle());
            returnList.add(new HttpMovie(movie, tweets, HttpImdbClient.getRating(movie.getMovieTitle())));
        }
        return returnList;
    }
}