package ru.tandser.solution.web;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tandser.solution.service.UserService;

public abstract class AbstractUserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


}