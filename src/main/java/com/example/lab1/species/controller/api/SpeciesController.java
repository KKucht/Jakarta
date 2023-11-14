package com.example.lab1.species.controller.api;

import com.example.lab1.species.models.rest.GetAllSpeciesResponse;
import com.example.lab1.species.models.rest.GetSpeciesResponse;
import com.example.lab1.species.models.rest.PatchSpeciesRequest;
import com.example.lab1.species.models.rest.PutSpeciesRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@Path("")
public interface SpeciesController {

    @GET
    @Path("/species")
    @Produces(MediaType.APPLICATION_JSON)
    GetAllSpeciesResponse getAllSpecies();

    @GET
    @Path("/species/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetSpeciesResponse getSpecies(@PathParam("id") UUID uuid);

    @DELETE
    @Path("/species/{id}")
    void deleteSpecies(@PathParam("id") UUID uuid);

    @PUT
    @Path("/species/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putSpecies(@PathParam("id") UUID uuid, PutSpeciesRequest request);

    @PATCH
    @Path("/species/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchSpecies(@PathParam("id") UUID uuid, PatchSpeciesRequest request);

}
