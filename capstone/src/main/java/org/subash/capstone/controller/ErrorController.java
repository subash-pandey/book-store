package org.subash.capstone.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@Slf4j
@Controller
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NoResourceFoundException.class)
    public ModelAndView error404(HttpServletRequest request) {
        // This is used in the security config for 404 pages
        ModelAndView response = new ModelAndView("error/404");

        log.info("!!!!!!!!!!!!!!!!!! IN ERROR CONTROLLER : 404 NOT FOUND : " + request.getMethod() + " " + request.getRequestURI());

        // this line of code is specifically setting a 404 status code
        response.setStatus(HttpStatus.NOT_FOUND);

        return response;
    }
    @RequestMapping(value = {"/error/403", "/403"})
    public ModelAndView handle403(HttpServletRequest request) {
        ModelAndView response = new ModelAndView("error/403");

        log.info("403 Error - Forbidden Access: Method: {} Request URI: {}", request.getMethod(), request.getRequestURI());

        response.setStatus(HttpStatus.FORBIDDEN);

        return response;
    }
    @ExceptionHandler(Exception.class)
    @RequestMapping(value = {"/error/500", "/500"})
    public ModelAndView handle500(HttpServletRequest request, Exception ex) {
        ModelAndView response = new ModelAndView("error/500");

        log.error("500 Error - Internal Server Error: Method: {} Request URI: {} - Exception: {}",
                request.getMethod(), request.getRequestURI(), ex.getMessage(), ex);

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return response;
    }
}
