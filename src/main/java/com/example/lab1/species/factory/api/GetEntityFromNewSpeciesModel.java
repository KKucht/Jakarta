package com.example.lab1.species.factory.api;


import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.models.NewSpeciesModel;

public interface GetEntityFromNewSpeciesModel {
    SpeciesEntity getEntityFromModel(NewSpeciesModel m);

}
