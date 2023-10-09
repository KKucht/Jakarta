package com.example.lab1.DataStore;

import com.example.lab1.Gardener.Entity.Gardener;
import com.example.lab1.Plant.Entity.Plant;
import com.example.lab1.Species.Entity.Species;

import java.util.HashSet;
import java.util.Set;

public class DataStore {

    private final Set<Gardener> gardeners = new HashSet<>();

    private final Set<Plant> plants = new HashSet<>();

    private final Set<Species> species = new HashSet<>();
}
