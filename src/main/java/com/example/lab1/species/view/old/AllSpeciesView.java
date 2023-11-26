package com.example.lab1.species.view.old;

import com.example.lab1.species.SpeciesService;
import com.example.lab1.species.factory.old.SpeciesFactory;
import com.example.lab1.species.models.old.AllSpeciesModel;
import com.example.lab1.species.models.old.SimpleSpeciesModel;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;

@ViewScoped
@Named
public class AllSpeciesView implements Serializable {
    private SpeciesService service;

    private AllSpeciesModel model;

    private final SpeciesFactory factory;

    @Inject
    public AllSpeciesView(SpeciesService service, SpeciesFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(SpeciesService service) {
        this.service = service;
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
