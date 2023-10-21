package com.example.lab1.species;

import com.example.lab1.species.factory.SpeciesFactory;
import com.example.lab1.species.models.SpeciesModel;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

public class SpeciesController {

    private final SpeciesService speciesService;

    private final SpeciesFactory factory;

    private final Jsonb jsonb = JsonbBuilder.create();

    @Inject
    public SpeciesController(SpeciesService speciesService, SpeciesFactory factory) {
        this.speciesService = speciesService;
        this.factory = factory;
    }

    public void getAllSpecies(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(jsonb.toJson(factory.getModelFromEntity(speciesService.getAllSpecies())));
    }

    public void getSpecies(UUID uuid, HttpServletResponse response) throws IOException {
        SpeciesModel model;
        response.setContentType("application/json");
        try {
            model = factory.getModelFromEntity(speciesService.getSpecies(uuid));
        } catch (Exception e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Species with the specified UUID not found.");
            return;
        }
        response.getWriter().write(jsonb.toJson(model));
    }

}
