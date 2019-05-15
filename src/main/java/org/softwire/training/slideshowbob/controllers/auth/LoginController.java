package org.softwire.training.slideshowbob.controllers.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginView(@RequestParam(name = "fail", required = false) String fail) {
        return  new ModelAndView("auth/login",
                "model", fail);
    }

}
