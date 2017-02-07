package ru.tandser.solution.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.tandser.solution.service.UserService;

@Controller
public class RootController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:meals";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/meals")
    public String meals() {
        return "meals";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }
}