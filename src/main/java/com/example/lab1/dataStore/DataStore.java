package com.example.lab1.dataStore;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.plant.PlantEntity;
import com.example.lab1.species.SpeciesEntity;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Singleton
public class DataStore {

    public DataStore() {
        gardeners.add(new GardenerEntity(UUID.fromString("6741bc76-a975-41dd-9bb7-68262ef8c2f2"), "Stefan",
                27,  new ArrayList<>()));
        gardeners.add(new GardenerEntity(UUID.fromString("62e5abed-076c-4f90-9a35-efa6eadd13fe"), "Wojciech",
                34,  new ArrayList<>()));
        gardeners.add(new GardenerEntity(UUID.fromString("17410c56-a9b2-4c15-a085-67de10e65aca"), "Milosz",
                36,  new ArrayList<>()));
        gardeners.add(new GardenerEntity(UUID.fromString("423c3a58-8777-4baf-baa5-e230372d18f4"), "Agata",
                83,  new ArrayList<>()));
    }

    private final Set<GardenerEntity> gardeners = new HashSet<>();

    private final Set<PlantEntity> plants = new HashSet<>();

    private final Set<SpeciesEntity> species = new HashSet<>();

}
