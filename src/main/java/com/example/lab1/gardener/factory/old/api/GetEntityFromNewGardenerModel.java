package com.example.lab1.gardener.factory.old.api;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.models.old.NewGardenerModel;

public interface GetEntityFromNewGardenerModel {
    GardenerEntity getEntityFromModel(NewGardenerModel m);

}
