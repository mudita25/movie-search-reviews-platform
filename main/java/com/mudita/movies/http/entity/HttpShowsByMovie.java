package com.mudita.movies.http.entity;

import com.mudita.movies.entity.Movie;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "showsByMovie")
public class HttpShowsByMovie {

    @XmlElement
    public Long movieId;

    @XmlElement
    public String title;

    @XmlElement
    public Integer year;

    @XmlElement
    public String rated;

    @XmlElement
    public String runtime;

    @XmlElement
    public String genre;

    @XmlElement
    private List<HttpMovieShow> shows = new ArrayList<>();

    @XmlElement
    private List<HttpShowsByTheatre> showsByTheatre = new ArrayList<>();

    //required by framework
    protected HttpShowsByMovie() {
    }

    public HttpShowsByMovie(Movie movie) {
        this.movieId = movie.getMovieId();
        this.title = movie.getMovieTitle();
        this.year = movie.getReleaseDate().getYear() + 1900;
        this.rated = movie.getRated();
        this.runtime = movie.getRuntime();
        this.genre = movie.getGenre();
    }

    public void addShows(HttpMovieShow show) {
        this.shows.add(show);
    }

    public void addShows(HttpShowsByTheatre showsByTheatre) {
        this.showsByTheatre.add(showsByTheatre);
    }
}
