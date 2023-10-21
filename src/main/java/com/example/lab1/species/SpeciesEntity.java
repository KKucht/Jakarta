package com.example.lab1.species;


import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.plant.PlantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class SpeciesEntity {

    private UUID id;

    private String name;

    private Type type;

    private double price;

    private List<PlantEntity> plants;

    public SpeciesEntity(SpeciesEntity species) {
        this.id = species.id;
        this.name = species.getName();
        this.plants = new ArrayList<>();
        this.plants.addAll(species.getPlants());
        this.type = species.getType();
        this.price = species.getPrice();
    }

}
