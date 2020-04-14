package com.doby2333.IED.controller;

import com.doby2333.IED.service.DashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
public class DashController {

    @Autowired
    private DashService dashService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model, HttpSession session, @RequestParam(value = "pid", defaultValue = "1") String p) {
        // if not logged in, kick user out
        if (session.getAttribute("id") == null) {
            model.addAttribute("msg", "Please Login First Before Accessing This Content!");
            return "redirect:/login";
        }

        // addAttribute for user and pot info
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("pid", Integer.parseInt(p));

        // addAttribute for displaying history settings
        // TODO: Dropdown list to select potID
        /*
            Write Post Method to let user select PotId, re-render page with potId
         */
        Long uid = Long.parseLong((String) session.getAttribute("id"));
        Long pid = Long.parseLong(p);
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

        model.addAttribute("pot_count", dashService.countPots(uid));
        model.addAttribute("pots", dashService.getPots(uid));
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
