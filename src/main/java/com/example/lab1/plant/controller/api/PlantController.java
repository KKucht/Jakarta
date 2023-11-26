package com.example.lab1.plant.controller.api;

import com.example.lab1.plant.models.rest.GetPlantResponse;
import com.example.lab1.plant.models.rest.GetPlantsResponse;
import com.example.lab1.plant.models.rest.PatchPlantRequest;
import com.example.lab1.plant.models.rest.PutPlantRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@Path("")
public interface PlantController {

    @GET
    @Path("/plants")
    @Produces(MediaType.APPLICATION_JSON)
    GetPlantsResponse getPlants();

    @GET
    @Path("/species/{id}/plants")
    @Produces(MediaType.APPLICATION_JSON)
    GetPlantsResponse getSpeciesPlants(@PathParam("id") UUID id);

    @GET
    @Path("/plants/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetPlantResponse getPlant(@PathParam("id") UUID id);

    @PUT
    @Path("/species/{speciesId}/plants/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putPlant(@PathParam("speciesId") UUID speciesId, @PathParam("id") UUID id, PutPlantRequest request);

    @PATCH
    @Path("/species/{speciesId}/plants/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchPlant(@PathParam("speciesId") UUID speciesId, @PathParam("id") UUID id, PatchPlantRequest request);

    @DELETE
    @Path("/plants/{id}")
    void deletePlant(@PathParam("id") UUID id);

    @GET
    @Path("/gardeners/{id}/plants/")
    @Produces(MediaType.APPLICATION_JSON)
    GetPlantsResponse getGardenerPlants(@PathParam("id") UUID id);

}
