package com.example.lab1.dataStore;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.GardenerService;
import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.PlantService;
import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.SpeciesService;
import com.example.lab1.species.Type;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@ApplicationScoped
public class InitializedData {

    private final GardenerService gardenerService;

    private final PlantService plantService;

    private final SpeciesService speciesService;

    private final RequestContextController requestContextController;

    @Inject
    public InitializedData(GardenerService gardenerService, PlantService plantService, SpeciesService speciesService,
                           RequestContextController requestContextController) {
        this.gardenerService = gardenerService;
        this.plantService = plantService;
        this.speciesService = speciesService;
        this.requestContextController = requestContextController;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    @SneakyThrows
    private void init() {
        requestContextController.activate();

        ArrayList<GardenerEntity> gardeners = new ArrayList<>();
        gardeners.add(new GardenerEntity(UUID.fromString("6741bc76-a975-41dd-9bb7-68262ef8c2f2"), "Stefan",
                27,  new ArrayList<>()));
        gardeners.add(new GardenerEntity(UUID.fromString("62e5abed-076c-4f90-9a35-efa6eadd13fe"), "Wojciech",
                34,  new ArrayList<>()));
        gardeners.add(new GardenerEntity(UUID.fromString("17410c56-a9b2-4c15-a085-67de10e65aca"), "Milosz",
                36,  new ArrayList<>()));
        gardeners.add(new GardenerEntity(UUID.fromString("423c3a58-8777-4baf-baa5-e230372d18f4"), "Agata",
                83,  new ArrayList<>()));

        for (GardenerEntity gardener : gardeners){
            gardenerService.createGardener(gardener);
        }

        ArrayList<SpeciesEntity> species = new ArrayList<>();
        species.add(new SpeciesEntity(UUID.fromString("cc15811e-6f7e-11ee-b962-0242ac120002"), "Brzoza", Type.TREE,
                50, new ArrayList<>()));
        species.add(new SpeciesEntity(UUID.fromString("405bdf00-6f7f-11ee-b962-0242ac120002"), "Dąb", Type.TREE,
                78, new ArrayList<>()));
        species.add(new SpeciesEntity(UUID.fromString("4fe18e8e-6f7f-11ee-b962-0242ac120002"), "Róża", Type.FLOWER,
                150, new ArrayList<>()));

        for (SpeciesEntity sp : species){
            speciesService.createSpecies(sp);
        }

        ArrayList<PlantEntity> plants = new ArrayList<>();
        plants.add(new PlantEntity(UUID.fromString("2f7d4e9c-6f81-11ee-b962-0242ac120002"), "misiek", 1.75,
                LocalDateTime.of(2001, 3, 30, 0, 0 , 0), null, null));
        plants.add(new PlantEntity(UUID.fromString("2f7d514e-6f81-11ee-b962-0242ac120002"), "stefan", 12,
                LocalDateTime.of(1998,6, 21, 0, 0 , 0), null, null));
        plants.add(new PlantEntity(UUID.fromString("2f7d5298-6f81-11ee-b962-0242ac120002"), "reksio", 3.64,
                LocalDateTime.of(2008,8, 17, 0, 0 , 0), null, null));
        plants.add(new PlantEntity(UUID.fromString("2f7d53c4-6f81-11ee-b962-0242ac120002"), "domek", 15,
                LocalDateTime.of(2001,10, 15, 0, 0 , 0), null, null));
        plants.add(new PlantEntity(UUID.fromString("2f7d54d2-6f81-11ee-b962-0242ac120002"), "maly", 0.3,
                LocalDateTime.of(2003,1, 2, 0, 0 , 0), null, null));
        plants.add(new PlantEntity(UUID.fromString("4d15be20-6f8c-11ee-b962-0242ac120002"), "duzy", 0.8,
                LocalDateTime.of(1999,8, 4, 0, 0 , 0), null, null));

        for (int i = 0 ; i < plants.size(); i++){
            if (i < 2)
                plantService.createPlant(plants.get(i), UUID.fromString("cc15811e-6f7e-11ee-b962-0242ac120002"));
            else if (i < 4)
                plantService.createPlant(plants.get(i), UUID.fromString("405bdf00-6f7f-11ee-b962-0242ac120002"));
            else
                plantService.createPlant(plants.get(i), UUID.fromString("4fe18e8e-6f7f-11ee-b962-0242ac120002"));
        }
        requestContextController.deactivate();
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            if (is != null) {
                return is.readAllBytes();
            } else {
                throw new IllegalStateException("Unable to get resource %s".formatted(name));
            }
        }
    }

}
