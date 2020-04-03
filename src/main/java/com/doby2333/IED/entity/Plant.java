package com.doby2333.IED.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Plant {
    private long id;
    private String name;
    private String sci_name;
    private int light_freq;
    private int light_intense;
    private String light_detail;
    private int water_freq;
    private String water_freq_detail;
    private int water_intense;
    private String water_intense_detail;
    private String fert_guide;
    private String fert_detail;
    private Date create_time;

}
