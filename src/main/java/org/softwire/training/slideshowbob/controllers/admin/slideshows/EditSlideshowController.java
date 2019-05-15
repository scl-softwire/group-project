package org.softwire.training.slideshowbob.controllers.admin.slideshows;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/slideshows")
public class EditSlideshowController {

    // Mapping - "/edit/id"
    // ModelAndView editSlideshowView (/id) GET -->> admin/slideshow/edit-slideshow.html

    // Mapping - "/edit/{id}/delete-slide/slideID"
    // ModelAndView deleteSlide POST

    // Mapping - "/edit/{id}/add"
    // ModelAndView addSlide POST
}
