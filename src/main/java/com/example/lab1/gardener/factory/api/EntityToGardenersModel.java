package com.example.lab1.gardener.factory.api;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.models.jsp.GardenersModel;

import java.util.List;

public interface EntityToGardenersModel {

    GardenersModel getModel(List<GardenerEntity> e);
}
