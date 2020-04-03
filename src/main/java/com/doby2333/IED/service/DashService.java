package com.doby2333.IED.service;

import com.doby2333.IED.entity.Plant;
import com.doby2333.IED.mapper.PlantMapper;
import com.doby2333.IED.mapper.SettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashService {

    @Autowired
    private PlantMapper plantMapper;

    @Autowired
    private SettingMapper settingMapper;

    public List<Plant> getPlants() {
        return plantMapper.getPlants();
    }

//    public Map<Long, String> getNames() {return plantMapper.getNames();}
//
//    public Map<Long, String> getSciNames() {return plantMapper.getSciNames();}
//
//    public Map<Long, Integer> getLightFreq() {return plantMapper.getLightFreq();}
//
//    public Map<Long, Integer> getLightIntense() {return plantMapper.getLightIntense();}
//
//    public Map<Long, String> getLightDetail() {return plantMapper.getLightDetail();}
//
//    public Map<Long, Integer> getWaterFreq() {return plantMapper.getWaterFreq();}
//
//    public Map<Long, String> getWaterFreqDetail() {return plantMapper.getWaterFreqDetail();}
//
//    public Map<Long, Integer> getWaterIntense() {return plantMapper.getWaterIntense();}
//
//    public Map<Long, String> getWaterIntenseDetail() {return plantMapper.getWaterIntenseDetail();}
//
//    public Map<Long, String> getFertGuide() {return plantMapper.getFertGuide();}
//
//    public Map<Long, String> getFertDetail() {return plantMapper.getFertDetail();}

    public void saveSetting(Long uid, Long pid, Integer lightFreq, Integer lightIntense, Integer waterFreq, Integer waterIntense) {
        settingMapper.saveSetting(uid, pid, lightFreq, lightIntense, waterFreq, waterIntense);
    }

}
