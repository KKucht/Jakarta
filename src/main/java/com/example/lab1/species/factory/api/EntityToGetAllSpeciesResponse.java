package com.example.lab1.species.factory.api;

import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.models.rest.GetAllSpeciesResponse;

import java.util.List;
import java.util.List;

public interface EntityToGetAllSpeciesResponse {

    GetAllSpeciesResponse getResponse(List<SpeciesEntity> e);

}
