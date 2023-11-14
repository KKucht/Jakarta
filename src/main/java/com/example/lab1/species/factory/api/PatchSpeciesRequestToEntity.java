package com.example.lab1.species.factory.api;

import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.models.rest.PatchSpeciesRequest;

public interface PatchSpeciesRequestToEntity {
    SpeciesEntity getUpdatedEntity(SpeciesEntity e, PatchSpeciesRequest r);
}
