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

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PlantFactory implements GetEntityFromPlantModel, GetEntityFromNewPlantModel, GetPlantModelFromEntity, GetPlantsModelFromEntity {
    @Override
    public PlantEntity getEntityFromModel(NewPlantModel m) {
        return PlantEntity.builder()
                .id(m.getId())
                .name(m.getName())
                .height(m.getHeight())
                .plantingDate(m.getPlantingDate())
                .keeper(null)
                .species(null)
                .build();
    }

    @Override
    public PlantModel getModelFromEntity(PlantEntity e) {
        return PlantModel.builder()
                .id(e.getId())
                .height(e.getHeight())
                .name(e.getName())
                .plantingDate(e.getPlantingDate())
                .keeper((e.getKeeper() != null) ? e.getKeeper().getId() : null)
                .species(e.getSpecies().getId())
                .version(e.getVersion())
                .creationDateTime(e.getCreationDateTime())
                .lastUpdateDateTime(e.getLastUpdateDateTime())
                .build();
    }

    @Override
    public PlantsModel getModelFromEntity(List<PlantEntity> e) {
        return new PlantsModel(e
                .stream()
                .map(en -> new SimplePlantModel(en.getId(), en.getName(), en.getVersion(), en.getCreationDateTime(), en.getLastUpdateDateTime()))
                .collect(Collectors.toList()));
    }

    @Override
    public PlantEntity getEntityFromModel(PlantModel m, PlantEntity e) {
        return PlantEntity.builder()
                .id(m.getId())
                .name(m.getName())
                .height(m.getHeight())
                .plantingDate(m.getPlantingDate())
                .keeper(e.getKeeper())
                .species(e.getSpecies())
                .version(m.getVersion())
                .creationDateTime(e.getCreationDateTime())
                .lastUpdateDateTime(e.getLastUpdateDateTime())
                .build();
    }
}
