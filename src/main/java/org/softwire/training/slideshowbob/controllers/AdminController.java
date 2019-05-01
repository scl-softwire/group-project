package org.softwire.training.slideshowbob.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    // TODO - add service

    @RequestMapping("/admin")
    ModelAndView admin() {
        return new ModelAndView("admin");
    }


}
