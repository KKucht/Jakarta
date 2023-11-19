package com.example.lab1.plant.view.old;

import com.example.lab1.plant.PlantService;
import com.example.lab1.plant.factory.old.PlantFactory;
import com.example.lab1.plant.models.old.PlantsModel;
import com.example.lab1.plant.models.old.SimplePlantModel;
import com.example.lab1.species.SpeciesService;
import com.example.lab1.species.factory.old.SpeciesFactory;
import com.example.lab1.species.models.old.SpeciesModel;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.stream.Collectors;

@ViewScoped
@Named
public class PlantsView implements Serializable {

    private final PlantService plantService;

    private final SpeciesService speciesService;

    private PlantsModel plantsModel;

    private final PlantFactory plantFactory;

    private final SpeciesFactory speciesFactory;

    @Getter
    @Setter
    private UUID speciesId;

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
        if (plantsModel == null) {
            if (speciesId != null)
                plantsModel = plantFactory.getModelFromEntity(plantService.getPlants().stream()
                    .filter(el -> speciesId.equals(el.getSpecies().getId()))
                    .collect(Collectors.toList()));
            else
                plantsModel = plantFactory.getModelFromEntity(plantService.getPlants());
        }
        return plantsModel;
    }

    public void init() {
        if (speciesId == null) {
            speciesDescription = "All plants";
        } else {
            try{
                SpeciesModel speciesModel = speciesFactory.getModelFromEntity(speciesService.getSpecies(speciesId));
                speciesDescription = speciesModel.getName() + " is " + speciesModel.getType() +". One costs " + speciesModel.getPrice() + " OJROS";

            }
            catch (Exception e){
                speciesDescription = "Error: " + e.getMessage();
            }
        }
    }

    public String deletePlant(SimplePlantModel model) throws IOException {
        plantService.deletePlant(model.getId());
        if (speciesId == null) {
            return "plant_list?faces-redirect=true";
        } else {
            return "plant_list?faces-redirect=true&species-id=" + speciesId;
        }
    }

}
