package com.example.lab1.gardener.factory.api;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.models.jsp.GardenerModel;

public interface EntityToGardenerModel {

    GardenerModel getModel(GardenerEntity e);
}
