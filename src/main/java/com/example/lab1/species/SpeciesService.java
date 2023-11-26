package com.example.lab1.species;

import com.example.lab1.gardener.GardenerRoles;
import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.PlantService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    private PlantService plantService;

    @EJB
    public void setPlantService(PlantService plantService) {
        this.plantService = plantService;
    }

    @Inject
    public SpeciesService(SpeciesRepository speciesRepository, PlantService plantService) {
        this.speciesRepository = speciesRepository;
        this.plantService = plantService;
    }

    @RolesAllowed(GardenerRoles.ADMIN)
    public void createSpecies(SpeciesEntity entity) throws IOException {
        if (speciesRepository.find(entity.getId()).isPresent()) {
            throw new IOException("Species with the specified UUID exits");
        }
        speciesRepository.create(entity);
    }

    @RolesAllowed(GardenerRoles.USER)
    public SpeciesEntity getSpecies(UUID uuid) throws IOException {
        Optional<SpeciesEntity> entity = speciesRepository.find(uuid);
        if (entity.isEmpty()) {
            throw new IOException("Species with the specified UUID not found");
        }
        return entity.get();
    }

    @RolesAllowed(GardenerRoles.USER)
    public List<SpeciesEntity> getAllSpecies() {
        return speciesRepository.findAll();
    }

    @RolesAllowed(GardenerRoles.ADMIN)
    public void deleteSpecies(UUID uuid) throws IOException {
        Optional<SpeciesEntity> entity = speciesRepository.find(uuid);
        if (entity.isEmpty()) {
            throw new IOException("Species with the specified not UUID exits");
        }
        for (PlantEntity var : entity.get().getPlants()) {
            plantService.deletePlant(var.getId());
        }
        speciesRepository.delete(uuid);
    }

    @RolesAllowed(GardenerRoles.ADMIN)
    public void updateSpecies(SpeciesEntity entity) throws IOException {
        if (speciesRepository.find(entity.getId()).isEmpty()) {
            throw new IOException("Species with the specified not UUID exits");
        }
        speciesRepository.update(entity.getId(), entity);
    }

}
