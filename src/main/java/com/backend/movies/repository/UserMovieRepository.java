package com.backend.movies.repository;

import com.backend.movies.entities.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This class serves as the Repository for UserMovie
 *
 * Created on 06/18/2021
 * Created by Kobe Kyle Relativo
 */
@Repository
public interface UserMovieRepository extends JpaRepository<UserMovie, String> {
}
