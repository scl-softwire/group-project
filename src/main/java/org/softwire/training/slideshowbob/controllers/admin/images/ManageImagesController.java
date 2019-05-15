package org.softwire.training.slideshowbob.controllers.admin.images;

import org.softwire.training.slideshowbob.models.database.Image;
import org.softwire.training.slideshowbob.models.pages.ImagePageModel;
import org.softwire.training.slideshowbob.services.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/images")
public class ManageImagesController {

    private final ImagesService imagesService;

    @Autowired
    public ManageImagesController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @RequestMapping("")
    ModelAndView manageImages() {
        List<Image> allImages = imagesService.getAllImages();
        return new ModelAndView("admin/images/manage-images","model", new ImagePageModel(allImages));
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    RedirectView uploadImage(@ModelAttribute Image image) {
        imagesService.uploadImage(image);
        return new RedirectView("/admin/images/");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    RedirectView deleteImage(@PathVariable("id") Integer imageId) {
        imagesService.deleteImage(imageId);
        return new RedirectView("/admin/images/");
    }

    @RequestMapping("/edit/{id}")
    ModelAndView editImageView(@PathVariable("id") Integer id) throws Exception {
        Optional<Image> image = imagesService.getSingleImage(id);
        if (image.isPresent()) {
            return new ModelAndView("admin/images/edit-image", "image", image.get());
        } else {
            throw new ImageNotFoundException(id);
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    RedirectView editImage(@ModelAttribute Image image) {
        imagesService.editImage(image);
        return new RedirectView("/admin/images/");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private static class ImageNotFoundException extends Exception {
        private ImageNotFoundException(Integer id) {
            super("Image with id " + id + " + was not found");
        }
    }
}
