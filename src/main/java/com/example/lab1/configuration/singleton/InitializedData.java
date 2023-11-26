package com.example.lab1.configuration.singleton;

import com.example.lab1.gardener.GardenerEntity;
import com.example.lab1.gardener.GardenerRoles;
import com.example.lab1.gardener.GardenerService;
import com.example.lab1.plant.PlantEntity;
import com.example.lab1.plant.PlantService;
import com.example.lab1.species.SpeciesEntity;
import com.example.lab1.species.SpeciesService;
import com.example.lab1.species.Type;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RunAs;
import jakarta.ejb.*;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
@NoArgsConstructor
@DependsOn("InitializeAdminService")
@DeclareRoles({GardenerRoles.ADMIN, GardenerRoles.USER})
@RunAs(GardenerRoles.ADMIN)
@Log
public class InitializedData {

    private GardenerService gardenerService;

    @EJB
    public void setGardenerService(GardenerService gardenerService) {
        this.gardenerService = gardenerService;
    }

    private PlantService plantService;

    @EJB
    public void setPlantService(PlantService plantService) {
        this.plantService = plantService;
    }

    private SpeciesService speciesService;

    @EJB
    public void setSpeciesService(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @PostConstruct
    @SneakyThrows
    private void init() {
        try {
            ArrayList<GardenerEntity> gardeners = new ArrayList<>();
            gardeners.add(GardenerEntity.builder()
                    .id(UUID.fromString("40b12d28-8bec-11ee-b9d1-0242ac120002"))
                    .login("admin")
                    .name("Stefan")
                    .age(27)
                    .password("admin")
                    .roles(List.of(GardenerRoles.USER,GardenerRoles.ADMIN))
                    .build());
            gardeners.add(GardenerEntity.builder()
                    .id(UUID.fromString("46257052-8bec-11ee-b9d1-0242ac120002"))
                    .login("user1")
                    .name("Wojciech")
                    .age(34)
                    .password("user1")
                    .roles(List.of(GardenerRoles.USER))
                    .build());
            gardeners.add(GardenerEntity.builder()
                    .id(UUID.fromString("46257412-8bec-11ee-b9d1-0242ac120002"))
                    .login("user2")
                    .name("Milosz")
                    .age(36)
                    .password("user2")
                    .roles(List.of(GardenerRoles.USER))
                    .build());
            gardeners.add(GardenerEntity.builder()
                    .id(UUID.fromString("6741bc76-a975-41dd-9bb7-68262ef8c2f2"))
                    .login("user3")
                    .name("Agata")
                    .age(83)
                    .password("user3")
                    .roles(List.of(GardenerRoles.USER))
                    .build());

            for (GardenerEntity gardener : gardeners) {
                gardenerService.createGardener(gardener);
            }

            ArrayList<SpeciesEntity> species = new ArrayList<>();
            species.add(new SpeciesEntity(UUID.fromString("cc15811e-6f7e-11ee-b962-0242ac120002"), "Brzoza", Type.TREE,
                    50, new ArrayList<>()));
            species.add(new SpeciesEntity(UUID.fromString("405bdf00-6f7f-11ee-b962-0242ac120002"), "Dab", Type.TREE,
                    78, new ArrayList<>()));
            species.add(new SpeciesEntity(UUID.fromString("4fe18e8e-6f7f-11ee-b962-0242ac120002"), "Roza", Type.FLOWER,
                    150, new ArrayList<>()));

            for (SpeciesEntity sp : species) {
                speciesService.createSpecies(sp);
            }

            ArrayList<PlantEntity> plants = new ArrayList<>();
            plants.add(new PlantEntity(UUID.fromString("2f7d4e9c-6f81-11ee-b962-0242ac120002"), "misiek", 1.75,
                    LocalDate.of(2001, 3, 30), gardeners.get(0), null));
            plants.add(new PlantEntity(UUID.fromString("2f7d514e-6f81-11ee-b962-0242ac120002"), "stefan", 12,
                    LocalDate.of(1998, 6, 21), gardeners.get(1), null));
            plants.add(new PlantEntity(UUID.fromString("2f7d5298-6f81-11ee-b962-0242ac120002"), "reksio", 3.64,
                    LocalDate.of(2008, 8, 17), gardeners.get(2), null));
            plants.add(new PlantEntity(UUID.fromString("2f7d53c4-6f81-11ee-b962-0242ac120002"), "domek", 15,
                    LocalDate.of(2001, 10, 15), gardeners.get(3), null));
            plants.add(new PlantEntity(UUID.fromString("2f7d54d2-6f81-11ee-b962-0242ac120002"), "maly", 0.3,
                    LocalDate.of(2003, 1, 2), gardeners.get(0), null));
            plants.add(new PlantEntity(UUID.fromString("4d15be20-6f8c-11ee-b962-0242ac120002"), "duzy", 0.8,
                    LocalDate.of(1999, 8, 4), gardeners.get(1), null));

            plants.add(new PlantEntity(UUID.fromString("4146f2aa-7593-11ee-b962-0242ac120002"), "s123", 1.75,
                    LocalDate.of(2001, 3, 30), gardeners.get(2), null));
            plants.add(new PlantEntity(UUID.fromString("4146f7b4-7593-11ee-b962-0242ac120002"), "abcdef", 12,
                    LocalDate.of(1998, 6, 21), gardeners.get(3), null));
            plants.add(new PlantEntity(UUID.fromString("4146f9da-7593-11ee-b962-0242ac120002"), "aaaaaaa", 3.64,
                    LocalDate.of(2008, 8, 17), gardeners.get(0), null));
            plants.add(new PlantEntity(UUID.fromString("4146fbce-7593-11ee-b962-0242ac120002"), "big", 15,
                    LocalDate.of(2001, 10, 15), gardeners.get(1), null));
            plants.add(new PlantEntity(UUID.fromString("4146fd72-7593-11ee-b962-0242ac120002"), "dig", 0.3,
                    LocalDate.of(2003, 1, 2), gardeners.get(2), null));
            plants.add(new PlantEntity(UUID.fromString("4146ff2a-7593-11ee-b962-0242ac120002"), "sssssa", 0.8,
                    LocalDate.of(1999, 8, 4), gardeners.get(3), null));
            plants.add(new PlantEntity(UUID.fromString("41470556-7593-11ee-b962-0242ac120002"), "lol", 1.75,
                    LocalDate.of(2001, 3, 30), gardeners.get(0), null));
            plants.add(new PlantEntity(UUID.fromString("414706e6-7593-11ee-b962-0242ac120002"), "xd", 12,
                    LocalDate.of(1998, 6, 21), gardeners.get(1), null));
            plants.add(new PlantEntity(UUID.fromString("41470876-7593-11ee-b962-0242ac120002"), "imie", 3.64,
                    LocalDate.of(2008, 8, 17), gardeners.get(2), null));
            plants.add(new PlantEntity(UUID.fromString("414709de-7593-11ee-b962-0242ac120002"), "nieimie", 15,
                    LocalDate.of(2001, 10, 15), gardeners.get(3), null));
            plants.add(new PlantEntity(UUID.fromString("41470b50-7593-11ee-b962-0242ac120002"), "andrzej", 0.3,
                    LocalDate.of(2003, 1, 2), gardeners.get(0), null));
            plants.add(new PlantEntity(UUID.fromString("41470cb8-7593-11ee-b962-0242ac120002"), "ojojjj", 0.8,
                    LocalDate.of(1999, 8, 4), gardeners.get(1), null));

            for (int i = 0; i < plants.size(); i++) {
                if (i < 2)
                    plantService.createPlant(plants.get(i), UUID.fromString("cc15811e-6f7e-11ee-b962-0242ac120002"));
                else if (i < 8)
                    plantService.createPlant(plants.get(i), UUID.fromString("405bdf00-6f7f-11ee-b962-0242ac120002"));
                else
                    plantService.createPlant(plants.get(i), UUID.fromString("4fe18e8e-6f7f-11ee-b962-0242ac120002"));
            }
        } catch (EJBException | IOException ignored) {

        }
    }

}
