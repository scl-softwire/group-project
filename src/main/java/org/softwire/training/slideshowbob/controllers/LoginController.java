package org.softwire.training.slideshowbob.controllers;

import org.softwire.training.slideshowbob.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
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

//    @ResponseBody
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String loginPost(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
//        String token = authService.login(username, password);
//        response.addCookie(new Cookie("Token", "Bearer " + token));
//        return token;
//    }

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
