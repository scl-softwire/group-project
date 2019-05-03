package org.softwire.training.slideshowbob.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        if (!(authentication instanceof JWTAuthenticationToken)) {
            return;
        }

        JWTAuthenticationToken jwtAuthenticaton = (JWTAuthenticationToken) authentication;

        // Add a session cookie
        Cookie sessionCookie = new Cookie("someSessionId", jwtAuthenticaton.getToken());
        response.addCookie(sessionCookie);

        //clearAuthenticationAttributes(request);

        // call the original impl
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

