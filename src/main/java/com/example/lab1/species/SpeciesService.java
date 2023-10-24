package com.example.lab1.species;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    @Inject
    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    public void createSpecies(SpeciesEntity entity) throws IOException {
        if (speciesRepository.find(entity.getId()).isPresent()){
            throw new IOException("Species with the specified UUID exits");
        }
        speciesRepository.create(entity);
    }

    public SpeciesEntity getSpecies(UUID uuid) throws IOException {
        Optional<SpeciesEntity> entity = speciesRepository.find(uuid);
        if (entity.isEmpty()) {
            throw new IOException("Species with the specified UUID not found");
        }
        return entity.get();
    }

    public Set<SpeciesEntity> getAllSpecies() {
        return speciesRepository.findAll();
    }

    public void deleteSpecies(UUID uuid) throws IOException {
//        if (speciesRepository.find(entity.getId()).isPresent()){
//            throw new IOException("Species with the specified UUID exits");
//        }
//        speciesRepository.create(entity);
    }

}
