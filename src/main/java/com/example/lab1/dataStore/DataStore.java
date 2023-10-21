package com.example.lab1.dataStore;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.plant.PlantEntity;
import com.example.lab1.species.SpeciesEntity;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@ApplicationScoped
public class DataStore {

    private final Set<GardenerEntity> gardeners = new HashSet<>();

    private final Set<PlantEntity> plants = new HashSet<>();

    private final Set<SpeciesEntity> species = new HashSet<>();

}
