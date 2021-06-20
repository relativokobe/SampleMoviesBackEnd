package com.backend.movies.repository;

import com.backend.movies.entities.Movie;
import com.backend.movies.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class serves as the Repository for Movies
 *
 * Created on 06/18/2021
 * Created by Kobe Kyle Relativo
 */
@Repository
public interface MoviesRepository extends JpaRepository<Movie, String> {
    @Query(value = "SELECT id, name, description, director FROM movies WHERE name LIKE %:search% " +
            "OR description LIKE %:search% OR director LIKE %:search%", nativeQuery = true)
    List<Movie> getMoviesBasedOnSearchQuery(final String search);

    List<Movie> findByUsers(final User user);
}
