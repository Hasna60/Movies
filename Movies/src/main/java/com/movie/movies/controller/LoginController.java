package com.movie.movies.controller;

import com.movie.movies.models.info.User;
import com.movie.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    private UserService userService;


    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    @ResponseBody
    public String Login(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        try {

            return ("/LoginPage");
        } catch (Exception e) {
            return ("redirect://LoginPage");
        }
    }

    @PostMapping("/login")
    public String formSubmit(@ModelAttribute("user") User user) {

        userService.getUserByEmail(user.getEmail());

        try { return ("LoginPage");

        } catch (Exception e) {
            return ("redirect://home");
        }
    }
}
