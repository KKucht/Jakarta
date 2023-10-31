package com.example.lab1.plant.factory.api;

import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.models.PlantModel;

public interface GetEntityFromPlantModel {

    PlantEntity getEntityFromModel(PlantModel m);
}