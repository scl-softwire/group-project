package org.softwire.training.slideshowbob.controllers.admin.slideshows;

import org.softwire.training.slideshowbob.models.database.AdminUser;
import org.softwire.training.slideshowbob.models.database.Image;
import org.softwire.training.slideshowbob.models.database.NewSlideshow;
import org.softwire.training.slideshowbob.models.pages.ImagePageModel;
import org.softwire.training.slideshowbob.services.ImagesService;
import org.softwire.training.slideshowbob.services.SlideshowService;
import org.softwire.training.slideshowbob.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/slideshows")
public class CreateNewSlideshowController {

    private final ImagesService imagesService;
    private final UsersService usersService;
    private final SlideshowService slideshowService;

    @Autowired
    public CreateNewSlideshowController(ImagesService imagesService, UsersService usersService, SlideshowService slideshowService) {
        this.imagesService = imagesService;
        this.usersService = usersService;
        this.slideshowService = slideshowService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    ModelAndView createSlideshowView() {
        List<Image> allImages = imagesService.getAllImages();
        return new ModelAndView("admin/slideshow/create-slideshow", "model", new ImagePageModel(allImages));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    RedirectView createSlideshow(HttpServletRequest request, @RequestBody NewSlideshow newSlideshow) {
        AdminUser loggedInAs = usersService.loadUserByUsername(request.getUserPrincipal().getName());
        slideshowService.createSlideshow(newSlideshow.getSlideshow(), newSlideshow.getSlideIds(),loggedInAs);
        return new RedirectView("/admin/slideshows");
    }
}
