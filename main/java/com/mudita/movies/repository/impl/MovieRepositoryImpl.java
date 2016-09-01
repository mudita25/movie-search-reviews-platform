package com.mudita.movies.repository.impl;

import com.mudita.movies.entity.Movie;
import com.mudita.movies.entity.MovieShow;
import com.mudita.movies.entity.impl.MovieImpl;
import com.mudita.movies.http.utils.StringUtils;
import com.mudita.movies.repository.MovieRepository;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public class MovieRepositoryImpl implements MovieRepository{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Movie> getMovieById(Set<Long> movieIds){
        Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieImpl.class).
                        add(Restrictions.in("movieId", movieIds));
        List<Movie> movies = crit.list();

        if(movies == null || movies.isEmpty()){
            return null;
        }
        return movies;
    }

    @Override
    public List<Movie> getAllMovies(int limit, int offset) {
        Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieImpl.class);
        crit.setFirstResult((offset)*limit);
        crit.setMaxResults(limit);
        List<Movie> movie = crit.list();
        return movie;
    }

    @Override
    public List<Movie> getAllUpcomingMovies(boolean isMovieReleased, int limit, int offset){
        Query query;
        if(isMovieReleased){
             query = this.sessionFactory.getCurrentSession().createQuery
                          ("FROM MovieImpl s where s.releaseDate <= :release_date ");
        }
        else{
             query = this.sessionFactory.getCurrentSession().createQuery
                          ("FROM MovieImpl s where s.releaseDate > :release_date ");
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        query.setParameter("release_date", date);
        List<Movie> movies = query.list();
        return movies;
    }

    @Override
    public List<Movie> getMoviesByParameters(String name, String year, String genre, int limit, int offset) {
        Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieImpl.class);
        if(!StringUtils.isNullOrEmpty(name)){
            crit.add(Restrictions.like("movieTitle", "%" + name + "%"));
        }
        if(!StringUtils.isNullOrEmpty(year)){
            crit.add(Restrictions.eq("year", Integer.parseInt(year)));
        }
        if(!StringUtils.isNullOrEmpty(genre)){
            crit.add(Restrictions.like("genre", "%" + genre + "%"));
        }
        crit.setFirstResult((offset)*limit);
        crit.setMaxResults(limit);
        List<Movie> movie = crit.list();
        return movie;
    }

    @Override
    public Movie getMovieByName(String movieTitle) {
        Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MovieImpl.class).add(Restrictions.like("movieTitle", "%" + movieTitle + "%"));
        List<Movie> movies = crit.list();
        if(movies == null || movies.isEmpty()){
            return null;
        }
        return movies.iterator().next();
    }
}
