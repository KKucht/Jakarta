package com.example.lab1.species.factory.api;


import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.models.SpeciesModel;

public interface GetSpeciesModelFromEntity {
    SpeciesModel getModelFromEntity(SpeciesEntity e);
}
