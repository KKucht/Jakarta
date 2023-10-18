package com.example.lab1.controller;

import com.example.lab1.gardener.GardenerController;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {"/api/*"})
public class MainController extends HttpServlet {
    private final GardenerController gardenerController;

    @Inject
    public MainController(GardenerController gardenerController) {
        this.gardenerController = gardenerController;
    }

    public static final class Patterns {

        private static final Pattern UUID = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

        public static final Pattern GARDENERS = Pattern.compile("/gardeners/?");

        public static final Pattern GARDENER = Pattern.compile("/gardeners/(%s)".formatted(UUID.pattern()));

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
            }
            else if (path.matches(Patterns.GARDENER.pattern())) {
                UUID uuid = extractUuid(pathURI);
                gardenerController.getGardener(uuid, response);
                return;
            }
            else if (path.matches(Patterns.GARDENER_IMAGE.pattern())) {
                UUID uuid = extractUuid(pathURI);
                gardenerController.getGardenerImage(uuid, response);
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
                gardenerController.postGardenerImage(uuid,request.getInputStream(),response);
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
                gardenerController.putGardenerImage(uuid, request.getInputStream(),response);
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
                gardenerController.deleteGardenerImage(uuid,response);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, pathURI);
    }

}
