package com.example.lab1.gardener.factory.api;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.models.rest.PutGardenerRequest;

import java.util.UUID;

public interface PutGardenerRequestToEntity {
    GardenerEntity getNewEntity(UUID uuid, PutGardenerRequest r);

    GardenerEntity getUpdatedEntity(GardenerEntity e, PutGardenerRequest r);

}
