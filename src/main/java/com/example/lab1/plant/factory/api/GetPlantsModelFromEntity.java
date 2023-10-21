package com.example.lab1.plant.factory.api;


import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.models.PlantsModel;

import java.util.Set;

public interface GetPlantsModelFromEntity {
    PlantsModel getModelFromEntity(Set<PlantEntity> e);
}
