package com.example.lab1.plant.factory.old;

import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.factory.old.api.GetEntityFromNewPlantModel;
import com.example.lab1.plant.factory.old.api.GetEntityFromPlantModel;
import com.example.lab1.plant.factory.old.api.GetPlantModelFromEntity;
import com.example.lab1.plant.factory.old.api.GetPlantsModelFromEntity;
import com.example.lab1.plant.models.old.NewPlantModel;
import com.example.lab1.plant.models.old.PlantModel;
import com.example.lab1.plant.models.old.PlantsModel;
import com.example.lab1.plant.models.old.SimplePlantModel;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class PlantFactory implements GetEntityFromPlantModel, GetEntityFromNewPlantModel, GetPlantModelFromEntity, GetPlantsModelFromEntity {
    @Override
    public PlantEntity getEntityFromModel(NewPlantModel m) {
        return new PlantEntity(
                m.getId(),
                m.getName(),
                m.getHeight(),
                m.getPlantingDate(),
                null, null);
    }

    @Override
    public PlantModel getModelFromEntity(PlantEntity e) {
        return new PlantModel(
                e.getId(),
                e.getName(),
                e.getHeight(),
                e.getPlantingDate(),
                (e.getKeeper() != null) ? e.getKeeper().getId() : null,
                e.getSpecies().getId());
    }

    @Override
    public PlantsModel getModelFromEntity(Set<PlantEntity> e) {
        return new PlantsModel(e
                .stream()
                .map(en -> new SimplePlantModel(en.getId(), en.getName()))
                .collect(Collectors.toList()));
    }

    @Override
    public PlantEntity getEntityFromModel(PlantModel m) {
        return new PlantEntity(
                m.getId(),
                m.getName(),
                m.getHeight(),
                m.getPlantingDate(),
                null, null);
    }
}
