package org.softwire.training.slideshowbob.services.auth;

import org.softwire.training.slideshowbob.AuthenticationException;
import org.softwire.training.slideshowbob.security.jwt.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class AuthService {

    private final TokenAuthenticationService tokenAuthenticationService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(TokenAuthenticationService tokenAuthenticationService, AuthenticationManager authenticationManager) {
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.authenticationManager = authenticationManager;
    }

    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return tokenAuthenticationService.addAuthentication(username);
        } catch (org.springframework.security.core.AuthenticationException e) {
            throw new AuthenticationException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);

        }
    }


    public boolean validate(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies())
            if (cookie.getName().equalsIgnoreCase("token"))
                return tokenAuthenticationService.validateToken(request.getCookies()[0].getValue());
        return false;
    }

    public String refresh(String username) {
        return tokenAuthenticationService.addAuthentication(username);
    }
}
