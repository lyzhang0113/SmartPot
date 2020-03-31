package com.doby2333.IED.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Plant {
    private Long ID;
    private String NAME;
    private String SCI_NAME;
    private int LIGHT_FREQ;
    private int LIGHT_INTENSE;
    private String LIGHT_DETAIL;
    private int WATER_FREQ;
    private String WATER_FREQ_DETAIL;
    private int WATER_INTENSE;
    private String WATER_INTENSE_DETAIL;
    private String FERT_GUIDE;
    private String FERT_DETAIL;
    private Date CREATE_TIME;

}
