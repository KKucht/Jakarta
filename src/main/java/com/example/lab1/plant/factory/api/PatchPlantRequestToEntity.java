package com.example.lab1.plant.factory.api;

import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.models.rest.PatchPlantRequest;

public interface PatchPlantRequestToEntity {
    PlantEntity getUpdatedEntity(PlantEntity e, PatchPlantRequest r);
}
