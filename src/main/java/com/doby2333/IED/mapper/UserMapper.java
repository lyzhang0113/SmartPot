package com.doby2333.IED.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    @Select("SELECT COUNT(*) FROM user WHERE username = #{username} AND password = #{password};")
    int validate(@Param("username") String username, @Param("password") String password);

    @Select("SELECT id FROM user WHERE username = #{username} AND password = #{password};")
    Long getID(@Param("username") String username, @Param("password") String password);

    @Select("SELECT COUNT(*) FROM user WHERE id = #{id};")
    int findID(@Param("id") Long id);

    @Insert("INSERT INTO user VALUES (DEFAULT, #{username}, #{password}, DEFAULT, DEFAULT);")
    void register(@Param("username") String username, @Param("password") String password);

    @Select("SELECT COUNT(*) FROM user WHERE username = #{username};")
    int countUser(@Param("username") String username);
}
