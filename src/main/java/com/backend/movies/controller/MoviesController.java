package com.backend.movies.controller;

import com.backend.movies.entities.Movie;
import com.backend.movies.service.MoviesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This class serves as the Controller for Movies
 *
 * Created on 06/18/2021
 * Created by Kobe Kyle Relativo
 */
@RestController
@RequestMapping("/api")
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    /**
     * Method to return movie using ID
     * @param id of the movie
     * @param response response to be sent to client
     */
    @GetMapping("/movies/{id}")
    public void getMovieUsingMovieId(final @PathVariable String id,
                                     final HttpServletResponse response) throws IOException {
        final Gson gson = new GsonBuilder().serializeNulls().create();
        final String stringResponse;
        final int responseStatus;
        final Movie movie = this.moviesService.getMovieUsingMovieId(id);
        stringResponse = gson.toJson(movie);
        responseStatus = HttpServletResponse.SC_OK;

        response.setStatus(responseStatus);
        response.getWriter().write(stringResponse);
    }

    /**
     * Method to add a favorite movie using the movie's ID
     *
     * @param userId id of the user adding the movie as favorite
     * @param id id of the movie
     * @param response response to be sent to client
     */
    @PostMapping("/favorites/{id}")
    public void addAFavoriteMovieUsingId(final @RequestParam String userId, final @PathVariable String id,
                                         final HttpServletResponse response){
        final boolean success = this.moviesService.addAFavoriteMovieUsingId(userId, id);
        final int responseStatus = success ? HttpServletResponse.SC_OK : HttpServletResponse.SC_EXPECTATION_FAILED;
        response.setStatus(responseStatus);
    }

    /**
     * Method to get the favorite movies
     * @param response response to be sent to client
     */
    @GetMapping("/favorites")
    public void getFavoriteMovies(final @RequestParam String userId, final HttpServletResponse response) throws IOException {
        final Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
        final List<Movie> movies = this.moviesService.getFavoriteMovies(userId);
        final String stringResponse = gson.toJson(movies);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(stringResponse);
    }

    /**
     * Method to get movies based on search query
     * @param search search query to be based on when retrieving movies
     * @param response response to be sent to client
     */
    @GetMapping("/movies")
    public void getMoviesBasedOnSearchQuery(final @RequestParam String search,
                                            final HttpServletResponse response) throws IOException {
        final Gson gson = new GsonBuilder().serializeNulls().create();
        final String stringResponse;
        final int responseStatus;
        final List<Movie> movies = this.moviesService.getMoviesBasedOnSearchQuery(search);
        stringResponse = gson.toJson(movies);
        responseStatus = HttpServletResponse.SC_OK;

        response.setStatus(responseStatus);
        response.getWriter().write(stringResponse);
    }

    /**
     * Method to add movie
     * @param response response to be sent to client
     */
    @PostMapping("/movies/add")
    public void addMovie(final @RequestBody Movie movie, final HttpServletResponse response){
        final boolean success = this.moviesService.addMovie(movie);
        final int responseStatus = success ? HttpServletResponse.SC_OK : HttpServletResponse.SC_EXPECTATION_FAILED;
        response.setStatus(responseStatus);
    }
}
