package com.example.lab1.species.factory.old.api;


import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.models.old.NewSpeciesModel;

public interface GetEntityFromNewSpeciesModel {
    SpeciesEntity getEntityFromModel(NewSpeciesModel m);

}
