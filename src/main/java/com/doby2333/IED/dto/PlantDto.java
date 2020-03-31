package com.doby2333.IED.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PlantDto {
    private Long ID;
    private int LIGHT_FREQ;
    private int LIGHT_INTENSE;
    private int WATER_FREQ;
    private int WATER_INTENSE;
}
