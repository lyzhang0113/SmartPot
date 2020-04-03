package com.doby2333.IED.mapper;

import com.doby2333.IED.entity.Plant;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface PlantMapper {

    @Select("SELECT * FROM plant ORDER BY name ASC")
    List<Plant> getPlants();

    @Select("SELECT id, name FROM plant ORDER BY name ASC")
    Map<Long, String> getNames();

    @Select("SELECT id, sci_name FROM plant")
    Map<Long, String> getSciNames();

    @Select("SELECT id, light_freq FROM plant")
    Map<Long, Integer> getLightFreq();

    @Select("SELECT id, light_intense FROM plant")
    Map<Long, Integer> getLightIntense();

    @Select("SELECT id, light_detail FROM plant")
    Map<Long, String> getLightDetail();

    @Select("SELECT id, water_freq FROM plant")
    Map<Long, Integer> getWaterFreq();

    @Select("SELECT id, water_freq_detail FROM plant")
    Map<Long, String> getWaterFreqDetail();

    @Select("SELECT id, water_intense FROM plant")
    Map<Long, Integer> getWaterIntense();

    @Select("SELECT id, water_intense_detail FROM plant")
    Map<Long, String> getWaterIntenseDetail();

    @Select("SELECT id, fert_guide FROM plant")
    Map<Long, String> getFertGuide();

    @Select("SELECT id, fert_detail FROM plant")
    Map<Long, String> getFertDetail();
}
