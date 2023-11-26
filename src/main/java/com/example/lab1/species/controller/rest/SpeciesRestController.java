package com.example.lab1.species.controller.rest;

import com.example.lab1.species.SpeciesService;
import com.example.lab1.species.controller.api.SpeciesController;
import com.example.lab1.species.factory.SpeciesFactory;
import com.example.lab1.species.models.rest.GetAllSpeciesResponse;
import com.example.lab1.species.models.rest.GetSpeciesResponse;
import com.example.lab1.species.models.rest.PatchSpeciesRequest;
import com.example.lab1.species.models.rest.PutSpeciesRequest;
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
public class SpeciesRestController implements SpeciesController {

    private HttpServletResponse response;

    private final UriInfo uriInfo;

    private SpeciesService service;

    private final SpeciesFactory factory;

    @Inject
    public SpeciesRestController(SpeciesFactory factory,
                                 @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo
    ) {
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @EJB
    public void setService(SpeciesService service) {
        this.service = service;
    }


    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public GetAllSpeciesResponse getAllSpecies() {
        return factory.getResponse(service.getAllSpecies());
    }

    @Override
    public GetSpeciesResponse getSpecies(UUID uuid) {
        try {
            return factory.getResponse(service.getSpecies(uuid));
        } catch (EJBException | IOException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public void deleteSpecies(UUID uuid) {
        try {
            service.deleteSpecies(uuid);
        } catch (EJBException | IOException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public void putSpecies(UUID uuid, PutSpeciesRequest request) {
        try {
            service.createSpecies(factory.getNewEntity(uuid, request));
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(SpeciesController.class, "getSpecies")
                    .build(uuid)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (EJBException | IOException e) {
            try {
                if (e.getMessage().equals("Species with the specified UUID exits")) {
                    service.updateSpecies(factory.getUpdatedEntity(service.getSpecies(uuid), request));
                } else {
                    throw new BadRequestException(e);
                }
            } catch (EJBException | IOException ex) {
                throw new BadRequestException(ex);
            }
        }
    }

    @Override
    public void patchSpecies(UUID uuid, PatchSpeciesRequest request) {
        try {
            service.updateSpecies(factory.getUpdatedEntity(service.getSpecies(uuid), request));
        } catch (EJBException | IOException e) {
            throw new NotFoundException();
        }
    }
}
