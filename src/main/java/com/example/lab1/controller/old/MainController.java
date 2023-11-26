package com.example.lab1.controller.old;

import com.example.lab1.gardener.controller.old.GardenerController;
import com.example.lab1.plant.controller.old.PlantController;
import com.example.lab1.species.controller.old.SpeciesController;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {"/api_old/*"})
public class MainController extends HttpServlet {
    private final GardenerController gardenerController;

    private final PlantController plantController;

    private final SpeciesController speciesController;

    @Inject
    public MainController(GardenerController gardenerController, PlantController plantController,
                          SpeciesController speciesController) {
        this.gardenerController = gardenerController;
        this.plantController = plantController;
        this.speciesController = speciesController;
    }

    public static final class Patterns {

        private static final Pattern UUID = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

        public static final Pattern GARDENERS = Pattern.compile("/gardeners/?");

        public static final Pattern PLANTS = Pattern.compile("/plants/?");

        public static final Pattern ALL_SPECIES = Pattern.compile("/species/?");

        public static final Pattern GARDENER = Pattern.compile("/gardeners/(%s)".formatted(UUID.pattern()));

        public static final Pattern PLANT = Pattern.compile("/plants/(%s)".formatted(UUID.pattern()));

        public static final Pattern SPECIES = Pattern.compile("/species/(%s)".formatted(UUID.pattern()));

        public static final Pattern GARDENER_IMAGE = Pattern.compile("/gardeners/(%s)/image".formatted(UUID.pattern()));
    }

    public static UUID extractUuid(String path) {
        Pattern uuidPattern = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
        Matcher matcher = uuidPattern.matcher(path);

        if (matcher.find()) {
            String uuidString = matcher.group();
            return UUID.fromString(uuidString);
        }
        throw new IllegalArgumentException("No UUID found in the path.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathURI = request.getRequestURI();
        if (pathURI.matches("/ogrod/api/.+")) {
            String path = request.getPathInfo();
            if (path.matches(Patterns.GARDENERS.pattern())) {
                gardenerController.getGardeners(response);
                return;
            } else if (path.matches(Patterns.GARDENER.pattern())) {
                UUID uuid = extractUuid(pathURI);
                gardenerController.getGardener(uuid, response);
                return;
            } else if (path.matches(Patterns.GARDENER_IMAGE.pattern())) {
                UUID uuid = extractUuid(pathURI);
                gardenerController.getGardenerImage(uuid, response);
                return;
            } else if (path.matches(Patterns.PLANT.pattern())) {
                UUID uuid = extractUuid(pathURI);
                plantController.getPlant(uuid, response);
                return;
            } else if (path.matches(Patterns.PLANTS.pattern())) {
                plantController.getPlants(response);
                return;
            } else if (path.matches(Patterns.SPECIES.pattern())) {
                UUID uuid = extractUuid(pathURI);
                speciesController.getSpecies(uuid, response);
                return;
            } else if (path.matches(Patterns.ALL_SPECIES.pattern())) {
                speciesController.getAllSpecies(response);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, pathURI);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathURI = request.getRequestURI();
        if (pathURI.matches("/ogrod/api/.+")) {
            String path = request.getPathInfo();
            if (path.matches(Patterns.GARDENER_IMAGE.pattern())) {
                UUID uuid = extractUuid(pathURI);
                gardenerController.postGardenerImage(uuid, request.getInputStream(), response);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, pathURI);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathURI = request.getRequestURI();
        if (pathURI.matches("/ogrod/api/.+")) {
            String path = request.getPathInfo();
            if (path.matches(Patterns.GARDENER_IMAGE.pattern())) {
                UUID uuid = extractUuid(pathURI);
                gardenerController.putGardenerImage(uuid, request.getInputStream(), response);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, pathURI);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathURI = request.getRequestURI();
        if (pathURI.matches("/ogrod/api/.+")) {
            String path = request.getPathInfo();
            if (path.matches(Patterns.GARDENER_IMAGE.pattern())) {
                UUID uuid = extractUuid(pathURI);
                gardenerController.deleteGardenerImage(uuid, response);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, pathURI);
    }

}
