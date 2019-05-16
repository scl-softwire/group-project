package org.softwire.training.slideshowbob.controllers.admin.slideshows;

import org.softwire.training.slideshowbob.models.database.CurrentSlideshow;
import org.softwire.training.slideshowbob.services.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin/slideshows")
public class DeleteSlideshowController {

    @Autowired
    SlideshowService slideshowService = new SlideshowService();

    @RequestMapping(value = "/delete/{id}")
    public RedirectView deleteSlideshow(@PathVariable("id") Integer id){
        slideshowService.deleteSlideshow(id);
        return new RedirectView("/admin/slideshows");
    }
}
