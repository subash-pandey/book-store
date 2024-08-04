package org.subash.capstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.subash.capstone.database.dao.UserDAO;
import org.subash.capstone.database.entity.User;
import org.subash.capstone.form.CreateUserFormBean;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/index/{id}")
    public ModelAndView indexPathVar(@PathVariable Integer id) {
        ModelAndView response = new ModelAndView("user/detail");
       User user  = userDAO.findUserByUserId(id);
        response.addObject("user", user );

        return response;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView response = new ModelAndView("user/list");
        List<User> users = userDAO.findAll();
        response.addObject("users", users);
        return response;
    }

    @GetMapping("/create")
    public ModelAndView createUser() {
        ModelAndView response = new ModelAndView("user/create");
        return response;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Integer id) {
        ModelAndView response = new ModelAndView("user/create");
        if (id != null) {
           User user = userDAO.findUserByUserId(id);
            if (user != null) {
                CreateUserFormBean form = new CreateUserFormBean();
                form.setUserId(user.getUserId());
                form.setFirstName(user.getFirstName());
                form.setLastName(user.getLastName());
                form.setPhone(user.getPhone());
                form.setEmail(user.getEmail());
                form.setAddressLine1(user.getAddressLine1());
                form.setAddressLine2(user.getAddressLine2());
                form.setCity(user.getCity());
                form.setState(user.getState());
                form.setZipCode(user.getZipCode());
                form.setCountry(user.getCountry());
                response.addObject("form", form);

            }
        }

        return response;


    }

    @GetMapping("/submit")
    public ModelAndView submit(CreateUserFormBean form) {
        ModelAndView response  = new ModelAndView();
        User user = new User();
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setAddressLine1(form.getAddressLine1());
        user.setAddressLine2(form.getAddressLine2());
        user.setCity(form.getCity());
        user.setState(form.getState());
        user.setZipCode(form.getZipCode());
        user.setCountry(form.getCountry());
        user.setPhone(form.getPhone());


        user = userDAO.save(user);
        response.setViewName("redirect:/user/index/" + user.getUserId());
        return response;
    }

}