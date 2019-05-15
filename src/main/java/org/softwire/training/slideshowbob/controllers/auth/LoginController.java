package org.softwire.training.slideshowbob.controllers.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginView(HttpServletRequest request, @RequestParam(name = "fail", required = false) String fail) {
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("url_prior_login", referer);
        return new ModelAndView("auth/login",
                "model", fail);
    }

}
