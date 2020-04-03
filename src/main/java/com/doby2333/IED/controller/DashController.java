package com.doby2333.IED.controller;

import com.doby2333.IED.service.DashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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

        // addAttribute for selecting plants
        model.addAttribute("name", dashService.getNames());
        model.addAttribute("sci_name", dashService.getSciNames());
        model.addAttribute("light_freq", dashService.getLightFreq());
        model.addAttribute("light_intense", dashService.getLightIntense());
        model.addAttribute("light_detail", dashService.getLightDetail());
        model.addAttribute("water_freq", dashService.getWaterFreq());
        model.addAttribute("water_freq_detail", dashService.getWaterFreqDetail());
        model.addAttribute("water_intense", dashService.getWaterIntense());
        model.addAttribute("water_intense_detail", dashService.getWaterIntenseDetail());
        model.addAttribute("fert_guide", dashService.getFertGuide());
        model.addAttribute("fert_detail", dashService.getFertDetail());

        // addAttribute for displaying history settings


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
