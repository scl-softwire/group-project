package org.softwire.training.slideshowbob.controllers.admin.images;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/images")
public class ManageImagesController {

    // Mapping - "/"
    // ModelAndView manageImages GET -->> admin/images/manage-images.html

    // Mapping - "/upload"
    // RedirectView uploadImage POST

    // Mapping - "/delete/id"
    // RedirectView deleteImage (/id) POST

    // Mapping - "/edit/id"
    // ModelAndView editImageView (/id) GET -->> admin/images/edit-images.html

    // Mapping - "/edit/id"
    // RedirectView editImage (/id) POST
}
