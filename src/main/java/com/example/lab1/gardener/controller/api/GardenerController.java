package com.example.lab1.gardener.controller.api;

import com.example.lab1.gardener.models.rest.GetGardenerResponse;
import com.example.lab1.gardener.models.rest.GetGardenersResponse;
import com.example.lab1.gardener.models.rest.PutGardenerRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.io.InputStream;
import java.util.UUID;

@Path("")
public interface GardenerController {

    @GET
    @Path("/gardeners")
    @Produces(MediaType.APPLICATION_JSON)
    GetGardenersResponse getGardeners();

    @GET
    @Path("/gardeners/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetGardenerResponse getGardener(@PathParam("id") UUID id);

    @PUT
    @Path("/gardeners/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void registerGardener(@PathParam("id") UUID id, PutGardenerRequest request);

    @GET
    @Path("/gardeners/{id}/image")
    @Produces("image/png")
    byte[] getGardenerImage(@PathParam("id") UUID id);

    @PUT
    @Path("/gardeners/{id}/image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    void putGardenerImage(
            @PathParam("id") UUID id,
            @SuppressWarnings("RestParamTypeInspection")@FormParam("image") InputStream image
    );

    @DELETE
    @Path("/gardeners/{id}/image")
    void deleteGardenerImage(@PathParam("id") UUID id);

}
