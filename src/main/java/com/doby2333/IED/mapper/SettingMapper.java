package com.doby2333.IED.mapper;

import com.doby2333.IED.dto.SettingDto;
import com.doby2333.IED.entity.Pot;
import com.doby2333.IED.entity.Setting;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface SettingMapper {
    @Insert("INSERT INTO setting VALUES (DEFAULT, #{uid}, #{pid}, #{lf}, #{li}, #{wf}, #{wi}, DEFAULT")
    Boolean saveSetting(@Param("uid") Long uid, @Param("pid") Long pid, @Param("lf") Integer lf, @Param("li") Integer li, @Param("wf") Integer wf, @Param("wi") Integer wi);

    @Select("SELECT * FROM setting WHERE uid = #{uid}")
    List<Setting> getSettings(@Param("uid") Long uid);

    @Select("SELECT COUNT(DISTINCT pid) FROM setting WHERE uid = #{uid}")
    int countPots(@Param("uid") Long uid);

    @Select("SELECT c.pid, d.name\n" +
            "FROM\n" +
            "  ( SELECT a.pid, a.plant_id, a.t\n" +
            "    FROM\n" +
            "      ( SELECT s.pid, s.plant_id, max(s.create_time) AS t\n" +
            "        FROM setting s WHERE uid = #{uid} GROUP BY pid, plant_id\n" +
            "      ) AS a\n" +
            "      INNER JOIN (\n" +
            "        SELECT s.pid, max(s.create_time) AS t\n" +
            "        FROM setting s WHERE uid = #{uid} GROUP BY s.pid\n" +
            "      ) AS b ON a.t = b.t AND a.pid = b.pid\n" +
            "  ) c,\n" +
            "  plant d\n" +
            "WHERE c.plant_id = d.id")
    @Results({
            @Result(property = "pid", column = "pid"),
            @Result(property = "plant", column = "name")
    })
    List<Pot> getPots(@Param("uid") Long uid);

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

    @Select("SELECT create_time FROM setting WHERE uid = #{uid} AND pid = #{pid} ORDER BY create_time DESC LIMIT 1")
    Date findRecentSettingDate(@Param("uid") Long uid, @Param("pid") Long pid);

}
