package com.doby2333.IED.controller;

import com.doby2333.IED.service.DashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
public class DashController {

    @Autowired
    private DashService dashService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model, HttpSession session, @RequestParam(value = "pid", defaultValue = "default") String p) {
        // if not logged in, kick user out
        if (session.getAttribute("id") == null) {
            model.addAttribute("msg", "Please Login First Before Accessing This Content!");
            return "redirect:/login";
        }

        // addAttribute for user
        model.addAttribute("username", session.getAttribute("username"));

        // addAttribute for displaying history settings
        Long uid = Long.parseLong((String) session.getAttribute("id"));
        Long pid = dashService.getUserPot(uid);
        model.addAttribute("pid", pid);
        Date settingDate = dashService.getRecentSettingDate(uid, pid);
        Map<Date, Integer> light_freq_map = dashService.getRecentSetting(uid, pid, "light_freq");
        Map<Date, Integer> light_intense_map = dashService.getRecentSetting(uid, pid, "light_intense");
        Map<Date, Integer> water_freq_map = dashService.getRecentSetting(uid, pid, "water_freq");
        Map<Date, Integer> water_intense_map = dashService.getRecentSetting(uid, pid, "water_intense");
        model.addAttribute("setting_date", settingDate);
        model.addAttribute("light_freq_map", light_freq_map);
        model.addAttribute("light_intense_map", light_intense_map);
        model.addAttribute("water_freq_map", water_freq_map);
        model.addAttribute("water_intense_map", water_intense_map);
        model.addAttribute("light_freq", light_freq_map.get(settingDate));
        model.addAttribute("light_intense", light_intense_map.get(settingDate));
        model.addAttribute("water_freq", water_freq_map.get(settingDate));
        model.addAttribute("water_intense", water_intense_map.get(settingDate));

        // addAttribute to show the number of pots, and their id and plant_names
        model.addAttribute("pot_count", dashService.countPots(uid));
        model.addAttribute("pots", dashService.getPots(uid));
        model.addAttribute("plant_name", dashService.getPlantName(pid));
        return "dashboard";
    }

    @GetMapping("/dashboard/setting")
    public String setting(Model model, HttpSession session) {
        // if not logged in, kick user out
        if (session.getAttribute("id") == null) {
            model.addAttribute("msg", "Please Login First Before Accessing This Content!");
            return "redirect:/login";
        }
        Long uid = Long.parseLong((String) session.getAttribute("id"));
        model.addAttribute("pot_count", dashService.countPots(uid));
        return "pot_setting";
    }

    @GetMapping("/dashboard/register")
    public String registerPot(Model model, HttpSession session) {
        // if not logged in, kick user out
        if (session.getAttribute("id") == null) {
            model.addAttribute("msg", "Please Login First Before Accessing This Content!");
            return "redirect:/login";
        }
        Long uid = Long.parseLong((String) session.getAttribute("id"));
        model.addAttribute("pot_count", dashService.countPots(uid));
        return "register_pot";
    }

    @PostMapping("/dashboard/register")
    public String registerPot(Model model, HttpSession session, @RequestParam("pid") Long pid) {
        if (dashService.registerPot(Long.parseLong((String) session.getAttribute("id")), pid)) {
            model.addAttribute("msg", "Registration Completed! You have successfully registered Pot " + pid + "!");
        } else {
            model.addAttribute("warn", "Registration Failed! This pot has already been registered!");
        }
        return "register_pot";
    }

    @PostMapping("/saveSetting")
    public void saveSetting(@RequestParam("pid") Long pid,
                            @RequestParam("plant_id") Long plant_id,
                            @RequestParam(value = "light_freq", required = false) Integer lightFreq,
                            @RequestParam(value = "light_intense", required = false) Integer lightIntense,
                            @RequestParam(value = "water_freq", required = false) Integer waterFreq,
                            @RequestParam(value = "water_intense", required = false) Integer waterIntense,
                            Model model, HttpSession session) {
        if (session.getAttribute("id") == null) {
            model.addAttribute("msg", "Please Login to Save Settings!");
        } else {
            if (lightFreq != null)
                dashService.saveSetting((Long) session.getAttribute("id"), pid, plant_id, lightFreq, lightIntense, waterFreq, waterIntense);
            else
                dashService.saveSetting((Long) session.getAttribute("id"), pid, plant_id);
        }
    }

}
