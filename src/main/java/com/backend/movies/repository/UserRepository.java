package com.backend.movies.repository;

import com.backend.movies.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * This class serves as the Repository for Users
 *
 * Created on 06/18/2021
 * Created by Kobe Kyle Relativo
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM users where api_key = :apiKey", nativeQuery = true)
    User findByApiKey(final String apiKey);
}
