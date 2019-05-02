package org.softwire.training.slideshowbob.controllers;

import org.softwire.training.slideshowbob.models.database.Image;
import org.softwire.training.slideshowbob.services.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/images")
public class ImageController {
    private final ImagesService imagesService;

    @Autowired
    public ImageController (ImagesService imagesService) {this.imagesService = imagesService;}

    @RequestMapping("/image-add/added")
    RedirectView uploadImage(@ModelAttribute Image image) {

        imagesService.uploadImage(image);

        return new RedirectView("/uploadImage");
    }
}
