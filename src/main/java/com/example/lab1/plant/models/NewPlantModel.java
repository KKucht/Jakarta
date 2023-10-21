package com.example.lab1.plant.models;

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
public class NewPlantModel {

    private UUID id;

    private String name;

    private double height;

    private LocalDateTime plantingDate;

    private UUID species;

}
