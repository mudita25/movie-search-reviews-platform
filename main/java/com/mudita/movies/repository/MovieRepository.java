package com.mudita.movies.repository;

import com.mudita.movies.entity.Movie;
import java.util.List;
import java.util.Set;

public interface MovieRepository {
     Movie getMovieByName(String movieTitle);
     List<Movie> getMovieById( Set<Long> movieIds);
     List<Movie> getAllMovies(int limit, int offset);
    List<Movie> getAllUpcomingMovies(boolean isMovieReleased, int limit, int offset);
     List<Movie> getMoviesByParameters(String name, String year, String genre, int limit, int offset);
}
