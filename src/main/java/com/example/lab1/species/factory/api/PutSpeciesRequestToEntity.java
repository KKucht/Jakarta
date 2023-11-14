package com.example.lab1.species.factory.api;

import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.models.rest.PatchSpeciesRequest;
import com.example.lab1.species.models.rest.PutSpeciesRequest;

import java.util.UUID;

public interface PutSpeciesRequestToEntity {
    SpeciesEntity getNewEntity(UUID uuid, PutSpeciesRequest r);

    SpeciesEntity getUpdatedEntity(SpeciesEntity e, PutSpeciesRequest r);
}
