package com.backend.movies.utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

/**
 * This class contains utilities for the entire project
 *
 * Created on 06/18/2021
 * Created by Kobe Kyle Relativo
 */
@Service
public class IdGenerator {

    /**
     * This method is used to generate random String
     * @param count number of characters
     * @return Random String
     */
    public static String generate(final int count){
        return RandomStringUtils.random(count, true, true);
    }
}
