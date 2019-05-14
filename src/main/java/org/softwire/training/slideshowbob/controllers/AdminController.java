package org.softwire.training.slideshowbob.controllers;

import org.softwire.training.slideshowbob.models.database.AdminUser;
import org.softwire.training.slideshowbob.models.database.Image;
import org.softwire.training.slideshowbob.models.database.NewSlideshow;
import org.softwire.training.slideshowbob.models.pages.ImagePageModel;
import org.softwire.training.slideshowbob.services.ImagesService;
import org.softwire.training.slideshowbob.services.SlideshowService;
import org.softwire.training.slideshowbob.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ImagesService imagesService;
    private final UsersService usersService;
    private final SlideshowService slideshowService;

    @Autowired
    public AdminController(ImagesService imagesService, UsersService usersService, SlideshowService slideshowService) {
        this.imagesService = imagesService;
        this.usersService = usersService;
        this.slideshowService = slideshowService;
    }

    @RequestMapping("")
    ModelAndView admin() {
        return new ModelAndView("admin");
    }

    @RequestMapping(value = "/select-images", method = RequestMethod.POST)
    RedirectView createSlideshow(HttpServletRequest request, @RequestBody NewSlideshow newSlideshow) {
        AdminUser loggedInAs = usersService.loadUserByUsername(request.getUserPrincipal().getName());
        slideshowService.createSlideshow(newSlideshow.getSlideshow(), newSlideshow.getSlideIds(),loggedInAs);
        return new RedirectView("/admin");
    }

    @RequestMapping("/manage")
    ModelAndView manage() {
        List<Image> allImages = imagesService.getAllImages();
        return new ModelAndView("manage","model", new ImagePageModel(allImages));
    }

    @RequestMapping(value = "/select-images", method = RequestMethod.GET)
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
