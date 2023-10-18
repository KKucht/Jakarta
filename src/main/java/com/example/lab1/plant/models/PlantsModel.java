package com.example.lab1.plant.models;

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
public class PlantsModel {

    private List<SimplePlantModel> gardeners = new ArrayList<>();

}
