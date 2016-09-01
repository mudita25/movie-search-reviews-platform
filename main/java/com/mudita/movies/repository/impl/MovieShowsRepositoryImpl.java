package com.mudita.movies.repository.impl;

import com.mudita.movies.entity.MovieShow;
import com.mudita.movies.repository.MovieRepository;
import com.mudita.movies.repository.MovieShowsRepository;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieShowsRepositoryImpl implements MovieShowsRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<MovieShow> getAllShowsForMovie(Long movieId){
        Query query = this.sessionFactory.getCurrentSession().getNamedQuery("findShowsByMovieId");
        query.setParameter("movie_id", movieId);
        List<MovieShow> shows = query.list();
        return shows;
    }

    @Override
    public List<MovieShow> getAllShowsInTheatre(Long theatreId){
        Query query = this.sessionFactory.getCurrentSession().getNamedQuery("findShowsByTheatreId");
        query.setParameter("theatre_id",theatreId);
        List<MovieShow> shows = query.list();
        return shows;
    }
}