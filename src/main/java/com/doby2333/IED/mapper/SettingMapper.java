package com.doby2333.IED.mapper;

import com.doby2333.IED.dto.SettingDto;
import com.doby2333.IED.entity.Setting;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SettingMapper {
    @Insert("INSERT INTO setting VALUES (DEFAULT, #{uid}, #{pid}, #{lf}, #{li}, #{wf}, #{wi}, DEFAULT")
    void saveSetting(@Param("uid") Long uid, @Param("pid") Long pid, @Param("lf") Integer lf, @Param("li") Integer li, @Param("wf") Integer wf, @Param("wi") Integer wi);

    @Select("SELECT * FROM setting WHERE uid = #{uid}")
    List<Setting> getSettings(@Param("uid") Long uid);


    @Select("SELECT light_freq, create_time FROM setting WHERE uid = #{uid} AND pid = #{pid} ORDER BY create_time ASC LIMIT 5")
    @Results({
            @Result(property = "value", column = "light_freq"),
            @Result(property = "time", column = "create_time")
    })
    List<SettingDto> find5LightFreq(@Param("uid") Long uid, @Param("pid") Long pid);

    @Select("SELECT light_intense, create_time FROM setting WHERE uid = #{uid} AND pid = #{pid} ORDER BY create_time ASC LIMIT 5")
    @Results({
            @Result(property = "value", column = "light_intense"),
            @Result(property = "time", column = "create_time")
    })
    List<SettingDto> find5LightIntense(@Param("uid") Long uid, @Param("pid") Long pid);

    @Select("SELECT water_freq, create_time FROM setting WHERE uid = #{uid} AND pid = #{pid} ORDER BY create_time ASC LIMIT 5")
    @Results({
            @Result(property = "value", column = "water_freq"),
            @Result(property = "time", column = "create_time")
    })
    List<SettingDto> find5WaterFreq(@Param("uid") Long uid, @Param("pid") Long pid);

    @Select("SELECT water_intense, create_time FROM setting WHERE uid = #{uid} AND pid = #{pid} ORDER BY create_time ASC LIMIT 5")
    @Results({
            @Result(property = "value", column = "water_intense"),
            @Result(property = "time", column = "create_time")
    })
    List<SettingDto> find5WaterIntense(@Param("uid") Long uid, @Param("pid") Long pid);

}
