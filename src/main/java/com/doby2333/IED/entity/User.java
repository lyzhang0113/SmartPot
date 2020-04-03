package com.doby2333.IED.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String password;
    private String username;
    private Date create_time;
}
