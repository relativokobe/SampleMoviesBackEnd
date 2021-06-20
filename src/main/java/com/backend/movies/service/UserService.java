package com.backend.movies.service;

import com.backend.movies.entities.User;
import com.backend.movies.repository.UserRepository;
import com.backend.movies.utilities.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * This class serves as the Service for Users
 *
 * Created on 06/18/2021
 * Created by Kobe Kyle Relativo
 */
@Service
public class UserService {
    private static final int ID_LENGTH = 10;
    private static final int API_KEY_LENGTH = 15;

    @Autowired
    private UserRepository userRepository;

    /**
     * Method to add user
     * @param user user object to be added
     * @return boolean value if user is successfully added or not
     */
    public boolean addUser(final User user){
        if(user == null){
            return false;
        }

        user.setId(IdGenerator.generate(ID_LENGTH));
        user.setApiKey(IdGenerator.generate(API_KEY_LENGTH));
        userRepository.save(user);
        return true;
    }

    /**
     * Method to check if user's API key exists
     * @param apiKey api key to be validated
     * @return return boolean result of the validation
     */
    public boolean checkUserApiKey(final String apiKey){
        return !ObjectUtils.isEmpty(apiKey) && !ObjectUtils.isEmpty(this.userRepository.findByApiKey(apiKey));
    }
}
