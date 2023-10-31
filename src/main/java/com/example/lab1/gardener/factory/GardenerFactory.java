package com.example.lab1.gardener.factory;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.factory.api.GetEntityFromNewGardenerModel;
import com.example.lab1.gardener.factory.api.GetGardenerModelFromEntity;
import com.example.lab1.gardener.factory.api.GetGardenersModelFromEntity;
import com.example.lab1.gardener.models.GardenerModel;
import com.example.lab1.gardener.models.GardenersModel;
import com.example.lab1.gardener.models.NewGardenerModel;
import com.example.lab1.gardener.models.SimpleGardenerModel;
import com.example.lab1.plant.PlantEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class GardenerFactory implements GetGardenerModelFromEntity, GetGardenersModelFromEntity, GetEntityFromNewGardenerModel {

    @Override
    public GardenerEntity getEntityFromModel(NewGardenerModel m) {
        return new GardenerEntity(
                m.getId(),
                m.getName(),
                m.getAge(),
                new ArrayList<>());
    }

    @Override
    public GardenerModel getModelFromEntity(GardenerEntity e) {
        return new GardenerModel(
                e.getId(),
                e.getName(),
                e.getAge(),
                new ArrayList<>(
                        e.getPlants().stream()
                                .map(PlantEntity::getId)
                                .collect(Collectors.toList())));
    }

    @Override
    public GardenersModel getModelFromEntity(Set<GardenerEntity> e) {
        return new GardenersModel(e
                .stream()
                .map(en -> new SimpleGardenerModel(en.getId(), en.getName()))
                .collect(Collectors.toList()));
    }
}
