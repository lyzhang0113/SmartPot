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

    @RequestMapping(value = {"/", "/hello", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Map<String, Object> model) {
        return "welcome";
    }

    @GetMapping("/login")
    public String login(Map<String, Object> model, HttpSession session) {
        if (userService.findID((Long) session.getAttribute("id")))
            return "dashboard";
        else return "login";
    }

    @PostMapping("/login")
    public String validateLogin(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                Map<String, Object> model, HttpSession session) {
        if (userService.validateUser(username, password)) {
            session.setAttribute("id", userService.getID(username, password));
            return "dashboard";
        } else {
            model.put("msg", "Incorrect Credential!");
            return "login";
        }
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
            session.setAttribute("id", userService.getID(username, password));
        } else {
            model.put("msg", "Registration Failed! Duplicate Usernames!");
        }
        return validRegistration;
    }
}
