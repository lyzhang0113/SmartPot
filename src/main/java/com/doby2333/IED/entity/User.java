package com.doby2333.IED.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long ID;
    private String PASSWORD;
    private String USERNAME;
    private Date CREATED_TIME;
}
