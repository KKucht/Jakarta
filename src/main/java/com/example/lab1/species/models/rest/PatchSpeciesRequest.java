package com.example.lab1.species.models.rest;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchSpeciesRequest {

    private String name;

    private double price;
}
