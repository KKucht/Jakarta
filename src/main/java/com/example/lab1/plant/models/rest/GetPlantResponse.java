package com.example.lab1.plant.models.rest;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetPlantResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Species {

        private UUID id;

        private String name;

    }

    private UUID id;

    private String name;

    private double height;

    private LocalDate plantingDate;

    private Species species;

    private Long version;

}
