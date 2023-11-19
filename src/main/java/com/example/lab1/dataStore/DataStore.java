package com.example.lab1.dataStore;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.plant.PlantEntity;
import com.example.lab1.species.SpeciesEntity;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Log
@Getter
@ApplicationScoped
public class DataStore {

    private final List<GardenerEntity> gardeners = new ArrayList<>();

    private final List<PlantEntity> plants = new ArrayList<>();

    private final List<SpeciesEntity> species = new ArrayList<>();

}
