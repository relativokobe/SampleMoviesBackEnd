package com.backend.movies.service;

import com.backend.movies.entities.Movie;
import com.backend.movies.entities.User;
import com.backend.movies.entities.UserMovie;
import com.backend.movies.repository.MoviesRepository;
import com.backend.movies.repository.UserMovieRepository;
import com.backend.movies.utilities.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * This class serves as the Service for Movies
 *
 * Created on 06/18/2021
 * Created by Kobe Kyle Relativo
 */
@Service
public class MoviesService {
    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private UserMovieRepository userMovieRepository;

    private static final int MOVIE_ID_COUNT = 10;

    /**
     * Method to call repository and retrieve movies based on search
     * @param search query to based on when retrieving movies
     * @return List of movies retrieved
     */
    public List<Movie> getMoviesBasedOnSearchQuery(final String search){
        return !ObjectUtils.isEmpty(search) ? this.moviesRepository.getMoviesBasedOnSearchQuery(search) :
             Collections.emptyList();
    }

    /**
     * Method to add movie
     * @param movie to be added
     * @return boolean result of the operation
     */
    public boolean addMovie(final Movie movie){
        if(movie == null){
            return false;
        }
        movie.setId(IdGenerator.generate(MOVIE_ID_COUNT));
        this.moviesRepository.save(movie);
        return true;
    }

    /**
     * Method to return movie using the Movie id
     * @param id to be used to retrieve Movie
     * @return Movie retrieved from DB
     */
    public Movie getMovieUsingMovieId(final String id){
        Movie movie = null;
        if(!ObjectUtils.isEmpty(id)){
            Optional<Movie> movieOptional =  this.moviesRepository.findById(id);
            if(movieOptional.isPresent()){
                movie = movieOptional.get();
            }
        }
        return movie;
    }

    /**
     * Method to add a favorite movie using the movie's ID
     *
     * @param userId user adding the movie as favorite
     * @param id the movie's id to be added
     * @return boolean result of the operation
     */
    public boolean addAFavoriteMovieUsingId(final String userId, final String id){
        if(!ObjectUtils.isEmpty(userId) && !ObjectUtils.isEmpty(id)){
            UserMovie userMovie = new UserMovie(userId, id);
            this.userMovieRepository.save(userMovie);
            return true;
        }
        return false;
    }

    /**
     * Method to retrieve user's favorite movies
     * @param userId user's id
     * @return user's favorite movies
     */
    public List<Movie> getFavoriteMovies(final String userId){
        List<Movie> movies = new ArrayList<>();
        if(!ObjectUtils.isEmpty(userId)){
            final User user = new User();
            user.setId(userId);
            movies = this.moviesRepository.findByUsers(user);
        }
        return movies;
    }
}
