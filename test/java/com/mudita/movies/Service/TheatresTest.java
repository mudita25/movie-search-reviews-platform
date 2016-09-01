package com.mudita.movies.Service;

import com.mudita.movies.service.TheatreService;
import com.mudita.movies.service.exception.ErrorCode;
import com.mudita.movies.service.exception.InvalidInputException;
import com.mudita.movies.service.exception.MovieException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TheatresTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	TheatreService theatreService;

	private static final String THEATRE_NAME_IN_SYSTEM = "AMC CUPERTINO SQUARE 16";
	private static final String THEATRE_NAME_NOT_IN_SYSTEM = "abc";

	@Test
	public void getAllTheatresTest(){
		Assert.assertNotNull(theatreService.getAllTheatresInSystem(10,0).size());
	}

	@Test
	public void getAllTheatresTest2(){
		Assert.assertEquals(true, theatreService.getAllTheatresInSystem(10,0).size() > 0);
	}

	@Test
	public void getTheatreByNameTest(){
		Assert.assertEquals(THEATRE_NAME_IN_SYSTEM, theatreService.getTheatreByName(THEATRE_NAME_IN_SYSTEM).getTheatreName());
	}

	@Test(expected = MovieException.class)
	public void getTheatreByNameTest2(){
		try {
			Assert.assertNull(theatreService.getTheatreByName(THEATRE_NAME_NOT_IN_SYSTEM));
		}catch (MovieException e){
			Assert.assertEquals(ErrorCode.MISSING_DATA, e.getErrorCode());
			throw e;
		}
	}

	@Test(expected = InvalidInputException.class)
	public void getMovieByNameTest3(){
		try {
			theatreService.getTheatreByName("");
		}catch (InvalidInputException e){
			Assert.assertEquals(ErrorCode.INVALID_INPUT, e.getErrorCode());
			throw e;
		}
	}
}
