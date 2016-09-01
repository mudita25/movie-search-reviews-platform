package com.mudita.movies.repository;

import com.mudita.movies.entity.Theatre;

import java.util.List;
import java.util.Set;

public interface TheatreRepository {
     Theatre getTheatreByName(String theatreName);
     List<Theatre> getTheatreById(Set<Long> theatreIds);
     List<Theatre> getAllTheatres(int limit, int offset);
     List<Theatre> getTheatresByParameters(String name, int zip, String place, int limit, int offset);
}
