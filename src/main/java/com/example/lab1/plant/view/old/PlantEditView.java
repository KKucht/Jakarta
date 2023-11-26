package com.example.lab1.plant.view.old;

import com.example.lab1.plant.PlantService;
import com.example.lab1.plant.factory.old.PlantFactory;
import com.example.lab1.plant.models.old.PlantModel;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
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
public class PlantEditView implements Serializable {

    private PlantService service;

    @EJB
    public void setService(PlantService service) {
        this.service = service;
    }

    private final PlantFactory factory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private PlantModel model;

    @Inject
    public PlantEditView(PlantFactory factory) {
        this.factory = factory;
    }

    public void init() throws IOException {
        try {
            model = factory.getModelFromEntity(service.getPlant(id));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Plant not found");
        }
    }

    public String saveAction() {
        try {
            service.updatePlant(factory.getEntityFromModel(model), model.getKeeper(), model.getSpecies());
            String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
            return viewId + "?faces-redirect=true&includeViewParams=true";
        } catch (EJBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
