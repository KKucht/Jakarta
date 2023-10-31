package com.example.lab1.plant.factory.old.api;


import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.models.old.NewPlantModel;

public interface GetEntityFromNewPlantModel {
    PlantEntity getEntityFromModel(NewPlantModel m);

}
