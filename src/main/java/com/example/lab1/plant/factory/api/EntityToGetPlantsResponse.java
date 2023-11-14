package com.example.lab1.plant.factory.api;

import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.models.rest.GetPlantsResponse;

import java.util.Set;

public interface EntityToGetPlantsResponse {

    GetPlantsResponse getResponse(Set<PlantEntity> e);
}
