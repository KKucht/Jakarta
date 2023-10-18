package com.example.lab1.gardener.factory.api;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.models.NewGardenerModel;

public interface GetEntityFromNewGardenerModel {
    GardenerEntity getEntityFromModel(NewGardenerModel m);

}
