package com.example.lab1.species.factory.old.api;


import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.models.old.AllSpeciesModel;

import java.util.Set;

public interface GetAllSpeciesModelFromEntity {
    AllSpeciesModel getModelFromEntity(Set<SpeciesEntity> e);
}
