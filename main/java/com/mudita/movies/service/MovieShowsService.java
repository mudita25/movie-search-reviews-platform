package com.mudita.movies.service;

import com.mudita.movies.entity.MovieShow;
import com.mudita.movies.http.entity.HttpShowsByMovie;
import com.mudita.movies.http.entity.HttpShowsByTheatre;

import java.util.List;

public interface MovieShowsService {
    HttpShowsByMovie getMovieShowsByMovie(String movieName, int limit, int offset);
    HttpShowsByTheatre getMovieShowsByTheatre(String theatreName, int limit, int offset);
}
