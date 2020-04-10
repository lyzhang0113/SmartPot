package com.doby2333.IED.controller;

import com.doby2333.IED.service.DashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class DashController {

    @Autowired
    private DashService dashService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        // if not logged in, kick user out
        if (session.getAttribute("id") == null) {
            model.addAttribute("msg", "Please Login First Before Accessing This Content!");
            return "redirect:/login";
        }

        // addAttribute for user info
        model.addAttribute("username", session.getAttribute("username"));

        // addAttribute for selecting plants
        model.addAttribute("plants", dashService.getPlants());

        // addAttribute for displaying history settings
        // TODO: Dropdown list to select potID
        /*
            Write Post Method to let user select PotId, re-render page with potId
         */
        model.addAttribute("light_freq_map", dashService.getRecentSetting(Long.parseLong((String) session.getAttribute("id")), Long.parseLong("1"), "light_freq"));
        model.addAttribute("light_intense_map", dashService.getRecentSetting(Long.parseLong((String) session.getAttribute("id")), Long.parseLong("1"), "light_intense"));
        model.addAttribute("water_freq_map", dashService.getRecentSetting(Long.parseLong((String) session.getAttribute("id")), Long.parseLong("1"), "water_freq"));
        model.addAttribute("water_intense_map", dashService.getRecentSetting(Long.parseLong((String) session.getAttribute("id")), Long.parseLong("1"), "water_intense"));
        return "dashboard";
    }

    @PostMapping("/saveSetting")
    public void saveSetting(@RequestParam("pid") Long pid,
                            @RequestParam("light_freq") Integer lightFreq,
                            @RequestParam("light_intense") Integer lightIntense,
                            @RequestParam("water_freq") Integer waterFreq,
                            @RequestParam("water_intense") Integer waterIntense,
                            Model model, HttpSession session) {
        if (session.getAttribute("id") == null) {
            model.addAttribute("msg", "Please Login to Save Settings!");
        } else {
            dashService.saveSetting((Long) session.getAttribute("id"), pid, lightFreq, lightIntense, waterFreq, waterIntense);
        }
    }

}
