package org.softwire.training.slideshowbob.controllers.auth;

import org.softwire.training.slideshowbob.models.database.AdminUser;
import org.softwire.training.slideshowbob.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView signUpView(@RequestParam(name = "fail", required = false) String fail) {
        return new ModelAndView("auth/signup", "model", fail);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public RedirectView signUp(@ModelAttribute AdminUser user) {
        if (usersService.getAlladmins()
                .stream()
                .noneMatch(user1 -> user1.getUsername().equalsIgnoreCase(user.getUsername()))) {
            usersService.createUser(user);
            return new RedirectView("/");
        } else return new RedirectView("/signup?fail");
    }

}
