package com.example.lab1.species;

import com.example.lab1.species.factory.SpeciesFactory;
import com.example.lab1.species.models.AllSpeciesModel;
import com.example.lab1.species.models.NewSpeciesModel;
import com.example.lab1.species.models.SpeciesModel;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    private final SpeciesFactory factory;

    @Inject
    public SpeciesService(SpeciesRepository speciesRepository, SpeciesFactory factory) {
        this.speciesRepository = speciesRepository;
        this.factory = factory;
    }

    public void createSpecies(NewSpeciesModel model) throws IOException {
        if (speciesRepository.find(model.getId()).isPresent()){
            throw new IOException("Species with the specified UUID exits");
        }
        speciesRepository.create(factory.getEntityFromModel(model));
    }

    public SpeciesModel getSpecies(UUID uuid) throws IOException {
        Optional<SpeciesEntity> entity = speciesRepository.find(uuid);
        if (entity.isEmpty()) {
            throw new IOException("Species with the specified UUID not found");
        }
        return factory.getModelFromEntity(entity.get());
    }

    public AllSpeciesModel getAllSpecies() {
        return factory.getModelFromEntity(speciesRepository.findAll());
    }

}
