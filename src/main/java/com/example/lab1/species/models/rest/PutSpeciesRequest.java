package com.example.lab1.species.models.rest;

import com.example.lab1.species.Type;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutSpeciesRequest {

    private String name;

    private Type type;

    private double price;
}
