package com.mudita.movies.entity.impl;

import com.mudita.movies.entity.Theatre;
import org.springframework.stereotype.Service;

import java.util.Date;
import javax.persistence.*;

@Service
@Entity
@Table(name="theatres")
public class TheatreImpl implements Theatre{
	@Id
	@Column(name="theatre_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long theatreId;

	@Column(name="name")
	private String theatreName;

	@Column(name="address")
	private String address;

	@Column(name="city")
	private String city;

	@Column(name="state")
	private String state;

	@Column(name="zip")
	private Integer zip;

	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="web_url")
	private String websiteUrl;

    @Column(name="amenities")
    private String amenities;

	@Column(name="number_of_screens")
	private Integer screens;

	@Column(name="created_at")
	private Date createdAt;

	public Long getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public String getAddressLine() {
		return address;
	}

	public void setAddressLine(String addressLine) {
		this.address = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public Integer getScreens() {
		return screens;
	}

	public void setScreens(Integer screens) {
		this.screens = screens;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
