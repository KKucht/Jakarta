package com.example.lab1.plant.factory.api;


import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.models.NewPlantModel;

public interface GetEntityFromNewPlantModel {
    PlantEntity getEntityFromModel(NewPlantModel m);

}
