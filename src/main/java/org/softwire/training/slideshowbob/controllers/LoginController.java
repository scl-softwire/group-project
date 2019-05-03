package org.softwire.training.slideshowbob.controllers;

import org.softwire.training.slideshowbob.security.UserPrincipal;
import org.softwire.training.slideshowbob.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private final AuthService authService;

    @Autowired
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginView() {
        return new ModelAndView("login");
    }

    @ResponseBody
    @RequestMapping("/fail")
    public String fail(){
        return "FAIL";
    }

    @ResponseBody
    @RequestMapping("/success")
    public String success(){
        return "FAIL";
    }
}
