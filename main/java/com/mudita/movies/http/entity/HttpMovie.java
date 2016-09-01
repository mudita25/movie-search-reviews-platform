package com.mudita.movies.http.entity;

import com.mudita.movies.entity.Movie;
import com.mudita.movies.imdb.HttpIMDB;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "movie")
public class HttpMovie {

    @XmlElement
    public Long movieId;

    @XmlElement
    public String title;

    @XmlElement
    public long year;

    @XmlElement
    public String rated;

    @XmlElement
    public String releaseDate;

    @XmlElement
    public String runtime;

    @XmlElement
    public String genre;

    @XmlElement
    public String director;

    @XmlElement
    public String starcast;

    @XmlElement
    public String moviePlot;

    @XmlElement
    public String language;

    @XmlElement
    public List<String> tweets;

    @XmlElement
    public String imdbRating;

    @XmlElement
    public String tomatoes;

    //required by framework
    protected HttpMovie() {
    }

    public HttpMovie(Movie movie, List<String> tweets, HttpIMDB imdb) {
        this.movieId = movie.getMovieId();
        this.title = movie.getMovieTitle();
        this.year = movie.getReleaseDate().getYear() + 1900;
        this.rated = movie.getRated();
        this.releaseDate = new SimpleDateFormat("yyyy-MM-dd").format(movie.getReleaseDate());
        this.runtime = movie.getRuntime();
        this.genre = movie.getGenre();
        this.director = movie.getDirector();
        this.starcast = movie.getCast1() + "  ,  " + movie.getCast2() + " ,  " +movie.getCast3();
        this.moviePlot = movie.getMoviePlot();
        this.language = movie.getLanguage();
        this.tweets = tweets;
        this.imdbRating = imdb.getImdbRating();
        this.tomatoes = imdb.getRottenTometoes();
    }
}
