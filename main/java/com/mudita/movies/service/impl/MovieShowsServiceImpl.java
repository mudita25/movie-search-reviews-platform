package com.mudita.movies.service.impl;

import com.mudita.movies.entity.Movie;
import com.mudita.movies.entity.MovieShow;
import com.mudita.movies.entity.Theatre;
import com.mudita.movies.http.entity.HttpMovieShow;
import com.mudita.movies.http.entity.HttpShowsByMovie;
import com.mudita.movies.http.entity.HttpShowsByTheatre;
import com.mudita.movies.http.utils.StringUtils;
import com.mudita.movies.repository.MovieShowsRepository;
import com.mudita.movies.service.MovieService;
import com.mudita.movies.service.MovieShowsService;
import com.mudita.movies.service.TheatreService;
import com.mudita.movies.service.exception.ErrorCode;
import com.mudita.movies.service.exception.InvalidInputException;
import com.mudita.movies.service.exception.MovieException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class MovieShowsServiceImpl implements MovieShowsService {

    @Autowired
    MovieService movieService;

    @Autowired
    TheatreService theatreService;

	@Autowired
	MovieShowsRepository movieShowsRepository;

    @Override
    public HttpShowsByMovie getMovieShowsByMovie(String movieName, int limit, int offset) {
        if (StringUtils.isNullOrEmpty(movieName)) {
            throw new InvalidInputException("Movie name is required");
        }

        Movie movie = movieService.getMovieByName(movieName);
        if (movie == null) {
            throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
        }

        List<MovieShow> shows = movieShowsRepository.getAllShowsForMovie(movie.getMovieId());
        if (shows == null || shows.isEmpty()) {
            throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
        }

        Map<Long, List<MovieShow>> showPerTheatreMap = new HashMap<>();
        for (MovieShow movieShow : shows) {
            if (!showPerTheatreMap.containsKey(movieShow.getTheatreId())) {
                showPerTheatreMap.put(movieShow.getTheatreId(), new ArrayList<>());
            }
            showPerTheatreMap.get(movieShow.getTheatreId()).add(movieShow);
        }

        List<Theatre> theatres = theatreService.getTheatreById(showPerTheatreMap.keySet());
        if (theatres == null || theatres.isEmpty()) {
            throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
        }
        HttpShowsByMovie httpShowsByMovie = new HttpShowsByMovie(movie);
        for (Theatre theatre : theatres) {
            List<MovieShow> showsList = showPerTheatreMap.get(theatre.getTheatreId());
            HttpShowsByTheatre showsByTheatre = new HttpShowsByTheatre(theatre);
            for(MovieShow show : showsList){
                showsByTheatre.addMovieShows(new HttpMovieShow(show));
            }
            httpShowsByMovie.addShows(showsByTheatre);
        }

        return httpShowsByMovie;
    }

    @Override
    public HttpShowsByTheatre getMovieShowsByTheatre(String theatreName, int limit, int offset){
        if(StringUtils.isNullOrEmpty(theatreName)){
            throw new MovieException(ErrorCode.INVALID_INPUT, "Theatre name is required");
        }
        Theatre theatre = theatreService.getTheatreByName(theatreName);
        if(theatre == null){
            throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
        }

        List<MovieShow> shows = movieShowsRepository.getAllShowsInTheatre(theatre.getTheatreId());
        if(shows == null || shows.isEmpty()){
            throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
        }

        Map<Long, List<MovieShow>> showPerMovieMap = new HashMap<>();
        for(MovieShow movieShow : shows){
            if(!showPerMovieMap.containsKey(movieShow.getMovieId())){
                showPerMovieMap.put(movieShow.getMovieId(), new ArrayList<>());
            }
            showPerMovieMap.get(movieShow.getMovieId()).add(movieShow);
        }

        List<Movie> movies = movieService.getMoviesById(showPerMovieMap.keySet());

        if(movies == null || movies.isEmpty()){
            throw new MovieException(ErrorCode.MISSING_DATA, "Data not found");
        }

        HttpShowsByTheatre httpShowsByTheatre = new HttpShowsByTheatre(theatre);
        for(Movie movie : movies){
            List<MovieShow> showsList = showPerMovieMap.get(movie.getMovieId());
            HttpShowsByMovie httpShowsByMovie = new HttpShowsByMovie(movie);
            for(MovieShow show : showsList){
                httpShowsByMovie.addShows(new HttpMovieShow(show));
            }
            httpShowsByTheatre.addMovieShowsByMovies(httpShowsByMovie);
        }
        return httpShowsByTheatre;
    }
}
