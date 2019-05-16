package org.softwire.training.slideshowbob.controllers.auth;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleError(HttpServletRequest request, Exception e)
    {
        return new ModelAndView("error", "exception", e);
    }

}
