package org.softwire.training.slideshowbob.services;

import org.softwire.training.slideshowbob.AuthenticationException;
import org.softwire.training.slideshowbob.security.UserPrincipal;
import org.softwire.training.slideshowbob.security.security.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private TokenHelper tokenHelper;

    @Autowired
    public AuthService(TokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }



    public String login(UserPrincipal user) {

        try {
            return tokenHelper.generateToken(user.getUsername());
        } catch (org.springframework.security.core.AuthenticationException e) {
            throw new AuthenticationException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);

        }
    }


}
