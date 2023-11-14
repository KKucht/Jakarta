package com.example.lab1.species.factory.api;

import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.models.rest.GetSpeciesResponse;


public interface EntityToGetSpeciesResponse {

    GetSpeciesResponse getResponse(SpeciesEntity e);
}
