package org.softwire.training.slideshowbob.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SlideshowController {

    @RequestMapping("/slideshow")
    ModelAndView slideshow() {
        // Eventually, these links will come from the database. But for now, they're hardcoded!
        List<String> imageUrls = new ArrayList<String>(){{
            add("https://s3-us-west-2.amazonaws.com/s.cdpn.io/203277/summer-slide.jpg");
            add("https://s3-us-west-2.amazonaws.com/s.cdpn.io/203277/fall-slide.jpg");
            add("https://s3-us-west-2.amazonaws.com/s.cdpn.io/203277/winter-slide.jpg");
            add("https://s3-us-west-2.amazonaws.com/s.cdpn.io/203277/spring-slide.jpg");
        }};
        return new ModelAndView("slideshow", "model", imageUrls);
    }

}