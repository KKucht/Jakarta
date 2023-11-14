package com.example.lab1.species.models.rest;

import com.example.lab1.species.Type;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetSpeciesResponse {

    private UUID id;

    private String name;

    private Type type;

    private double price;

}
