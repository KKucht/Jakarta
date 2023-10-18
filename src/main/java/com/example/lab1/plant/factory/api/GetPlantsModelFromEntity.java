package com.example.lab1.plant.factory.api;


import com.example.lab1.plant.models.PlantsModel;
import com.example.lab1.species.SpeciesEntity;

import java.util.Set;

public interface GetPlantsModelFromEntity {
    PlantsModel getModelFromEntity(Set<SpeciesEntity> e);
}
