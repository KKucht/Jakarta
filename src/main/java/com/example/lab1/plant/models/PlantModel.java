package com.example.lab1.plant.models;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.species.SpeciesEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PlantModel {

    private UUID id;

    private String name;

    private double height;

    private LocalDateTime plantingDate;

    private UUID keeper;

    private UUID species;

}
