package com.mudita.movies.entity.impl;

import java.util.Date;
import javax.persistence.*;
import com.mudita.movies.entity.MovieShow;
import org.springframework.stereotype.Service;

@NamedNativeQueries({
		@NamedNativeQuery(
				name = "findShowsByMovieId",
				query = "select s.* FROM shows s inner join movies m on m.movie_id=s.movie_id where s.movie_id = :movie_id",
				resultClass = MovieShowImpl.class
		),
		@NamedNativeQuery(
				name = "findShowsByTheatreId",
				query = "select s.* FROM shows s inner join theatres t on t.theatre_id=s.theatre_id where s.theatre_id = :theatre_id",
				resultClass = MovieShowImpl.class
		)
})
@Service
@Entity
@Table(name="shows")
public class MovieShowImpl implements MovieShow {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long showId;

	@Column(name="movie_id")
    private Long movieId;

	@Column(name="theatre_id")
    private Long theatreId;

	@Column(name="time")
    private Date dateTime;

	@Column(name="type")
    private String showType;

	@Column(name="price")
    private Double showPrice;

	@Column(name="availability")
    private Integer availability;

	@Column(name="additional_text1")
	private String info1;

	@Column(name="additional_text2")
	private String info2;

	@Column(name="created_at")
    private Date createdAt;

	@ManyToOne(targetEntity=MovieImpl.class)
	@JoinColumn(name="movie_id", insertable = false, updatable = false)
	private MovieImpl movie;

	@ManyToOne(targetEntity=TheatreImpl.class)
	@JoinColumn(name = "theatre_id", insertable = false,updatable = false)
	private TheatreImpl theatre;

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public Long getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public Double getShowPrice() {
		return showPrice;
	}

	public void setShowPrice(Double showPrice) {
		this.showPrice = showPrice;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public MovieImpl getMovie() {
		return movie;
	}

	public void setMovie(MovieImpl movie) {
		this.movie = movie;
	}

	public TheatreImpl getTheatre() {
		return theatre;
	}

	public void setTheatre(TheatreImpl theatre) {
		this.theatre = theatre;
	}
}
