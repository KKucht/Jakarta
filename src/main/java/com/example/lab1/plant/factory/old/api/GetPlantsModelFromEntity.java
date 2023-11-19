package com.example.lab1.plant.factory.old.api;


import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.models.old.PlantsModel;

import java.util.List;

public interface GetPlantsModelFromEntity {
    PlantsModel getModelFromEntity(List<PlantEntity> e);
}
