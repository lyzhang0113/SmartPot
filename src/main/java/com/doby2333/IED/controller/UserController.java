package com.doby2333.IED.controller;

import com.doby2333.IED.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/hello", "/welcome"})
    public String welcome(Map<String, Object> model) {
        return "welcome";
    }

    @RequestMapping("/login")
    public String login(Map<String, Object> model) {
        return "login";
    }

    @ResponseBody
    @PostMapping("/login/validate")
    public boolean validateLogin(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                Map<String, Object> model, HttpSession session) {
        boolean validUser = userService.validateUser(username, password);
        if (validUser) {
            session.setAttribute("id", userService.getUserID(username, password));
        } else {
            model.put("msg", "Incorrect Credential!");
        }
        return validUser;
    }

    @RequestMapping("/register")
    public String register(Map<String, Object> model) {
        return "register";
    }

    @ResponseBody
    @PostMapping("/register/validate")
    public boolean validateRegistration(@RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        Map<String, Object> model, HttpSession session) {
        boolean validRegistration = userService.register(username, password);
        if (validRegistration) {
            session.setAttribute("id", userService.getUserID(username, password));
        } else {
            model.put("msg", "Registration Failed! Duplicate Usernames!");
        }
        return validRegistration;
    }
}
