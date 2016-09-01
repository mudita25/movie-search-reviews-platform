package com.mudita.movies.http.entity;

import com.mudita.movies.entity.MovieShow;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlRootElement(name = "movie")
public class HttpMovieShow {

    @XmlElement
    public Long showId;

    @XmlElement
    public String date;

    @XmlElement
    public String type;

    @XmlElement
    public String price;

    @XmlElement
    public Integer availability;

    @XmlElement
    public String features;

    //required by framework
    protected HttpMovieShow() {
    }

    public HttpMovieShow(MovieShow shows) {
        this.showId = shows.getShowId();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        this.date = format.format(shows.getDateTime());
        this.type = shows.getShowType();
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        this.price = currencyFormatter.format(shows.getShowPrice());
        this.availability = shows.getAvailability();
        this.features = shows.getInfo1() + ", " + shows.getInfo2();
    }
}
