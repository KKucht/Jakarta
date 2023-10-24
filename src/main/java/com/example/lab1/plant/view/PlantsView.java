package com.example.lab1.plant.view;

import com.example.lab1.plant.PlantService;
import com.example.lab1.plant.factory.PlantFactory;
import com.example.lab1.plant.models.PlantsModel;
import com.example.lab1.species.SpeciesService;
import com.example.lab1.species.factory.SpeciesFactory;
import com.example.lab1.species.models.SpeciesModel;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
@Named
public class PlantsView {

    private final PlantService plantService;

    private final SpeciesService speciesService;

    private PlantsModel model;

    private final PlantFactory plantFactory;

    private final SpeciesFactory speciesFactory;

    @Getter
    @Setter
    private String speciesId;

    @Getter
    @Setter
    private String speciesDescription;

    @Inject
    public PlantsView(PlantService plantService,SpeciesService speciesService, PlantFactory plantFactory,
                      SpeciesFactory speciesFactory) {
        this.plantService = plantService;
        this.plantFactory = plantFactory;
        this.speciesService = speciesService;
        this.speciesFactory = speciesFactory;
    }

    public PlantsModel getPlantsModel() {
        if (model == null) {
            model = plantFactory.getModelFromEntity(plantService.getPlants());
        }
        return model;
    }

    public void init() {
        if (speciesId == null) {
            speciesDescription = "All plants";
        } else {
            try{
                SpeciesModel speciesModel = speciesFactory.getModelFromEntity(speciesService.getSpecies(UUID.fromString(speciesId)));
                speciesDescription = speciesModel.getName() + " is " + speciesModel.getType() +". One costs " + speciesModel.getPrice() + " OJROS";
            }
            catch (Exception e){
                speciesDescription = "Error: " + e.getMessage();
            }
        }
    }

}
