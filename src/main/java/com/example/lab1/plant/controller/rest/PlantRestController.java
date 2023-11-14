package com.example.lab1.plant.controller.rest;

import com.example.lab1.plant.PlantService;
import com.example.lab1.plant.controller.api.PlantController;
import com.example.lab1.plant.factory.PlantFactory;
import com.example.lab1.plant.models.rest.GetPlantResponse;
import com.example.lab1.plant.models.rest.GetPlantsResponse;
import com.example.lab1.plant.models.rest.PatchPlantRequest;
import com.example.lab1.plant.models.rest.PutPlantRequest;
import com.example.lab1.species.controller.api.SpeciesController;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.io.IOException;
import java.util.UUID;

@Path("")
public class PlantRestController implements PlantController{

    private HttpServletResponse response;

    private final UriInfo uriInfo;

    private final PlantService service;

    private final PlantFactory factory;

    @Inject
    public PlantRestController(PlantService service,
                               PlantFactory factory,
                               @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo
    ) {
        this.service = service;
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public GetPlantsResponse getPlants() {
        return factory.getResponse(service.getPlants());
    }

    @Override
    public GetPlantsResponse getSpeciesPlants(UUID id) {
        try{
            return factory.getResponse(service.getSpeciesPlants(id));
        } catch (IOException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public GetPlantResponse getPlant(UUID id) {
        try {
            return factory.getResponse(service.getPlant(id));
        } catch (IOException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public void putPlant(UUID speciesId, UUID id, PutPlantRequest request) {
        try{
            service.createPlant(factory.getNewEntity(id,request),speciesId);
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(PlantController.class, "getPlant")
                    .build(id)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (Exception e) {
            if (e.getMessage().equals("Plant with the specified UUID exits")){
                try{
                    service.updatePlant(factory.getUpdatedEntity(service.getPlant(id), request), null, speciesId);
                } catch (IOException ex) {
                    throw new BadRequestException();
                }
            }
            else
                throw new BadRequestException();
        }
    }

    @Override
    public void patchPlant(UUID speciesId, UUID id, PatchPlantRequest request) {
        try {
            service.updatePlant(factory.getUpdatedEntity(service.getPlant(id) , request), null, speciesId);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public void deletePlant(UUID id) {
        try{
            service.deletePlant(id);
        } catch (IOException e) {
            throw new NotFoundException();
        }
    }
}
