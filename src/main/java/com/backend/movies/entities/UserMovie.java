package com.backend.movies.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity for user_movie table
 * @author Kobe Kyle Relativo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_movie", uniqueConstraints=
@UniqueConstraint(columnNames = {"user_id", "movie_id"}))
@IdClass(UserMovieCompositeKey.class)
public class UserMovie {
    @Id
    @Column(name = "user_id")
    private String user_id;
    @Id
    @Column(name = "movie_id")
    private String movie_id;
}
