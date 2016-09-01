package com.mudita.movies.Service;

import com.mudita.movies.service.MovieService;
import com.mudita.movies.service.exception.ErrorCode;
import com.mudita.movies.service.exception.InvalidInputException;
import com.mudita.movies.service.exception.MovieException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class MoviesTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private MovieService movieService;

	private static final String MOVIE_NAME_IN_SYSTEM = "The Revenant";
	private static final String MOVIE_NAME_NOT_IN_SYSTEM = "abc";

	@Test
	public void getAllMoviesTest(){
		Assert.assertNotNull(movieService.getAllMoviesInSystem(10,0).size());
	}

	@Test
	public void getAllMoviesTest2(){
		Assert.assertEquals(true, movieService.getAllMoviesInSystem(10,0).size() > 0);
	}

	@Test
	public void getMovieByNameTest(){
		Assert.assertEquals(MOVIE_NAME_IN_SYSTEM, movieService.getMovieByName(MOVIE_NAME_IN_SYSTEM).getMovieTitle());
	}

	@Test(expected = MovieException.class)
	public void getMovieByNameTest2(){
		try {
			movieService.getMovieByName(MOVIE_NAME_NOT_IN_SYSTEM);
		} catch (MovieException e){
			Assert.assertEquals(ErrorCode.MISSING_DATA, e.getErrorCode());
			throw e;
		}
	}

	@Test(expected = InvalidInputException.class)
	public void getMovieByNameTest3(){
		try {
			movieService.getMovieByName("");
		} catch (InvalidInputException e){
			Assert.assertEquals(ErrorCode.INVALID_INPUT, e.getErrorCode());
			throw e;
		}
	}
}
