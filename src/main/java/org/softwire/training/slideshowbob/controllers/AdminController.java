package org.softwire.training.slideshowbob.controllers;

import org.softwire.training.slideshowbob.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    private final AuthService authService;

    @Autowired
    public AdminController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping("/admin")
    ModelAndView admin() {
        return new ModelAndView("admin");
    }

    @RequestMapping("/admin/manage")
    ModelAndView manage() {
        return new ModelAndView("manage");
    }

}
