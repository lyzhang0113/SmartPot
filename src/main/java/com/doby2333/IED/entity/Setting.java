package com.doby2333.IED.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Setting {

    private Long id;
    private Long uid;
    private Long pid;
    private Long plant_id;
    private Integer light_freq;
    private Integer light_intense;
    private Integer water_freq;
    private Integer water_intense;
    private Date create_time;

}
