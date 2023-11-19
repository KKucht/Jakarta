package com.example.lab1.plant.factory.api;

import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.models.rest.GetPlantsResponse;

import java.util.List;

public interface EntityToGetPlantsResponse {

    GetPlantsResponse getResponse(List<PlantEntity> e);
}
