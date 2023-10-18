package com.example.lab1.species.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AllSpeciesModel {

    private List<SimpleSpeciesModel> gardeners = new ArrayList<>();

}
