package com.doby2333.IED.mapper;

import com.doby2333.IED.entity.Plant;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PlantMapper {

    @Select("SELECT * FROM plant ORDER BY name ASC")
    List<Plant> getPlants();

    @Select("SELECT light_freq FROM plant WHERE id = #{id}")
    int getLightFreqByID(@Param("id") Long id);

    @Select("SELECT light_intense FROM plant WHERE id = #{id}")
    int getLightIntenseByID(@Param("id") Long id);

    @Select("SELECT water_freq FROM plant WHERE id = #{id}")
    int getWaterFreqByID(@Param("id") Long id);

    @Select("SELECT light_freq FROM plant WHERE id = #{id}")
    int getWaterIntenseByID(@Param("id") Long id);

//    @Select("SELECT id, name FROM plant ORDER BY name ASC")
//    @MapKey("id")
//    Map<Long, String> getNames();
//
//    @Select("SELECT id, sci_name FROM plant")
//    @MapKey("id")
//    Map<Long, String> getSciNames();
//
//    @Select("SELECT id, light_freq FROM plant")
//    @MapKey("id")
//    Map<Long, Integer> getLightFreq();
//
//    @Select("SELECT id, light_intense FROM plant")
//    @MapKey("id")
//    Map<Long, Integer> getLightIntense();
//
//    @Select("SELECT id, light_detail FROM plant")
//    @MapKey("id")
//    Map<Long, String> getLightDetail();
//
//    @Select("SELECT id, water_freq FROM plant")
//    @MapKey("id")
//    Map<Long, Integer> getWaterFreq();
//
//    @Select("SELECT id, water_freq_detail FROM plant")
//    @MapKey("id")
//    Map<Long, String> getWaterFreqDetail();
//
//    @Select("SELECT id, water_intense FROM plant")
//    @MapKey("id")
//    Map<Long, Integer> getWaterIntense();
//
//    @Select("SELECT id, water_intense_detail FROM plant")
//    @MapKey("id")
//    Map<Long, String> getWaterIntenseDetail();
//
//    @Select("SELECT id, fert_guide FROM plant")
//    @MapKey("id")
//    Map<Long, String> getFertGuide();
//
//    @Select("SELECT id, fert_detail FROM plant")
//    @MapKey("id")
//    Map<Long, String> getFertDetail();
}
