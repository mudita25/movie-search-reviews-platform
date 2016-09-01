package com.mudita.movies.http.exception;

import com.mudita.movies.service.exception.MovieException;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Return HTTP 409 with entity body
 */

@Provider
@Component
public class MovieExceptionMapper implements ExceptionMapper<MovieException> {

	@Override
	public Response toResponse(MovieException ex) {
		return Response.status(Response.Status.CONFLICT).entity(new HttpError(ex)).build();
	}

}
