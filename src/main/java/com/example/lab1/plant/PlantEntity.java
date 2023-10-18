package com.example.lab1.plant;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.species.SpeciesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PlantEntity {

    private UUID id;

    private double height;

    private String name;

    private Date plantingDate;

    private GardenerEntity keeper;

    private SpeciesEntity species;

}
