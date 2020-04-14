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

}
