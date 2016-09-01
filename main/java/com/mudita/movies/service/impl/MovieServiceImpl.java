package com.mudita.movies.service.impl;

import java.util.List;
import java.util.Set;
import com.mudita.movies.entity.Movie;
import com.mudita.movies.http.utils.StringUtils;
import com.mudita.movies.repository.MovieRepository;
import com.mudita.movies.service.MovieService;
import com.mudita.movies.service.exception.ErrorCode;
import com.mudita.movies.service.exception.InvalidInputException;
import com.mudita.movies.service.exception.MovieException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<Movie> getAllMoviesInSystem(int limit, int offset) {
		List<Movie> movies = movieRepository.getAllMovies(limit, offset);
		if(movies == null || movies.isEmpty()){
			throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
		}
		return movies;
	}

    @Override
    public List<Movie> getAllUpcomingMoviesInSystem(boolean isMovieReleased, int limit, int offset) {
        List<Movie> movies = movieRepository.getAllUpcomingMovies(isMovieReleased, limit, offset);
        if(movies == null || movies.isEmpty()){
            throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesById(Set<Long> movieIds){
        List<Movie> movies = movieRepository.getMovieById(movieIds);
        if(movies == null){
            throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
        }
        return movies;
    }

	@Override
	public List<Movie> getMoviesByParameters(String name, String year, String genre, int limit, int offset) {
		if(StringUtils.isNullOrEmpty(name) && StringUtils.isNullOrEmpty(year) && StringUtils.isNullOrEmpty(genre)){
			throw new MovieException(ErrorCode.INVALID_INPUT, "Please enter valid input");
		}

		List<Movie> movies = movieRepository.getMoviesByParameters(name, year, genre, limit, offset);
		if(movies == null || movies.isEmpty()){
			throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
		}
		return movies;
	}

	//Filter movies by name
	@Override
	public Movie getMovieByName(String movieTitle){
		if(StringUtils.isNullOrEmpty(movieTitle)){
			throw new InvalidInputException("Movie title is required");
		}
		Movie movie = movieRepository.getMovieByName(movieTitle);
		if(movie == null){
			throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
		}
		return movie;
	}
}
