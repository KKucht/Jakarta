package com.example.lab1.plant.controller.rest;

import com.example.lab1.gardener.GardenerRoles;
import com.example.lab1.plant.PlantService;
import com.example.lab1.plant.controller.api.PlantController;
import com.example.lab1.plant.factory.PlantFactory;
import com.example.lab1.plant.models.rest.GetPlantResponse;
import com.example.lab1.plant.models.rest.GetPlantsResponse;
import com.example.lab1.plant.models.rest.PatchPlantRequest;
import com.example.lab1.plant.models.rest.PutPlantRequest;
import com.example.lab1.species.SpeciesService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
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
@RolesAllowed(GardenerRoles.USER)
public class PlantRestController implements PlantController {

    private HttpServletResponse response;

    private final UriInfo uriInfo;

    private PlantService service;

    @EJB
    public void setService(PlantService service) {
        this.service = service;
    }

    private final PlantFactory factory;

    @Inject
    public PlantRestController(PlantFactory factory,
                               @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo
    ) {
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public GetPlantsResponse getPlants() {
        try{
            return factory.getResponse(service.getPlants());
        } catch (IOException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public GetPlantsResponse getSpeciesPlants(UUID id) {
        try {
            return factory.getResponse(service.getSpeciesPlants(id));
        } catch (EJBException | IOException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public GetPlantResponse getPlant(UUID id) {
        try {
            return factory.getResponse(service.getPlant(id));
        } catch (EJBException | IOException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public void putPlant(UUID speciesId, UUID id, PutPlantRequest request) {
        try {
            service.createPlant(factory.getNewEntity(id, request), speciesId);
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(PlantController.class, "getPlant")
                    .build(id)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (EJBException | IOException e) {
            if (e.getMessage().equals("Plant with the specified UUID exits")) {
                try {
                    service.updatePlant(factory.getUpdatedEntity(service.getPlant(id), request), null, speciesId);
                } catch (EJBException | IOException ex) {
                    throw new BadRequestException();
                }
            } else
                throw new BadRequestException();
        }
    }

    @Override
    public void patchPlant(UUID speciesId, UUID id, PatchPlantRequest request) {
        try {
            service.updatePlant(factory.getUpdatedEntity(service.getPlant(id), request), null, speciesId);
        } catch (EJBException | IOException e) {
            throw new BadRequestException();
        }
    }

    @Override
    public void deletePlant(UUID id) {
        try {
            service.deletePlant(id);
        } catch (EJBException | IOException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public GetPlantsResponse getGardenerPlants(UUID id) {
        try {
            return factory.getResponse(service.getGardenerPlants(id));
        } catch (EJBException | IOException e) {
            throw new NotFoundException();
        }
    }
}
