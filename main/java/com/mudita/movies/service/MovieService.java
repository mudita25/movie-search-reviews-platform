package com.mudita.movies.service;

import com.mudita.movies.entity.Movie;

import java.util.List;
import java.util.Set;

public interface MovieService {
    Movie getMovieByName(String movieTitle);
    List<Movie> getMoviesById(Set<Long> movieIds);
    List<Movie> getAllMoviesInSystem(int limit, int offset);
    List<Movie> getAllUpcomingMoviesInSystem(boolean isMovieReleased, int limit, int offset);
    List<Movie> getMoviesByParameters(String name, String year, String genre, int limit, int offset);
}
