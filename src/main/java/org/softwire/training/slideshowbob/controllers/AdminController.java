package org.softwire.training.slideshowbob.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    
    @RequestMapping("/admin")
    ModelAndView adminView() {
        return new ModelAndView("admin/admin");
    }

}
