package com.doby2333.IED.service;

import com.doby2333.IED.dto.PlantDto;
import com.doby2333.IED.dto.PotDto;
import com.doby2333.IED.dto.SettingDto;
import com.doby2333.IED.mapper.PlantMapper;
import com.doby2333.IED.mapper.SettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashService {

    @Autowired
    private PlantMapper plantMapper;

    @Autowired
    private SettingMapper settingMapper;

    public int countPots(Long uid) {
        return settingMapper.countPots(uid);
    }

    public String getPlantNameByPID(Long pid) {
        return settingMapper.getPlantNameByPID(pid);
    }

    public PlantDto getPlants() {
        PlantDto dto = new PlantDto(plantMapper.getPlants());
        return dto;
    }

    public Long getUserPot(Long uid) {
        Long r = settingMapper.getUserPot(uid);
        return r == null ? Long.valueOf(-1) : r;
    }

    public PotDto getPots(Long uid) {
        PotDto potDto = new PotDto(settingMapper.getPots(uid));
        return potDto;
    }

    public boolean registerPot(Long uid, Long pid) {
        if (settingMapper.validatePID(pid) != 0) return false;
        settingMapper.saveSetting(uid, pid, Long.valueOf(0), 0, 0, 0, 0);
        return true;
    }

    public Boolean saveSetting(Long uid, Long pid, Long plant_id, Integer lightFreq, Integer lightIntense, Integer waterFreq, Integer waterIntense) {
        return settingMapper.saveSetting(uid, pid, plant_id, lightFreq, lightIntense, waterFreq, waterIntense);
    }

    public Boolean saveSetting(Long uid, Long pid, Long plant_id) {
        int lightFreq = plantMapper.getLightFreqByID(plant_id);
        int lightIntense = plantMapper.getLightIntenseByID(plant_id);
        int waterFreq = plantMapper.getWaterFreqByID(plant_id);
        int waterIntense = plantMapper.getWaterIntenseByID(plant_id);
        return saveSetting(uid, pid, plant_id, lightFreq, lightIntense, waterFreq, waterIntense);
    }

    public Map<Date, Integer> getRecentSetting(Long uid, Long pid, String setting) {
        Map<Date, Integer> result = new LinkedHashMap<>();
        List<SettingDto> settings = new LinkedList<>();
        int n = 10;
        switch (setting) {
            case "light_freq":
                settings = settingMapper.findRecentLightFreq(uid, pid, n);
                break;
            case "light_intense":
                settings = settingMapper.findRecentLightIntense(uid, pid, n);
                break;
            case "water_freq":
                settings = settingMapper.findRecentWaterFreq(uid, pid, n);
                break;
            case "water_intense":
                settings = settingMapper.findRecentWaterIntense(uid, pid, n);
                break;
        }
        for (int i = settings.size() - 1; i >= 0; i--) {
            result.put(settings.get(i).getTime(), settings.get(i).getValue());
        }
        return result;
    }

    public Date getRecentSettingDate(Long uid, Long pid) {
        return settingMapper.findRecentSettingDate(uid, pid);
    }
}
