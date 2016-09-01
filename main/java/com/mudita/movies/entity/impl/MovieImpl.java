package com.mudita.movies.entity.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.mudita.movies.entity.Movie;
import org.springframework.stereotype.Service;
import javax.persistence.*;

@Service
@Entity
@Table(name="movies")
public class MovieImpl implements Movie {
	@Id
	@Column(name="movie_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long movieId;

	@Column(name="title")
	private String movieTitle;

	@Column(name="rated")
	private String rated;

	@Column(name="genre")
	private String genre;

	@Column(name="runtime")
	private String runtime;

	@Column(name="director")
	private String director;

	@Column(name="cast1")
	private String cast1;

	@Column(name="cast2")
	private String cast2;

	@Column(name="cast3")
	private String cast3;

	@Column(name="description")
	private String moviePlot;

	@Column(name="release_date")
	private Date releaseDate;

	@Column(name="language")
	private String language;

	@Column(name="created_at")
	private Date createdAt;

	//private Integer year;

//	public Integer getYear() {
//		return year;
//	}

//    public void setYear(Date releaseDate) {
//        this.year = releaseDate.getYear() + 1900;;
//    }

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getMoviePlot() {
		return moviePlot;
	}

	public void setMoviePlot(String moviePlot) {
		this.moviePlot = moviePlot;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getCast1() {
		return cast1;
	}

	public void setCast1(String cast1) {
		this.cast1 = cast1;
	}

	public String getCast2() {
		return cast2;
	}

	public void setCast2(String cast2) {
		this.cast2 = cast2;
	}

	public String getCast3() {
		return cast3;
	}

	public void setCast3(String cast3) {
		this.cast3 = cast3;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

//	public List<MovieShowImpl> getShows() {
//		return shows;
//	}
//
//	public void setShows(List<MovieShowImpl> shows) {
//		this.shows = shows;
//	}
}
