package org.softwire.training.slideshowbob.controllers;

import org.softwire.training.slideshowbob.models.database.Image;
import org.softwire.training.slideshowbob.models.database.Slideshow;
import org.softwire.training.slideshowbob.models.database.SlideshowSlide;
import org.softwire.training.slideshowbob.models.pages.EditSlideshowPageModel;
import org.softwire.training.slideshowbob.services.ImagesService;
import org.softwire.training.slideshowbob.services.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class SlideshowController {

    private final ImagesService imagesService;
    private final SlideshowService slideshowService;

    @Autowired
    public SlideshowController(ImagesService imagesService, SlideshowService slideshowService) {
        this.imagesService = imagesService;
        this.slideshowService = slideshowService;
    }


    @RequestMapping("/slideshow")
    ModelAndView slideshow() {
        // Eventually, these links will come from the database. But for now, they're hardcoded!
        List<String> imageUrls = imagesService.getImageURLs();
        return new ModelAndView("slideshow", "model", imageUrls);
    }

    @RequestMapping("/edit-slideshow/{id}")
    ModelAndView slideshowSlide(@PathVariable("id") Integer id) {

        Slideshow slideshow = slideshowService.getSlideshow(id);
        List<SlideshowSlide> imagesForSlideshow = slideshowService.getImagesforSlideshow(id);

        return new ModelAndView("edit-slideshow", "model", new EditSlideshowPageModel(
                slideshow,
                imagesForSlideshow
        ));

    }
    @RequestMapping(value = "/edit-slideshow/{id}/slide-delete/{slideId}", method = RequestMethod.POST)
    RedirectView deleteSlide(@PathVariable("slideId") Integer slideId, @PathVariable("id") Integer id)  {

        slideshowService.deleteSlide(slideId, id);

        return new RedirectView("/edit-slideshow/{id}");
    }

    @RequestMapping(value = "/edit-slideshow/{id}/added-image", method = RequestMethod.POST)
    RedirectView uploadSlide(@PathVariable("id") Integer slideshowId, @ModelAttribute Image image) {

        slideshowService.uploadSlide(image, slideshowId);

        return new RedirectView("/edit-slideshow/{id}");
    }

}