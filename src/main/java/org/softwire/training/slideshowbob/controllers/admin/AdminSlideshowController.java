package org.softwire.training.slideshowbob.controllers.admin;


import org.softwire.training.slideshowbob.models.database.CurrentSlideshow;
import org.softwire.training.slideshowbob.models.database.Slideshow;
import org.softwire.training.slideshowbob.models.pages.SelectActiveSlideshowPageModel;
import org.softwire.training.slideshowbob.services.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/admin/slideshows")
public class AdminSlideshowController {

    @Autowired
    SlideshowService slideshowService = new SlideshowService();

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public ModelAndView selectAvtiveSlideshow() {
        List<Slideshow> slideshowList = slideshowService.getAllSlideshows();
        Slideshow activeSlideshow = slideshowService.getCurrentSlideshow();
        SelectActiveSlideshowPageModel model = new SelectActiveSlideshowPageModel(slideshowList, activeSlideshow);
        return new ModelAndView("/admin/slideshow/select-active-slideshow.html", "model", model);
    }

    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public RedirectView setActiveSlideshow(@ModelAttribute CurrentSlideshow slideshow) {
        slideshowService.setCurrentSlideshow(slideshow.getSlideshow());
        return new RedirectView("/admin");
    }

}
