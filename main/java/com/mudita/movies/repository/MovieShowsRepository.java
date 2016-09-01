package com.mudita.movies.repository;

import com.mudita.movies.entity.MovieShow;
import java.util.List;

public interface MovieShowsRepository {
    List<MovieShow> getAllShowsForMovie(Long movieId);
    List<MovieShow> getAllShowsInTheatre(Long theatreId);
}
