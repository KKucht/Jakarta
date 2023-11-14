package com.example.lab1.plant.factory.api;

import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.models.rest.PutPlantRequest;

import java.util.UUID;

public interface PutPlantRequestToEntity {
    PlantEntity getNewEntity(UUID uuid, PutPlantRequest r);

    PlantEntity getUpdatedEntity(PlantEntity e, PutPlantRequest r);
}
