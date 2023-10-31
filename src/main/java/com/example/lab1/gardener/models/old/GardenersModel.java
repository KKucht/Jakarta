package com.example.lab1.gardener.models.old;

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
public class GardenersModel {

    private List<SimpleGardenerModel> gardeners = new ArrayList<>();

}
