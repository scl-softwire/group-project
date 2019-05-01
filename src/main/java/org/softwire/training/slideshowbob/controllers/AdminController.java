package org.softwire.training.slideshowbob.controllers;

import org.softwire.training.slideshowbob.models.database.Image;
import org.softwire.training.slideshowbob.models.pages.ImagePageModel;
import org.softwire.training.slideshowbob.services.ImagesService;
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

    @Autowired
    public AdminController (ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    // TODO - add service

    @RequestMapping("")
    ModelAndView admin() {
        return new ModelAndView("admin");
    }


    @RequestMapping("/select-images")
    ModelAndView allImages() {
        List<Image> allImages = new ArrayList<>();
        return new ModelAndView("/images", "model", new ImagePageModel(allImages));
    }

}
