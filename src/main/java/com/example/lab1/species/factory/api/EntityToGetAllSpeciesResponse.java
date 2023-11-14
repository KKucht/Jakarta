package com.example.lab1.species.factory.api;

import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.models.rest.GetAllSpeciesResponse;

import java.util.Set;

public interface EntityToGetAllSpeciesResponse {

    GetAllSpeciesResponse getResponse(Set<SpeciesEntity> e);

}
