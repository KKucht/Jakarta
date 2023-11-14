package com.example.lab1.plant;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.species.SpeciesEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode
public class PlantEntity {

    private UUID id;

    private String name;

    private double height;

    private LocalDate plantingDate;

    @EqualsAndHashCode.Exclude
    private GardenerEntity keeper;

    @EqualsAndHashCode.Exclude
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
