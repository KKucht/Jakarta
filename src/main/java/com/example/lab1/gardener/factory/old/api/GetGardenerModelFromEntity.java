package com.example.lab1.gardener.factory.old.api;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.models.old.GardenerModel;

public interface GetGardenerModelFromEntity {
    GardenerModel getModelFromEntity(GardenerEntity e);
}
