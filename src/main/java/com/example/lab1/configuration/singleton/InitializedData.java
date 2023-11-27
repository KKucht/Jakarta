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
            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("2f7d4e9c-6f81-11ee-b962-0242ac120002"))
                    .name("misiek")
                    .height(1.75)
                    .plantingDate(LocalDate.of(2001, 3, 30))
                    .keeper(gardeners.get(0))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("2f7d514e-6f81-11ee-b962-0242ac120002"))
                    .name("stefan")
                    .height(12)
                    .plantingDate(LocalDate.of(1998, 6, 21))
                    .keeper(gardeners.get(1))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("2f7d5298-6f81-11ee-b962-0242ac120002"))
                    .name("reksio")
                    .height(3.64)
                    .plantingDate(LocalDate.of(2008, 8, 17))
                    .keeper(gardeners.get(2))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("2f7d53c4-6f81-11ee-b962-0242ac120002"))
                    .name("domek")
                    .height(15)
                    .plantingDate(LocalDate.of(2001, 10, 15))
                    .keeper(gardeners.get(3))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("2f7d54d2-6f81-11ee-b962-0242ac120002"))
                    .name("maly")
                    .height(0.3)
                    .plantingDate(LocalDate.of(2003, 1, 2))
                    .keeper(gardeners.get(0))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("4d15be20-6f8c-11ee-b962-0242ac120002"))
                    .name("duzy")
                    .height(0.8)
                    .plantingDate(LocalDate.of(1999, 8, 4))
                    .keeper(gardeners.get(1))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("4146f2aa-7593-11ee-b962-0242ac120002"))
                    .name("s123")
                    .height(1.75)
                    .plantingDate(LocalDate.of(2001, 3, 30))
                    .keeper(gardeners.get(2))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("4146f7b4-7593-11ee-b962-0242ac120002"))
                    .name("abcdef")
                    .height(12)
                    .plantingDate(LocalDate.of(1998, 6, 21))
                    .keeper(gardeners.get(3))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("4146f9da-7593-11ee-b962-0242ac120002"))
                    .name("aaaaaaa")
                    .height(3.64)
                    .plantingDate(LocalDate.of(2008, 8, 17))
                    .keeper(gardeners.get(0))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("4146fbce-7593-11ee-b962-0242ac120002"))
                    .name("big")
                    .height(15)
                    .plantingDate(LocalDate.of(2001, 10, 15))
                    .keeper(gardeners.get(1))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("4146fd72-7593-11ee-b962-0242ac120002"))
                    .name("dig")
                    .height(0.3)
                    .plantingDate(LocalDate.of(2003, 1, 2))
                    .keeper(gardeners.get(2))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("4146ff2a-7593-11ee-b962-0242ac120002"))
                    .name("sssssa")
                    .height(0.8)
                    .plantingDate(LocalDate.of(1999, 8, 4))
                    .keeper(gardeners.get(3))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("41470556-7593-11ee-b962-0242ac120002"))
                    .name("lol")
                    .height(1.75)
                    .plantingDate(LocalDate.of(2001, 3, 30))
                    .keeper(gardeners.get(0))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("414706e6-7593-11ee-b962-0242ac120002"))
                    .name("xd")
                    .height(12)
                    .plantingDate(LocalDate.of(1998, 6, 21))
                    .keeper(gardeners.get(1))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("41470876-7593-11ee-b962-0242ac120002"))
                    .name("imie")
                    .height(3.64)
                    .plantingDate(LocalDate.of(2008, 8, 17))
                    .keeper(gardeners.get(2))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("414709de-7593-11ee-b962-0242ac120002"))
                    .name("nieimie")
                    .height(15)
                    .plantingDate(LocalDate.of(2001, 10, 15))
                    .keeper(gardeners.get(3))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("41470b50-7593-11ee-b962-0242ac120002"))
                    .name("andrzej")
                    .height(0.3)
                    .plantingDate(LocalDate.of(2003, 1, 2))
                    .keeper(gardeners.get(0))
                    .species(null)
                    .build());

            plants.add(PlantEntity.builder()
                    .id(UUID.fromString("41470cb8-7593-11ee-b962-0242ac120002"))
                    .name("ojojjj")
                    .height(0.8)
                    .plantingDate(LocalDate.of(1999, 8, 4))
                    .keeper(gardeners.get(1))
                    .species(null)
                    .build());

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
