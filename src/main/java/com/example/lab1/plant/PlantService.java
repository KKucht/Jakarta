package com.example.lab1.plant;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.GardenerRepository;
import com.example.lab1.gardener.GardenerRoles;
import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.SpeciesRepository;
import com.example.lab1.species.SpeciesService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class PlantService {

    private final PlantRepository plantRepository;

    private final SpeciesRepository speciesRepository;

    private final GardenerRepository gardenerRepository;

    private final SecurityContext securityContext;

    private SpeciesService speciesService;

    @EJB
    public void setSpeciesService(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @Inject
    public PlantService(GardenerRepository gardenerRepository,
                        PlantRepository plantRepository,
                        SpeciesRepository speciesRepository,
                        @SuppressWarnings("CdiInjectionPointsInspection") SecurityContext securityContext) {
        this.plantRepository = plantRepository;
        this.speciesRepository = speciesRepository;
        this.gardenerRepository = gardenerRepository;
        this.securityContext = securityContext;
    }

    @RolesAllowed(GardenerRoles.USER)
    public void createPlant(PlantEntity entity, UUID speciesId) throws IOException {
        if (plantRepository.find(entity.getId()).isPresent()) {
            throw new IOException("Plant with the specified UUID exits");
        }
        if (speciesRepository.find(speciesId).isEmpty())
            throw new IOException("Species with the specified UUID not found");
        if (entity.getKeeper() != null) {
            if (gardenerRepository.find(entity.getKeeper().getId()).isEmpty()) {
                throw new IOException("Gardener with the specified UUID not found");
            }
        }
        else {
            Optional<GardenerEntity> g = gardenerRepository.findByLogin(securityContext.getCallerPrincipal().getName());
            if (g.isEmpty())
                throw new IOException("Gardener with the specified UUID not found");
            entity.setKeeper(g.get());
        }
        SpeciesEntity entity1 = speciesService.getSpecies(speciesId);
        entity.setSpecies(entity1);
        entity1.getPlants().add(entity);
        plantRepository.create(entity);
        speciesRepository.update(entity1.getId(), entity1);
    }

    @RolesAllowed(GardenerRoles.USER)
    public PlantEntity getPlant(UUID uuid) throws IOException {
        Optional<PlantEntity> entity;
        if (securityContext.isCallerInRole(GardenerRoles.ADMIN)) {
            entity = plantRepository.find(uuid);
            if (entity.isEmpty()) {
                throw new IOException("Plant with the specified UUID not found");
            }
            return entity.get();
        }
        else {
            Optional<GardenerEntity> g = gardenerRepository.findByLogin(securityContext.getCallerPrincipal().getName());
            if (g.isEmpty())
                throw new IOException("Gardener with the specified UUID not found");
            return getPlant(g.get(),uuid);
        }
    }

    @RolesAllowed(GardenerRoles.USER)
    public PlantEntity getPlant(GardenerEntity g, UUID uuid) throws IOException {
        Optional<PlantEntity> entity = plantRepository.findByIdAndGardener(uuid,g);
        if (entity.isEmpty()) {
            throw new IOException("Plant with the specified UUID not found");
        }
        return entity.get();
    }

    @RolesAllowed(GardenerRoles.USER)
    public List<PlantEntity> getPlants() throws IOException {
        if (securityContext.isCallerInRole(GardenerRoles.ADMIN)) {
            return plantRepository.findAll();
        }
        Optional<GardenerEntity> g = gardenerRepository.findByLogin(securityContext.getCallerPrincipal().getName());
        if (g.isEmpty())
            throw new IOException("Gardener with the specified UUID not found");
        return plantRepository.findAllByGardener(g.get());
    }

    @RolesAllowed(GardenerRoles.USER)
    public List<PlantEntity> getSpeciesPlants(UUID speciesID) throws IOException {
        if (speciesRepository.find(speciesID).isEmpty()) {
            throw new IOException("Species with the specified UUID not found");
        }
        if (securityContext.isCallerInRole(GardenerRoles.ADMIN))
            return plantRepository.findAll().stream()
                    .filter(entity -> entity.getSpecies().getId().equals(speciesID))
                    .collect(Collectors.toList());
        Optional<GardenerEntity> g = gardenerRepository.findByLogin(securityContext.getCallerPrincipal().getName());
        if (g.isEmpty())
            throw new IOException("Gardener with the specified UUID not found");
        return plantRepository.findAllByGardener(g.get()).stream()
                .filter(entity -> entity.getSpecies().getId().equals(speciesID))
                .collect(Collectors.toList());
    }

    @RolesAllowed(GardenerRoles.USER)
    public void deletePlant(UUID uuid) throws IOException {
        if (plantRepository.find(uuid).isEmpty()) {
            throw new IOException("Plant with the specified not UUID exits");
        }
        checkAdminRoleOrOwner(plantRepository.find(uuid));
        plantRepository.delete(uuid);
    }

    @RolesAllowed(GardenerRoles.USER)
    public void updatePlant(PlantEntity entity, UUID keeper, UUID species) throws IOException {
        if (plantRepository.find(entity.getId()).isEmpty()) {
            throw new IOException("Plant with the specified not UUID exits");
        }
        if (keeper != null) {
            if (gardenerRepository.find(keeper).isEmpty())
                throw new IOException("Gardener with the specified UUID not found");
            entity.setKeeper(gardenerRepository.find(keeper).get());
        }
        if (species != null) {
            if (speciesRepository.find(species).isEmpty())
                throw new IOException("Species with the specified UUID not found");
            entity.setSpecies(speciesRepository.find(species).get());
        }
        checkAdminRoleOrOwner(plantRepository.find(entity.getId()));
        plantRepository.update(entity.getId(), entity);
    }

    @RolesAllowed(GardenerRoles.ADMIN)
    public List<PlantEntity> getGardenerPlants(UUID gardenerID) throws IOException {
        if (gardenerRepository.find(gardenerID).isEmpty()) {
            throw new IOException("Gardener with the specified UUID not found");
        }
        return plantRepository.findAll().stream()
                .filter(entity -> entity.getKeeper().getId().equals(gardenerID))
                .collect(Collectors.toList());
    }

    private void checkAdminRoleOrOwner(Optional<PlantEntity> plant) throws EJBAccessException {
        if (securityContext.isCallerInRole(GardenerRoles.ADMIN)) {
            return;
        }
        if (securityContext.isCallerInRole(GardenerRoles.USER)
                && plant.isPresent()
                && plant.get().getKeeper().getLogin().equals(securityContext.getCallerPrincipal().getName())) {
            return;
        }
        throw new EJBAccessException("Caller not authorized.");
    }
}
