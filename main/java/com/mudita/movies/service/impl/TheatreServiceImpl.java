package com.mudita.movies.service.impl;

import com.mudita.movies.entity.Theatre;
import com.mudita.movies.http.utils.StringUtils;
import com.mudita.movies.repository.TheatreRepository;
import com.mudita.movies.service.TheatreService;
import com.mudita.movies.service.exception.ErrorCode;
import com.mudita.movies.service.exception.InvalidInputException;
import com.mudita.movies.service.exception.MovieException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TheatreServiceImpl implements TheatreService{

	@Autowired
	TheatreRepository theatreRepository;

	@Override
	public List<Theatre> getTheatreById(Set<Long> theatreIds){
		List<Theatre> theatres = theatreRepository.getTheatreById(theatreIds);
		if(theatres == null){
			throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
		}
		return theatres;
	}

	@Override
	public List<Theatre> getAllTheatresInSystem(int limit, int offset) {
		List<Theatre> theatres = theatreRepository.getAllTheatres(limit, offset);
		if(theatres == null || theatres.isEmpty()){
			throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
		}
		return theatres;
	}

	@Override
	public List<Theatre> getTheatresByParameters(String name, String zip, String place, int limit, int offset) {
		if(StringUtils.isNullOrEmpty(name) && StringUtils.isNullOrEmpty(zip) && StringUtils.isNullOrEmpty(place)) {
			throw new MovieException(ErrorCode.INVALID_INPUT, "Please enter valid input");
		}
		int zipCode = 0;
		if(! StringUtils.isNullOrEmpty(zip)) {
			 zipCode = Integer.parseInt(zip);
		}
		List<Theatre> theatres = theatreRepository.getTheatresByParameters(name, zipCode, place, limit, offset);
		if(theatres == null || theatres.isEmpty()){
			throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
		}
		return theatres;
	}

	//Filter theatres by name
	@Override
	public Theatre getTheatreByName(String theatreName){
		if(StringUtils.isNullOrEmpty(theatreName)){
			throw new InvalidInputException("Theatre name is required");
		}
		Theatre theatre = theatreRepository.getTheatreByName(theatreName);
		if(theatre == null){
			throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
		}
		return theatre;
	}
}
