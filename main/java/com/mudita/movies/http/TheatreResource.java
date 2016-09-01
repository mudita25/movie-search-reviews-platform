package com.mudita.movies.http;

import com.mudita.movies.entity.Theatre;
import com.mudita.movies.http.entity.HttpTheatre;
import com.mudita.movies.http.utils.StringUtils;
import com.mudita.movies.service.TheatreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/theatres")
@Component
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class TheatreResource {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private TheatreService theatreService;

    @GET
    @Path("/all")
    public List<HttpTheatre> getAllTheatres(@QueryParam("limit") String pLimit,
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
        List<Theatre> found = theatreService.getAllTheatresInSystem(limit, offset);
        List<HttpTheatre> returnList = new ArrayList<>(found.size());
        for(Theatre theatre:found){
            returnList.add(new HttpTheatre(theatre));
        }
        return returnList;
    }

    @GET
    @Path("/")
    public List<HttpTheatre> getTheatreListings(@QueryParam("name") String name,
                                                @QueryParam("zip") String zip,
                                                @QueryParam("place") String place,
                                                @QueryParam("limit") String pLimit,
                                                @QueryParam("offset") String pOffset){

        logger.info("user search =" + name + zip + place);

        int limit = 10;
        int offset = 0;

        if(!StringUtils.isNullOrEmpty(pLimit)){
            limit = Integer.parseInt(pLimit);
        }
        if(!StringUtils.isNullOrEmpty(pOffset)){
            offset = Integer.parseInt(pOffset);
        }

        List<Theatre> found = theatreService.getTheatresByParameters(name, zip, place,  limit, offset);

        List<HttpTheatre> returnList = new ArrayList<>(found.size());
        for(Theatre theatre:found){
            returnList.add(new HttpTheatre(theatre));
        }
        return returnList;
    }
}