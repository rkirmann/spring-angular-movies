package com.movies.test.repository;

import com.movies.test.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    void deleteByImdbID(String imdbId);
}
