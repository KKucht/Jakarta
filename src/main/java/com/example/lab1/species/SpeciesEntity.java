package com.example.lab1.species;


import com.example.lab1.plant.PlantEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode
public class SpeciesEntity {

    private UUID id;

    private String name;

    private Type type;

    private double price;

    @EqualsAndHashCode.Exclude
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
