package org.softwire.training.slideshowbob.controllers;

import org.softwire.training.slideshowbob.models.database.Technology;
import org.softwire.training.slideshowbob.models.page.AboutPageModel;
import org.softwire.training.slideshowbob.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/about")
public class AboutUsController {

    private final TechnologyService technologyService;

    @Autowired
    public AboutUsController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @RequestMapping("")
    ModelAndView aboutUs() {

        List<Technology> allTechnologies = technologyService.getAllTechnologies();

        AboutPageModel aboutPageModel = new AboutPageModel();
        aboutPageModel.setTechnologies(allTechnologies);

        return new ModelAndView("about", "model", aboutPageModel);
    }

    @RequestMapping("/add-technology")
    RedirectView addTechnology(@ModelAttribute Technology technology) {

        technologyService.addTechnology(technology);

        return new RedirectView("/about");
    }

    @RequestMapping("/delete-technology")
    RedirectView deleteTechnology(@RequestParam int technologyId) {

        technologyService.deleteTechnology(technologyId);

        return new RedirectView("/about");
    }
}
