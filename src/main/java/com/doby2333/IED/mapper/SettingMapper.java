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

    @Select("SELECT COUNT(*) FROM setting WHERE pid = #{pid}")
    int validatePID(@Param("pid") Long pid);

    @Insert("INSERT INTO setting VALUES (DEFAULT, #{uid}, #{pid}, #{plant_id}, #{lf}, #{li}, #{wf}, #{wi}, DEFAULT)")
    Boolean saveSetting(@Param("uid") Long uid, @Param("pid") Long pid, @Param("plant_id") Long plant_id, @Param("lf") int lf, @Param("li") int li, @Param("wf") int wf, @Param("wi") int wi);

    @Delete("DELETE FROM setting WHERE uid = #{uid} AND pid = #{pid}")
    Boolean removePot(@Param("uid") Long uid, @Param("pid") Long pid);

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

    @Select("SELECT d.name\n" +
            "FROM\n" +
            "  ( SELECT a.pid, a.plant_id, a.t\n" +
            "    FROM\n" +
            "      ( SELECT s.pid, s.plant_id, max(s.create_time) AS t\n" +
            "        FROM setting s WHERE pid = #{pid} GROUP BY pid, plant_id\n" +
            "      ) AS a\n" +
            "      INNER JOIN (\n" +
            "        SELECT s.pid, max(s.create_time) AS t\n" +
            "        FROM setting s WHERE pid = #{pid} GROUP BY s.pid\n" +
            "      ) AS b ON a.t = b.t AND a.pid = b.pid\n" +
            "  ) c,\n" +
            "  plant d\n" +
            "WHERE c.plant_id = d.id")
    String getPlantNameByPID(@Param("pid") Long pid);

    @Select("SELECT pid FROM setting WHERE uid = #{uid} LIMIT 1")
    Long getUserPot(@Param("uid") Long uid);

    @Select("SELECT light_freq, create_time FROM setting WHERE uid = #{uid} AND pid = #{pid} ORDER BY create_time DESC LIMIT #{n}")
    @Results({
            @Result(property = "value", column = "light_freq"),
            @Result(property = "time", column = "create_time")
    })
    List<SettingDto> findRecentLightFreq(@Param("uid") Long uid, @Param("pid") Long pid, @Param("n") int n);

    @Select("SELECT light_intense, create_time FROM setting WHERE uid = #{uid} AND pid = #{pid} ORDER BY create_time DESC LIMIT #{n}")
    @Results({
            @Result(property = "value", column = "light_intense"),
            @Result(property = "time", column = "create_time")
    })
    List<SettingDto> findRecentLightIntense(@Param("uid") Long uid, @Param("pid") Long pid, @Param("n") int n);

    @Select("SELECT water_freq, create_time FROM setting WHERE uid = #{uid} AND pid = #{pid} ORDER BY create_time DESC LIMIT #{n}")
    @Results({
            @Result(property = "value", column = "water_freq"),
            @Result(property = "time", column = "create_time")
    })
    List<SettingDto> findRecentWaterFreq(@Param("uid") Long uid, @Param("pid") Long pid, @Param("n") int n);

    @Select("SELECT water_intense, create_time FROM setting WHERE uid = #{uid} AND pid = #{pid} ORDER BY create_time DESC LIMIT #{n}")
    @Results({
            @Result(property = "value", column = "water_intense"),
            @Result(property = "time", column = "create_time")
    })
    List<SettingDto> findRecentWaterIntense(@Param("uid") Long uid, @Param("pid") Long pid, @Param("n") int n);

    @Select("SELECT * FROM setting WHERE uid = #{uid} AND pid = #{pid} ORDER BY create_time DESC LIMIT 1")
    Setting findRecentSetting(@Param("uid") Long uid, @Param("pid") Long pid);

    @Select("SELECT create_time FROM setting WHERE uid = #{uid} AND pid = #{pid} ORDER BY create_time DESC LIMIT 1")
    Date findRecentSettingDate(@Param("uid") Long uid, @Param("pid") Long pid);

}
