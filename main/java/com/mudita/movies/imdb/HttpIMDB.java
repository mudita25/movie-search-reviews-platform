package com.mudita.movies.imdb;

public class HttpIMDB {
    private String imdbRating;

    private String rottenTometoes;

    public HttpIMDB(String imdbRating, String rottenTometoes){
        this.imdbRating = imdbRating;
        this.rottenTometoes = rottenTometoes;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getRottenTometoes() {
        return rottenTometoes;
    }
}
