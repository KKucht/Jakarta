package com.example.lab1.species;

import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.PlantService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    private final PlantService plantService;

    @Inject
    public SpeciesService(SpeciesRepository speciesRepository, PlantService plantService) {
        this.speciesRepository = speciesRepository;
        this.plantService = plantService;
    }

    @Transactional
    public void createSpecies(SpeciesEntity entity) throws IOException {
        if (speciesRepository.find(entity.getId()).isPresent()){
            throw new IOException("Species with the specified UUID exits");
        }
        speciesRepository.create(entity);
    }

    @Transactional
    public SpeciesEntity getSpecies(UUID uuid) throws IOException {
        Optional<SpeciesEntity> entity = speciesRepository.find(uuid);
        if (entity.isEmpty()) {
            throw new IOException("Species with the specified UUID not found");
        }
        return entity.get();
    }

    @Transactional
    public List<SpeciesEntity> getAllSpecies() {
        return speciesRepository.findAll();
    }

    @Transactional
    public void deleteSpecies(UUID uuid) throws IOException {
        Optional<SpeciesEntity> entity = speciesRepository.find(uuid);
        if (entity.isEmpty()){
            throw new IOException("Species with the specified not UUID exits");
        }
        for (PlantEntity var : entity.get().getPlants()){
            plantService.deletePlant(var.getId());
        }
        speciesRepository.delete(uuid);
    }

    @Transactional
    public void updateSpecies(SpeciesEntity entity) throws IOException {
        if (speciesRepository.find(entity.getId()).isEmpty()){
            throw new IOException("Species with the specified not UUID exits");
        }
        speciesRepository.update(entity.getId(), entity);
    }

}
