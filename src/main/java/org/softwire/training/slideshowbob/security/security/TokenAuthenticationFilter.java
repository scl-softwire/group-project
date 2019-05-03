package org.softwire.training.slideshowbob.security.security;

import org.softwire.training.slideshowbob.security.SlideShowBobUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    SlideShowBobUserDetailsService usersService;

    private String getToken(HttpServletRequest request) {


        // get token from a Cookie
        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length < 1) {
            throw new AuthenticationServiceException("Invalid Token");
        }

        Cookie sessionCookie = null;
        for (Cookie cookie : cookies) {
            if (("Authorisation").equalsIgnoreCase(cookie.getName())) {
                sessionCookie = cookie;
                break;
            }
        }

        if (sessionCookie == null || sessionCookie.getValue().isEmpty()) {
            throw new AuthenticationServiceException("Invalid Token");
        }

        return sessionCookie.getValue();
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authToken = getToken(request);

        try {
            // Get username from token
            String username = tokenHelper.getUsernameFromToken(authToken);
            if (username != null) {

                // Get user
                UserDetails userDetails = usersService.loadUserByUsername(username);

                // Create authentication
                TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                authentication.setToken(authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
                System.out.println(e);
                SecurityContextHolder.getContext().setAuthentication(new AnonAuthentication());//prevent show login form...
        }
        chain.doFilter(request, response);
    }

}
