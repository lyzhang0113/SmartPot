package com.doby2333.IED.dto;

import com.doby2333.IED.entity.Pot;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class PotDto {
    private List<Pot> pots;

    public PotDto() {
        this.pots = new LinkedList<Pot>();
    }

    public PotDto(List<Pot> pots) {
        this.pots = pots;
    }

    public void addPot(Pot p) {
        this.pots.add(p);
    }
}
