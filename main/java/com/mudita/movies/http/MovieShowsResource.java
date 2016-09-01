package com.mudita.movies.http;

import com.mudita.movies.http.entity.HttpShowsByMovie;
import com.mudita.movies.http.entity.HttpShowsByTheatre;
import com.mudita.movies.http.utils.StringUtils;
import com.mudita.movies.service.MovieShowsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/showtimes")
@Component
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class MovieShowsResource {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MovieShowsService movieShowsService;

    @GET
    @Path("/theatre")
    public HttpShowsByTheatre getShowsByTheatreName(@QueryParam("name") String name,
                                                    @QueryParam("limit") String pLimit,
                                                    @QueryParam("offset") String pOffset){

        logger.info("user search ="+name);

        int limit = 10;
        int offset = 0;

        if(!StringUtils.isNullOrEmpty(pLimit)){
            limit = Integer.parseInt(pLimit);
        }
        if(!StringUtils.isNullOrEmpty(pOffset)){
            offset = Integer.parseInt(pOffset);
        }
        HttpShowsByTheatre found = movieShowsService.getMovieShowsByTheatre(name,limit,offset);
        return found;
    }

    @GET
    @Path("/movie")
    public HttpShowsByMovie getShowsByMovieName(@QueryParam("name") String name,
                                                @QueryParam("limit") String pLimit,
                                                @QueryParam("offset") String pOffset){

        logger.info("user search ="+name);

        int limit = 10;
        int offset = 0;
        if (!StringUtils.isNullOrEmpty(pLimit)) {
            limit = Integer.parseInt(pLimit);
        }
        if (!StringUtils.isNullOrEmpty(pOffset)) {
            offset = Integer.parseInt(pOffset);
        }
        HttpShowsByMovie found = movieShowsService.getMovieShowsByMovie(name, limit, offset);
        return found;
    }
}