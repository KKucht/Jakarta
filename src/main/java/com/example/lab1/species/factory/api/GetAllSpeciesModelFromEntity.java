package com.example.lab1.species.factory.api;


import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.models.AllSpeciesModel;

import java.util.Set;

public interface GetAllSpeciesModelFromEntity {
    AllSpeciesModel getModelFromEntity(Set<SpeciesEntity> e);
}
