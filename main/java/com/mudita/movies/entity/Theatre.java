package com.mudita.movies.entity;

import java.util.Date;

public interface Theatre {
     Long getTheatreId();
     String getTheatreName();
     String getAddressLine();
     String getCity();
     String getState();
     Integer getZip();
     String getPhoneNumber();
     String getWebsiteUrl();
     String getAmenities();
     Integer getScreens();
     Date getCreatedAt();
}