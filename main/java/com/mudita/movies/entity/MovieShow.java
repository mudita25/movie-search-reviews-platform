package com.mudita.movies.entity;

import java.util.Date;

public interface MovieShow {
     Long getShowId();
     Long getMovieId();
     Long getTheatreId();
     Date getDateTime();
     String getShowType();
     Double getShowPrice();
     Integer getAvailability();
     String getInfo1();
     String getInfo2();
     Date getCreatedAt();
}
