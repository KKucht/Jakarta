package com.example.lab1.plant.models.old;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SimplePlantModel {

    private UUID id;

    private String name;

}
