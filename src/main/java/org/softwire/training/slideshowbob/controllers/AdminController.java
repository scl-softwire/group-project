package org.softwire.training.slideshowbob.controllers;

import org.softwire.training.slideshowbob.models.database.Image;
import org.softwire.training.slideshowbob.models.pages.ImagePageModel;
import org.softwire.training.slideshowbob.services.ImagesService;
import org.softwire.training.slideshowbob.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping ("/admin")
public class AdminController {

    private final ImagesService imagesService;
    private final AuthService authService;

    @Autowired
    public AdminController(ImagesService imagesService, AuthService authService) {
        this.imagesService = imagesService;
        this.authService = authService;
    }

    // TODO - add service


    @RequestMapping("")
    ModelAndView admin() {
        return new ModelAndView("admin");
    }


    @RequestMapping("/select-images")
    ModelAndView allImages() {
        List<Image> allImages = new ArrayList<>();
        return new ModelAndView("select-images", "model", new ImagePageModel(allImages));
    }

}
