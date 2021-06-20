package com.backend.movies.entities;

import java.io.Serializable;

/**
 * Composite Key for many to many
 */
public class UserMovieCompositeKey implements Serializable {
    private String user_id;
    private String movie_id;
}
