package com.example.lab1.plant;

import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.SpeciesRepository;
import com.example.lab1.species.SpeciesService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class PlantService {

    private final PlantRepository plantRepository;

    private final SpeciesRepository speciesRepository;

    private final SpeciesService speciesService;

    @Inject
    public PlantService(PlantRepository plantRepository, SpeciesService speciesService, SpeciesRepository speciesRepository) {
        this.plantRepository = plantRepository;
        this.speciesService = speciesService;
        this.speciesRepository = speciesRepository;
    }

    public void createPlant(PlantEntity entity, UUID speciesId) throws IOException {
        if (plantRepository.find(entity.getId()).isPresent()){
            throw new IOException("Plant with the specified UUID exits");
        }
        SpeciesEntity entity1 = speciesService.getSpecies(speciesId);
        entity.setSpecies(entity1);
        entity1.getPlants().add(entity);
        plantRepository.create(entity);
        speciesRepository.update(entity1.getId(),entity1);
    }

    public PlantEntity getPlant(UUID uuid) throws IOException {
        Optional<PlantEntity> entity = plantRepository.find(uuid);
        if (entity.isEmpty()) {
            throw new IOException("Plant with the specified UUID not found");
        }
        return entity.get();
    }

    public Set<PlantEntity> getPlants() {
        return plantRepository.findAll();
    }
}
