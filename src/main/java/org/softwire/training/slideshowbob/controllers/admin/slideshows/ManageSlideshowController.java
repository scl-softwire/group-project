package org.softwire.training.slideshowbob.controllers.admin.slideshows;


import org.softwire.training.slideshowbob.models.database.CurrentSlideshow;
import org.softwire.training.slideshowbob.models.database.Slideshow;
import org.softwire.training.slideshowbob.models.pages.ManageSlideshowPageModel;
import org.softwire.training.slideshowbob.services.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/slideshows")
public class ManageSlideshowController {

    @Autowired
    SlideshowService slideshowService = new SlideshowService();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView manageSlideshows(@RequestParam(name = "fail",required = false) Optional<String> fail) {
        List<Slideshow> slideshowList = slideshowService.getAllSlideshows();
        Slideshow activeSlideshow = slideshowService.getCurrentSlideshow();
        ManageSlideshowPageModel model = new ManageSlideshowPageModel(slideshowList, activeSlideshow, fail.isPresent());
        return new ModelAndView("admin/slideshow/manage-slideshows", "model", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public RedirectView setActiveSlideshow(@ModelAttribute CurrentSlideshow slideshow) {
        slideshowService.setCurrentSlideshow(slideshow.getSlideshowId());
        return new RedirectView("/admin/slideshows");
    }

}
