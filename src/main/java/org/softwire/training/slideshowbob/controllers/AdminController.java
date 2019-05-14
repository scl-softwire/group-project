package org.softwire.training.slideshowbob.controllers;

import org.softwire.training.slideshowbob.models.database.Image;
import org.softwire.training.slideshowbob.models.pages.ImagePageModel;
import org.softwire.training.slideshowbob.services.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ImagesService imagesService;

    @Autowired
    public AdminController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @RequestMapping("")
    ModelAndView admin() {
        return new ModelAndView("admin");
    }


    @RequestMapping("/manage")
    ModelAndView manage() {
        List<Image> allImages = imagesService.getAllImages();
        return new ModelAndView("manage","model", new ImagePageModel(allImages));
    }

    @RequestMapping("/select-images")
    ModelAndView allImages() {
        List<Image> allImages = imagesService.getAllImages();
        return new ModelAndView("select-images", "model", new ImagePageModel(allImages));
 
    }

    @RequestMapping("/manage/edit/{id}") ModelAndView edit(@PathVariable("id") Integer id) throws Exception {
        Optional<Image> image = imagesService.getSingleImage(id);
        if (image.isPresent()) {
            return new ModelAndView("edit", "image", image.get());
        } else {
            throw new ImageNotFoundException(id);
        }
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    private static class ImageNotFoundException extends Exception {
        private ImageNotFoundException(Integer id) {
            super("Image with id " + id + " + was not found");
        }
    }

}
