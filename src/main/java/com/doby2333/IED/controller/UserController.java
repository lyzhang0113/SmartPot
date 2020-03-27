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

    @RequestMapping(value = {"/", "/hello"})
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @ResponseBody
    @PostMapping("/login/validate")
    public boolean validateLogin(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                Map<String, Object> map, HttpSession session) {
        boolean validUser = userService.validateUser(username, password);
        if (validUser) {
            session.setAttribute("id", userService.getUserID(username, password));
        } else {
            map.put("msg", "Incorrect Credential!");
        }
        return validUser;
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @ResponseBody
    @PostMapping("/register/validate")
    public boolean validateRegistration(@RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        Map<String, Object> map, HttpSession session) {
        boolean validRegistration = userService.register(username, password);
        if (validRegistration) {
            session.setAttribute("id", userService.getUserID(username, password));
        } else {
            map.put("msg", "Registration Failed! Duplicate Usernames!");
        }
        return validRegistration;
    }
}
