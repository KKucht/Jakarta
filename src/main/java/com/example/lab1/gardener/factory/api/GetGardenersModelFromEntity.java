package com.example.lab1.gardener.factory.api;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.models.GardenersModel;

import java.util.Set;

public interface GetGardenersModelFromEntity {
    GardenersModel getModelFromEntity(Set<GardenerEntity> e);
}
