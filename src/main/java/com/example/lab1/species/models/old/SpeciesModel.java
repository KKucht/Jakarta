package com.example.lab1.species.models.old;

import com.example.lab1.species.Type;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SpeciesModel {

    private UUID id;

    private String name;

    private Type type;

    private double price;

    private List<UUID> plants;

}
