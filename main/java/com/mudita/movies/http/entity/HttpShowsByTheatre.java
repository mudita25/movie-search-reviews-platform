package com.mudita.movies.http.entity;

import com.mudita.movies.entity.Theatre;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "showsByTheatre")
public class HttpShowsByTheatre {

    @XmlElement
    public Long theatreId;

    @XmlElement
    public String name;

    @XmlElement
    public String address;

    @XmlElement
    public String city;

    @XmlElement
    public String state;

    @XmlElement
    public Integer zip;

    @XmlElement
    public String phoneNumber;

    @XmlElement
    public List<HttpShowsByMovie> showsByMovie = new ArrayList<>();

    @XmlElement
    public List<HttpMovieShow> movieShows = new ArrayList<>();

    //required by framework
    protected HttpShowsByTheatre() {
    }

    public HttpShowsByTheatre(Theatre theatre) {
        this.theatreId = theatre.getTheatreId();
        this.name = theatre.getTheatreName();
        this.address = theatre.getAddressLine();
        this.city = theatre.getCity();
        this.state = theatre.getState();
        this.zip = theatre.getZip();
        this.phoneNumber = theatre.getPhoneNumber();
    }

    public void addMovieShowsByMovies(HttpShowsByMovie movie) {
        this.showsByMovie.add(movie);
    }

    public void addMovieShows(HttpMovieShow movieShow) {
        this.movieShows.add(movieShow);
    }
}
