package org.softwire.training.slideshowbob.controllers;

import org.softwire.training.slideshowbob.models.database.Image;
import org.softwire.training.slideshowbob.models.pages.ImagePageModel;
import org.softwire.training.slideshowbob.services.ImagesService;
import org.softwire.training.slideshowbob.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ImagesService imagesService;
    private final AuthService authService;

    @Autowired
    public AdminController(ImagesService imagesService, AuthService authService) {
        this.imagesService = imagesService;
        this.authService = authService;
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

    @RequestMapping("/manage/edit") ModelAndView edit() {
        // TODO: Get image from DB

        Image image = new Image();
        image.setId(1);
        image.setAuthor("Joel");
        image.setImageName("G");
        image.setLicense("Asdf");
        image.setDateTimeStamp(LocalDateTime.now());
        image.setUrl("https://static.gamespot.com/uploads/scale_super/171/1712892/3527994-helen%20sloan%20-%20hbo%20%286%29%20copy.jpg");

        return new ModelAndView("edit", "image", image);

    }

}
