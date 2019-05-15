package org.softwire.training.slideshowbob.controllers;

import org.softwire.training.slideshowbob.models.database.Slideshow;
import org.softwire.training.slideshowbob.models.database.SlideshowSlide;
import org.softwire.training.slideshowbob.models.pages.PlaySlideshowPageModel;
import org.softwire.training.slideshowbob.services.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SlideshowController {

    private final SlideshowService slideshowService;

    @Autowired
    public SlideshowController(SlideshowService slideshowService) {
        this.slideshowService = slideshowService;
    }

    @RequestMapping("/slideshow")
    ModelAndView slideshow() {
        Slideshow slideshow = slideshowService.getCurrentSlideshow();
        List<SlideshowSlide> slideshowSlides = slideshowService.getImagesforSlideshow(slideshow.getId());
        PlaySlideshowPageModel model = new PlaySlideshowPageModel(slideshow, slideshowSlides);
        return new ModelAndView("slideshow", "model", model);
    }

}