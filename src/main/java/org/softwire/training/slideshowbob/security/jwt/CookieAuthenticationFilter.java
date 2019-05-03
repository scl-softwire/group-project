package org.softwire.training.slideshowbob.security.jwt;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public CookieAuthenticationFilter(RequestMatcher requestMatcher) {
        super(requestMatcher);
        setAuthenticationManager(super.getAuthenticationManager());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String token = "";

        // get token from a Cookie
        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length < 1) {
            throw new AuthenticationServiceException("Invalid Token");
        }

        Cookie sessionCookie = null;
        for (Cookie cookie : cookies) {
            if (("Authorisation").equals(cookie.getName())) {
                sessionCookie = cookie;
                break;
            }
        }

        validateCookieValue(sessionCookie);

        JWTAuthenticationToken jwtAuthentication = new JWTAuthenticationToken(sessionCookie.getValue(), null, null);

        return jwtAuthentication;

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }

    private void validateCookieValue(Cookie sessionCookie) {
        if (sessionCookie == null || StringUtils.isEmpty(sessionCookie.getValue())) {
            throw new AuthenticationServiceException("Invalid Token");
        }
    }
}
