package com.example.lab1.species.view;

import com.example.lab1.species.SpeciesService;
import com.example.lab1.species.factory.SpeciesFactory;
import com.example.lab1.species.models.AllSpeciesModel;
import com.example.lab1.species.models.SimpleSpeciesModel;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;

@ViewScoped
@Named
public class AllSpeciesView implements Serializable {
    private final SpeciesService service;

    private AllSpeciesModel model;

    private final SpeciesFactory factory;

    @Inject
    public AllSpeciesView(SpeciesService service, SpeciesFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    public AllSpeciesModel getModel() {
        if (model == null) {
            model = factory.getModelFromEntity(service.getAllSpecies());
        }
        return model;
    }

    public String deleteSpecies(SimpleSpeciesModel model) throws IOException {
        service.deleteSpecies(model.getId());
        return "species_list?faces-redirect=true";
    }

}
