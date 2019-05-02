package org.softwire.training.slideshowbob.controllers;

import org.softwire.training.slideshowbob.services.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SlideshowController {

    private final ImagesService imagesService;

    @Autowired
    public SlideshowController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @RequestMapping("/slideshow")
    ModelAndView slideshow() {
        // Eventually, these links will come from the database. But for now, they're hardcoded!
        List<String> imageUrls = imagesService.getImageURLs();
        return new ModelAndView("slideshow", "model", imageUrls);
    }

}