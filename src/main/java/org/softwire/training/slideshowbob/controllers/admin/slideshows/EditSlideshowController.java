package org.softwire.training.slideshowbob.controllers.admin.slideshows;

import org.softwire.training.slideshowbob.models.database.ExistingSlides;
import org.softwire.training.slideshowbob.models.database.Image;
import org.softwire.training.slideshowbob.models.database.Slideshow;
import org.softwire.training.slideshowbob.models.database.SlideshowSlide;
import org.softwire.training.slideshowbob.models.pages.EditSlideshowPageModel;
import org.softwire.training.slideshowbob.services.ImagesService;
import org.softwire.training.slideshowbob.services.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/admin/slideshows")
public class EditSlideshowController {

    private final ImagesService imagesService;
    private final SlideshowService slideshowService;

    @Autowired
    public EditSlideshowController(ImagesService imagesService, SlideshowService slideshowService) {
        this.imagesService = imagesService;
        this.slideshowService = slideshowService;
    }

    @RequestMapping("/edit/{id}")
    ModelAndView editSlideshowView(@PathVariable("id") Integer id) {
        Slideshow slideshow = slideshowService.getSlideshow(id);
        List<SlideshowSlide> imagesForSlideshow = slideshowService.getImagesforSlideshow(id);
        List<Image> allImages = imagesService.getAllImages();
        return new ModelAndView("admin/slideshow/edit-slideshow", "model", new EditSlideshowPageModel(
                slideshow,
                imagesForSlideshow,
                allImages
        ));
    }

    @RequestMapping(value = "/edit/{id}/slide-delete/{slideId}", method = RequestMethod.POST)
    RedirectView deleteSlide(@PathVariable("slideId") Integer slideId, @PathVariable("id") Integer id) {
        slideshowService.deleteSlide(slideId, id);
        return new RedirectView("/edit/{id}");
    }

    @RequestMapping(value = "/edit/{id}/add", method = RequestMethod.POST)
    RedirectView addSlide(@PathVariable("id") Integer slideshowId, @RequestBody ExistingSlides slides) {
        slideshowService.addExistingSlidesToSlideshow(slideshowId, slides.getSlideIds());
        return new RedirectView("/edit/{id}");
    }

}
