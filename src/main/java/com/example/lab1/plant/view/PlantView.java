package com.example.lab1.plant.view;

import com.example.lab1.plant.PlantService;
import com.example.lab1.plant.factory.PlantFactory;
import com.example.lab1.plant.models.PlantModel;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

@ViewScoped
@Named
public class PlantView implements Serializable {

    private final PlantService service;

    private final PlantFactory factory;

    @Setter
    @Getter
    private UUID id;

    @Setter
    @Getter
    private PlantModel model;

    @Inject
    public PlantView(PlantService service, PlantFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    public void init() throws IOException {
        try{
            model = factory.getModelFromEntity(service.getPlant(id));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Plant not found");
        }
    }
}
