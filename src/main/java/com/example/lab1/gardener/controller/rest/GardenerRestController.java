package com.example.lab1.gardener.controller.rest;

import com.example.lab1.gardener.GardenerService;
import com.example.lab1.gardener.controller.api.GardenerController;
import com.example.lab1.gardener.factory.GardenerFactory;
import com.example.lab1.gardener.models.rest.GetGardenerResponse;
import com.example.lab1.gardener.models.rest.GetGardenersResponse;
import com.example.lab1.gardener.models.rest.PutGardenerRequest;
import com.example.lab1.plant.controller.api.PlantController;
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
import java.io.InputStream;
import java.util.UUID;

@Path("")
public class GardenerRestController implements GardenerController {
    private HttpServletResponse response;

    private final UriInfo uriInfo;

    private final GardenerService service;

    private final GardenerFactory factory;

    @Inject
    public GardenerRestController(GardenerService service,
                                  GardenerFactory factory,
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
    public GetGardenersResponse getGardeners() {
        return factory.getResponse(service.getGardeners());
    }

    @Override
    public GetGardenerResponse getGardener(UUID id) {
        try{
            return factory.getResponse(service.getGardener(id));
        } catch (IOException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public void registerGardener(UUID id, PutGardenerRequest request) {
        try{
            service.createGardener(factory.getNewEntity(id,request));
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(PlantController.class, "getPlant")
                    .build(id)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (IOException e) {
            throw new BadRequestException();
        }
    }

    @Override
    public byte[] getGardenerImage(UUID id) {
        try {
            return service.getGardenerImage(id);
        } catch (IOException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public void putGardenerImage(UUID id, InputStream image) {
        try {
            service.getGardenerImage(id);
            service.updateGardenerImage(id, image);
        } catch (IOException e) {
            try{
                service.createGardenerImage(id, image);
            } catch (IOException ex) {
                throw new BadRequestException(ex);
            }
        }
    }

    @Override
    public void deleteGardenerImage(UUID id) {
        try{
            service.getGardenerImage(id);
            service.removeGardenerImage(id);
        } catch (IOException e) {
            throw new NotFoundException(e);
        }
    }
}
