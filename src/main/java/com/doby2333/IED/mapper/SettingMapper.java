package com.doby2333.IED.mapper;

import com.doby2333.IED.entity.Setting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SettingMapper {
    @Insert("INSERT INTO setting VALUES (DEFAULT, #{uid}, #{pid}, #{lf}, #{li}, #{wf}, #{wi}, DEFAULT")
    void saveSetting(@Param("uid") Long uid, @Param("pid") Long pid, @Param("lf") Integer lf, @Param("li") Integer li, @Param("wf") Integer wf, @Param("wi") Integer wi);

    @Select("SELECT * FROM setting WHERE uid = #{uid}")
    List<Setting> getSettings(@Param("uid") Long uid);

}
