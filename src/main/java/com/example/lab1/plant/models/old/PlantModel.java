package com.example.lab1.plant.models.old;

import lombok.*;

import java.time.LocalDate;
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

    private LocalDate plantingDate;

    private UUID keeper;

    private UUID species;

}
