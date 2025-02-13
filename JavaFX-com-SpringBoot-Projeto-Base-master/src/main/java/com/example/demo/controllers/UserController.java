package com.example.demo.controllers;

import com.example.demo.entities.User;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    public UserController() {
        User user = new User();
        user.setId(1);
        user.setName("Jean");
        user.setFunction("Administrador");

        User user1 = new User();
        user1.setId(1);
        user1.setName("Jean");
        user1.setFunction("Administrador");
    }
}
