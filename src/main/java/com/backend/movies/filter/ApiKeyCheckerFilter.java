package com.backend.movies.filter;

import com.backend.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter to check API keys
 *
 * Created by Kobe Kyle Relativo
 * Created on 06/21/2021
 */
@Component
public class ApiKeyCheckerFilter implements Filter {

    private static final String INVALID_API_KEY = "INVALID_API_KEY";
    private static final String EMPTY_API_KEY = "EMPTY_API_KEY";
    private static final String API_KEY = "apiKey";
    private static final String API_USERS_ADD = "/api/users/add";

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            final String requestUri = httpServletRequest.getRequestURI();
            if(!API_USERS_ADD.equals(requestUri)){
                String apiKey = request.getParameter(API_KEY);
                if(ObjectUtils.isEmpty(apiKey)){
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    httpResponse.getWriter().write(EMPTY_API_KEY);
                    return;
                }
                else if(!this.userService.checkUserApiKey(apiKey)){
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    httpResponse.getWriter().write(INVALID_API_KEY);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }
}
