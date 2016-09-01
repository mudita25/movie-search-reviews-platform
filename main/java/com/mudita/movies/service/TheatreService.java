package com.mudita.movies.service;

import com.mudita.movies.entity.Theatre;

import java.util.List;
import java.util.Set;

public interface TheatreService {
    Theatre getTheatreByName(String theatreName);
    List<Theatre> getTheatreById(Set<Long> TheatreIds);
    List<Theatre> getAllTheatresInSystem(int limit, int offset);
    List<Theatre> getTheatresByParameters(String name, String zip, String place, int limit, int offset);

}
