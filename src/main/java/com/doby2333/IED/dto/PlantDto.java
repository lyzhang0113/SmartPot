package com.doby2333.IED.dto;

import com.doby2333.IED.entity.Plant;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class PlantDto {
    private List<Plant> plants;

    public PlantDto() {
        this.plants = new LinkedList<Plant>();
    }

    public PlantDto(List<Plant> plants) {
        this.plants = plants;
    }

    public void addPlant(Plant p) {
        plants.add(p);
    }
}
