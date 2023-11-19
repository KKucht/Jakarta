package com.example.lab1.species.factory.old;

import com.example.lab1.plant.PlantEntity;
import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.factory.old.api.GetAllSpeciesModelFromEntity;
import com.example.lab1.species.factory.old.api.GetEntityFromNewSpeciesModel;
import com.example.lab1.species.factory.old.api.GetSpeciesModelFromEntity;
import com.example.lab1.species.models.old.AllSpeciesModel;
import com.example.lab1.species.models.old.NewSpeciesModel;
import com.example.lab1.species.models.old.SimpleSpeciesModel;
import com.example.lab1.species.models.old.SpeciesModel;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SpeciesFactory implements GetSpeciesModelFromEntity, GetAllSpeciesModelFromEntity, GetEntityFromNewSpeciesModel {

    @Override
    public SpeciesEntity getEntityFromModel(NewSpeciesModel m) {
        return new SpeciesEntity(
                m.getId(),
                m.getName(),
                m.getType(),
                m.getPrice(),
                new ArrayList<>());
    }

    @Override
    public SpeciesModel getModelFromEntity(SpeciesEntity e) {
        return new SpeciesModel(
                e.getId(),
                e.getName(),
                e.getType(),
                e.getPrice(),
                new ArrayList<>(
                        e.getPlants().stream()
                                .map(PlantEntity::getId)
                                .collect(Collectors.toList())));
    }

    @Override
    public AllSpeciesModel getModelFromEntity(List<SpeciesEntity> e) {
        return new AllSpeciesModel(e
                .stream()
                .map(en -> new SimpleSpeciesModel(en.getId(), en.getName(), en.getType()))
                .collect(Collectors.toList()));
    }
}
