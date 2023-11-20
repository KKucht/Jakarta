package com.example.lab1.plant;

import com.example.lab1.gardener.GardenerRepository;
import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.SpeciesRepository;
import com.example.lab1.species.SpeciesService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Optional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class PlantService {

    private final PlantRepository plantRepository;

    private final SpeciesRepository speciesRepository;

    private final GardenerRepository gardenerRepository;

    private final SpeciesService speciesService;

    @Inject
    public PlantService(GardenerRepository gardenerRepository, PlantRepository plantRepository, SpeciesService speciesService, SpeciesRepository speciesRepository) {
        this.plantRepository = plantRepository;
        this.speciesService = speciesService;
        this.speciesRepository = speciesRepository;
        this.gardenerRepository = gardenerRepository;
    }

    @Transactional
    public void createPlant(PlantEntity entity, UUID speciesId) throws IOException {
        if (plantRepository.find(entity.getId()).isPresent()){
            throw new IOException("Plant with the specified UUID exits");
        }
        if (speciesRepository.find(speciesId).isEmpty())
            throw new IOException("Species with the specified UUID not found");
        SpeciesEntity entity1 = speciesService.getSpecies(speciesId);
        entity.setSpecies(entity1);
        entity1.getPlants().add(entity);
        plantRepository.create(entity);
        speciesRepository.update(entity1.getId(),entity1);
    }

    @Transactional
    public PlantEntity getPlant(UUID uuid) throws IOException {
        Optional<PlantEntity> entity = plantRepository.find(uuid);
        if (entity.isEmpty()) {
            throw new IOException("Plant with the specified UUID not found");
        }
        return entity.get();
    }

    @Transactional
    public List<PlantEntity> getPlants() {
        return plantRepository.findAll();
    }

    @Transactional
    public List<PlantEntity> getSpeciesPlants(UUID speciesID) throws IOException {
        if (speciesRepository.find(speciesID).isEmpty()){
            throw new IOException("Species with the specified UUID not found");
        }
        return plantRepository.findAll().stream()
                .filter(entity -> entity.getSpecies().getId().equals(speciesID))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePlant(UUID uuid) throws IOException {
        if (plantRepository.find(uuid).isEmpty()){
            throw new IOException("Plant with the specified not UUID exits");
        }
        plantRepository.delete(uuid);
    }

    @Transactional
    public void updatePlant(PlantEntity entity, UUID keeper, UUID species) throws IOException {
        if (plantRepository.find(entity.getId()).isEmpty()){
            throw new IOException("Plant with the specified not UUID exits");
        }
        if (keeper != null){
            if (gardenerRepository.find(keeper).isEmpty())
                throw new IOException("Gardener with the specified UUID not found");
            entity.setKeeper(gardenerRepository.find(keeper).get());
        }
        if (species != null){
            if (speciesRepository.find(species).isEmpty())
                throw new IOException("Species with the specified UUID not found");
            entity.setSpecies(speciesRepository.find(species).get());
        }
        plantRepository.update(entity.getId(), entity);
    }
}
