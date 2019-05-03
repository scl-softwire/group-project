package org.softwire.training.slideshowbob.security.jwt;

import org.softwire.training.slideshowbob.security.UserPrincipal;
import org.softwire.training.slideshowbob.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UsernamePasswordAuthenticationToken implements AuthenticationProvider {

    private final UsersService usersService;
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    public UsernamePasswordAuthenticationToken(UsersService usersService, TokenAuthenticationService tokenAuthenticationService) {
        this.usersService = usersService;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        // perform API call to auth against a 3rd party TODO db verify


        // get User data
        UserPrincipal user = new UserPrincipal(usersService.getUserByUsername(login));

        // create a JWT token
        String jwtToken = "some-token-123";

        return new JWTAuthenticationToken(jwtToken, user, new ArrayList<>());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
