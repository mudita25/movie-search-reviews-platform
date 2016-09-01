package com.mudita.movies.http.entity;

import com.mudita.movies.entity.Theatre;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "theatre")
public class HttpTheatre {

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
    public String website;

    @XmlElement
    public String amenities;

    @XmlElement
    public Integer screens;

    //required by framework
    protected HttpTheatre() {
    }

    public HttpTheatre(Theatre theatre) {
        this.theatreId = theatre.getTheatreId();
        this.name = theatre.getTheatreName();
        this.address = theatre.getAddressLine();
        this.city = theatre.getCity();
        this.state = theatre.getState();
        this.zip = theatre.getZip();
        this.phoneNumber = theatre.getPhoneNumber();
        this.website = theatre.getWebsiteUrl();
        this.amenities = theatre.getAmenities();
        this.screens = theatre.getScreens();
    }
}
