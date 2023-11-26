package com.example.lab1.species.factory;

import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.factory.api.EntityToGetAllSpeciesResponse;
import com.example.lab1.species.factory.api.EntityToGetSpeciesResponse;
import com.example.lab1.species.factory.api.PatchSpeciesRequestToEntity;
import com.example.lab1.species.factory.api.PutSpeciesRequestToEntity;
import com.example.lab1.species.models.rest.GetAllSpeciesResponse;
import com.example.lab1.species.models.rest.GetSpeciesResponse;
import com.example.lab1.species.models.rest.PatchSpeciesRequest;
import com.example.lab1.species.models.rest.PutSpeciesRequest;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SpeciesFactory implements EntityToGetSpeciesResponse, EntityToGetAllSpeciesResponse, PatchSpeciesRequestToEntity,
        PutSpeciesRequestToEntity {

    @Override
    public GetAllSpeciesResponse getResponse(List<SpeciesEntity> e) {
        return GetAllSpeciesResponse.builder()
                .allSpecies(e.stream()
                        .map(species -> GetAllSpeciesResponse.Species.builder()
                                .id(species.getId())
                                .name(species.getName())
                                .build())
                        .toList())
                .build();
    }

    @Override
    public GetSpeciesResponse getResponse(SpeciesEntity e) {
        return GetSpeciesResponse.builder()
                .name(e.getName())
                .price(e.getPrice())
                .type(e.getType())
                .id(e.getId())
                .build();
    }

    @Override
    public SpeciesEntity getUpdatedEntity(SpeciesEntity e, PatchSpeciesRequest r) {
        return SpeciesEntity.builder()
                .id(e.getId())
                .type(e.getType())
                .price(r.getPrice())
                .name(r.getName())
                .plants(e.getPlants())
                .build();
    }

    @Override
    public SpeciesEntity getNewEntity(UUID uuid, PutSpeciesRequest r) {
        return SpeciesEntity.builder()
                .id(uuid)
                .price(r.getPrice())
                .type(r.getType())
                .name(r.getName())
                .plants(new ArrayList<>())
                .build();
    }

    @Override
    public SpeciesEntity getUpdatedEntity(SpeciesEntity e, PutSpeciesRequest r) {
        return SpeciesEntity.builder()
                .id(e.getId())
                .type(r.getType())
                .price(r.getPrice())
                .name(r.getName())
                .plants(e.getPlants())
                .build();
    }
}
