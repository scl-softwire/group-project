package org.softwire.training.slideshowbob.security.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        setUseReferer(true);
        String referer = (String) request.getSession().getAttribute("url_prior_login");
        request.getSession().removeAttribute("url_prior_login");
        getRedirectStrategy().sendRedirect(request,response,referer);
    }
}

