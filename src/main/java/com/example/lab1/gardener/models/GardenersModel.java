package com.example.lab1.gardener.models;

import com.example.lab1.gardener.GardenerEntity;
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
