package com.example.lab1.gardener;

import com.example.lab1.gardener.models.GardenerModel;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class GardenerController {

    private final GardenerService gardenerService;

    private final Jsonb jsonb = JsonbBuilder.create();

    @Inject
    public GardenerController(GardenerService gardenerService) {
        this.gardenerService = gardenerService;
    }

    public void getGardeners(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(jsonb.toJson(gardenerService.getGardeners()));
    }

    public void getGardener(UUID uuid, HttpServletResponse response) throws IOException {
            GardenerModel gardener;
            response.setContentType("application/json");
            try {
                gardener = gardenerService.getGardener(uuid);
            } catch (Exception e){
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Gardener with the specified UUID not found.");
                return;
            }
            response.getWriter().write(jsonb.toJson(gardener));
    }

    public void getGardenerImage(UUID uuid, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        try {
            response.getOutputStream().write(gardenerService.getGardenerImage(uuid));
        } catch (IOException e) {
            if (e.getMessage().equals("Image not found")) {
                response.sendError(HttpServletResponse.SC_CONFLICT, "Image for this gardener not exit.");
            }
            else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Gardener with the specified UUID not found.");
            }
        }
    }

    public void putGardenerImage(UUID uuid, InputStream is, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try {
            gardenerService.getGardenerImage(uuid);
            gardenerService.updateGardenerImage(uuid, is);
        } catch (IOException e){
            if (e.getMessage().equals("Image not found")) {
                response.sendError(HttpServletResponse.SC_CONFLICT, "Image for this gardener not exit.");
            }
            else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Gardener with the specified UUID not found.");
            }
        }
    }

    public void postGardenerImage(UUID uuid, InputStream is, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try {
            gardenerService.getGardenerImage(uuid);
            response.sendError(HttpServletResponse.SC_CONFLICT, "Image for this gardener exits.");
        } catch (IOException e){
            if (e.getMessage().equals("Image not found")) {
                gardenerService.createGardenerImage(uuid, is);
            }
            else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Gardener with the specified UUID not found.");
            }
        }
    }

    public void deleteGardenerImage(UUID uuid, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try {
            gardenerService.getGardenerImage(uuid);
            gardenerService.removeGardenerImage(uuid);
        } catch (IOException e){
            if (e.getMessage().equals("Image not found")) {
                response.sendError(HttpServletResponse.SC_CONFLICT, "Image for this gardener not exits.");
            }
            else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Gardener with the specified UUID not found.");
            }
        }

    }
}
