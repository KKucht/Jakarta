package com.example.lab1.gardener;

import com.example.lab1.plant.PlantEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GardenerEntity {

    private UUID id;

    private String name;

    private int age;

    private List<PlantEntity> plants;

    public GardenerEntity(GardenerEntity gardener) {
        this.id = gardener.id;
        this.name = gardener.name;
        this.age = gardener.age;
        this.plants = new ArrayList<>();
        this.plants.addAll(gardener.getPlants());
    }
}
