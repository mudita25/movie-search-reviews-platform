package com.mudita.movies.entity;

import java.util.Date;

public interface Movie {
    Long getMovieId();
    String getMovieTitle();
    String getRated();
    String getGenre();
    String getRuntime();
    String getDirector();
    String getCast1();
    String getCast2();
    String getCast3();
    String getMoviePlot();
    Date getReleaseDate();
    String getLanguage();
    Date getCreatedAt();
}
