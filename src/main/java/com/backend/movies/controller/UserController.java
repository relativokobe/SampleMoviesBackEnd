package com.backend.movies.controller;

import com.backend.movies.entities.User;
import com.backend.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * This class serves as the Controller for Users
 *
 * Created on 06/18/2021
 * Created by Kobe Kyle Relativo
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Method to add movie
     * @param response response to be sent to client
     */
    @PostMapping("/users/add")
    public void addUser(final @RequestBody User user, final HttpServletResponse response){
        boolean success = this.userService.addUser(user);
        final int responseStatus = success ? HttpServletResponse.SC_OK : HttpServletResponse.SC_EXPECTATION_FAILED;
        response.setStatus(responseStatus);
    }
}
