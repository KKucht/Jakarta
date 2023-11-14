package com.example.lab1.plant.factory;

import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.factory.api.EntityToGetPlantResponse;
import com.example.lab1.plant.factory.api.EntityToGetPlantsResponse;
import com.example.lab1.plant.factory.api.PatchPlantRequestToEntity;
import com.example.lab1.plant.factory.api.PutPlantRequestToEntity;
import com.example.lab1.plant.models.rest.GetPlantResponse;
import com.example.lab1.plant.models.rest.GetPlantsResponse;
import com.example.lab1.plant.models.rest.PatchPlantRequest;
import com.example.lab1.plant.models.rest.PutPlantRequest;
import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.models.rest.GetAllSpeciesResponse;
import com.example.lab1.species.models.rest.GetSpeciesResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class PlantFactory implements EntityToGetPlantResponse, EntityToGetPlantsResponse, PatchPlantRequestToEntity, PutPlantRequestToEntity {

    @Override
    public GetPlantResponse getResponse(PlantEntity e) {
        return GetPlantResponse.builder()
                .name(e.getName())
                .id(e.getId())
                .height(e.getHeight())
                .plantingDate(e.getPlantingDate())
                .species(GetPlantResponse.Species.builder()
                        .id(e.getSpecies().getId())
                        .name(e.getSpecies().getName())
                        .build())
                .build();
    }

    @Override
    public GetPlantsResponse getResponse(Set<PlantEntity> e) {
        return GetPlantsResponse.builder()
                .plants(e.stream()
                        .map(plant -> GetPlantsResponse.Plant.builder()
                                .id(plant.getId())
                                .name(plant.getName())
                                .build())
                        .toList())
                .build();
    }

    @Override
    public PlantEntity getUpdatedEntity(PlantEntity e, PatchPlantRequest r) {
        return PlantEntity.builder()
                .id(e.getId())
                .height(r.getHeight())
                .keeper(e.getKeeper())
                .name(r.getName())
                .plantingDate(e.getPlantingDate())
                .species(e.getSpecies())
                .build();
    }

    @Override
    public PlantEntity getNewEntity(UUID uuid, PutPlantRequest r) {
        return PlantEntity.builder()
                .id(uuid)
                .height(r.getHeight())
                .keeper(null)
                .name(r.getName())
                .plantingDate(r.getPlantingDate())
                .species(null)
                .build();
    }

    @Override
    public PlantEntity getUpdatedEntity(PlantEntity e, PutPlantRequest r) {
        return PlantEntity.builder()
                .id(e.getId())
                .height(r.getHeight())
                .keeper(e.getKeeper())
                .name(r.getName())
                .plantingDate(r.getPlantingDate())
                .species(e.getSpecies())
                .build();
    }
}
