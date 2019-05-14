package org.softwire.training.slideshowbob.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginView(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("url_prior_login",referer);
        return new ModelAndView("login");
    }

    @ResponseBody
    @RequestMapping("/success")
    public String success(){
        return "SUCCESS";
    }

}
