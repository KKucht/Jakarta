package com.example.lab1.plant;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.species.SpeciesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PlantEntity {

    private UUID id;

    private String name;

    private double height;

    private LocalDateTime plantingDate;

    private GardenerEntity keeper;

    private SpeciesEntity species;

    public PlantEntity(PlantEntity plant) {
        this.id = plant.id;
        this.height = plant.height;
        this.name = plant.name;
        this.plantingDate = plant.plantingDate;
        this.keeper = plant.keeper;
        this.species = plant.species;
    }

}
