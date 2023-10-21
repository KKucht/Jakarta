package com.example.lab1.plant;

import com.example.lab1.plant.factory.PlantFactory;
import com.example.lab1.plant.models.PlantModel;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

public class PlantController {

    private final PlantService plantService;

    private final PlantFactory factory;

    private final Jsonb jsonb = JsonbBuilder.create();

    @Inject
    public PlantController(PlantService plantService, PlantFactory factory) {
        this.plantService = plantService;
        this.factory = factory;
    }

    public void getPlants(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(jsonb.toJson(factory.getModelFromEntity(plantService.getPlants())));
    }

    public void getPlant(UUID uuid, HttpServletResponse response) throws IOException {
        PlantModel model;
        response.setContentType("application/json");
        try {
            model = factory.getModelFromEntity(plantService.getPlant(uuid));
        } catch (Exception e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
            return;
        }
        response.getWriter().write(jsonb.toJson(model));
    }

}
