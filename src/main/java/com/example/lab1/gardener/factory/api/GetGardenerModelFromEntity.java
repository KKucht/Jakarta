package com.example.lab1.gardener.factory.api;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.models.GardenerModel;

public interface GetGardenerModelFromEntity {
    GardenerModel getModelFromEntity(GardenerEntity e);
}
