package com.example.lab1.plant.models.rest;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutPlantRequest {

    private String name;

    private double height;

    private LocalDate plantingDate;

    private Long version;
}
