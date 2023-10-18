package com.example.lab1.plant.models;

import lombok.*;

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

    private Date plantingDate;

    private UUID species;

}
