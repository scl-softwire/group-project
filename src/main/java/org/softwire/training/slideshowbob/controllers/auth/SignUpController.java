package org.softwire.training.slideshowbob.controllers.auth;

import org.softwire.training.slideshowbob.models.database.AdminUser;
import org.softwire.training.slideshowbob.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SignUpController {

    private final UsersService usersService;

    @Autowired
    public SignUpController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signUpView() {
        return new ModelAndView("auth/signup");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public RedirectView signUp(@ModelAttribute AdminUser user) {
        usersService.createUser(user);
        return new RedirectView("/");
    }

}
