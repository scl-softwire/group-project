package org.softwire.training.slideshowbob.security.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.softwire.training.slideshowbob.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private int EXPIRES_IN = 1000 * 60 * 60 * 24 * 3; // 3 days

    @Autowired
    TokenHelper tokenHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        clearAuthenticationAttributes(request);
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        String jwt = tokenHelper.generateToken(user.getUsername());

        UserTokenState userTokenState = new UserTokenState(jwt, EXPIRES_IN);

        try {
            String jwtResponse = userTokenState.jws;
            response.addCookie(new Cookie("Authorisation", jwtResponse));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private class UserTokenState {
        private String jws;
        private int expires;

        public UserTokenState(String jws, int expires) {
            this.jws = jws;
            this.expires = expires;
        }

        public String getJws() {
            return jws;
        }

        public void setJws(String jws) {
            this.jws = jws;
        }

        public int getExpires() {
            return expires;
        }

        public void setExpires(int expire) {
            this.expires = expire;
        }
    }
}

