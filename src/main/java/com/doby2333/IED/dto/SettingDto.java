package com.doby2333.IED.dto;

import lombok.Data;

@Data
public class SettingDto {

    private Long id;
    private Long uid;
    private Long pid;
    private Integer light_freq;
    private Integer light_intense;
    private Integer water_freq;
    private Integer water_intense;

}
