package com.mudita.movies.Service;

import com.mudita.movies.service.MovieShowsService;
import com.mudita.movies.service.exception.ErrorCode;
import com.mudita.movies.service.exception.InvalidInputException;
import com.mudita.movies.service.exception.MovieException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class MovieShowTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private MovieShowsService movieShowsService;

    private static final String MOVIE_NAME_IN_SYSTEM = "The Revenant";
    private static final String MOVIE_NAME_NOT_IN_SYSTEM = "abc";
    private static final String THEATRE_NAME_IN_SYSTEM = "AMC CUPERTINO SQUARE 16";
    private static final String THEATRE_NAME_NOT_IN_SYSTEM = "abc";

    @Test
    public void getMovieShowsByIdTest(){
       Assert.assertEquals(true, movieShowsService.getMovieShowsByMovie(MOVIE_NAME_IN_SYSTEM, 10,0) != null);
    }

    @Test(expected = MovieException.class)
    public void getMovieShowsByIdTest2(){
        try {
            Assert.assertNull(movieShowsService.getMovieShowsByMovie(MOVIE_NAME_NOT_IN_SYSTEM, 10, 0));
        } catch (InvalidInputException e){
            Assert.assertEquals(ErrorCode.MISSING_DATA, e.getErrorCode());
            throw e;
        }
    }

    @Test(expected = InvalidInputException.class)
    public void getMovieShowsByIdTest3(){
        try {
            movieShowsService.getMovieShowsByMovie("", 10, 0);
        } catch (InvalidInputException e){
            Assert.assertEquals(ErrorCode.INVALID_INPUT, e.getErrorCode());
            throw e;
        }
    }

    @Test
    public void getMovieShowsByTheatreTest(){
        Assert.assertEquals(true,movieShowsService.getMovieShowsByTheatre(THEATRE_NAME_IN_SYSTEM, 10, 0).theatreId > 0);
    }

    @Test(expected = MovieException.class)
    public void getMovieShowsByTheatreTest2(){
        try {
            Assert.assertNull(movieShowsService.getMovieShowsByTheatre(THEATRE_NAME_NOT_IN_SYSTEM, 10, 0));
        }catch (MovieException e){
            Assert.assertEquals(ErrorCode.MISSING_DATA, e.getErrorCode());
            throw e;
        }
    }

    @Test(expected = MovieException.class)
    public void getMovieShowsByTheatreTest3(){
        try {
            movieShowsService.getMovieShowsByTheatre("", 10, 0);
        }catch (MovieException e){
            Assert.assertEquals(ErrorCode.INVALID_INPUT, e.getErrorCode());
            throw e;
        }
    }
}
