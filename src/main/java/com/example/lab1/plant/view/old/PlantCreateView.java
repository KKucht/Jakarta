package com.example.lab1.plant.view.old;

import com.example.lab1.plant.PlantService;
import com.example.lab1.plant.factory.old.PlantFactory;
import com.example.lab1.plant.models.old.NewPlantModel;
import com.example.lab1.species.SpeciesService;
import com.example.lab1.species.factory.old.SpeciesFactory;
import com.example.lab1.species.models.old.AllSpeciesModel;
import com.example.lab1.species.models.old.SimpleSpeciesModel;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

@ViewScoped
@Named
public class PlantCreateView implements Serializable {

    private final PlantService plantService;

    private final SpeciesService speciesService;

    private final PlantFactory plantFactory;

    private final SpeciesFactory speciesFactory;

    @Getter
    @Setter
    private UUID id;

    @Getter
    private NewPlantModel model = new NewPlantModel();

    @Getter
    private AllSpeciesModel allSpeciesModel;

    @Getter
    @Setter
    private SimpleSpeciesModel species;


    @Inject
    public PlantCreateView(PlantService plantService,SpeciesService speciesService, PlantFactory plantFactory,
                      SpeciesFactory speciesFactory) {
        this.plantService = plantService;
        this.plantFactory = plantFactory;
        this.speciesService = speciesService;
        this.speciesFactory = speciesFactory;
    }

    public void init() {
        allSpeciesModel = speciesFactory.getModelFromEntity(speciesService.getAllSpecies());
        model.setId(UUID.randomUUID());
    }

    public String saveAction() throws IOException {
        System.out.println(species.getId());
        model.setSpecies(species.getId());
        plantService.createPlant(plantFactory.getEntityFromModel(model),model.getSpecies());
        return "/plant/plant_list.xhtml?faces-redirect=true";
    }

    public String cancelAction() {
        return "/plant/plant_list.xhtml?faces-redirect=true";
    }
}
