package com.example.lab1.plant.factory.api;

import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.models.rest.GetPlantResponse;

public interface EntityToGetPlantResponse {
    GetPlantResponse getResponse(PlantEntity e);
}
