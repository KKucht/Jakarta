package com.example.lab1.gardener.view;

import com.example.lab1.gardener.GardenerService;
import com.example.lab1.gardener.factory.GardenerFactory;
import com.example.lab1.gardener.models.jsp.GardenersModel;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;

@ViewScoped
@Named
public class GardenerList implements Serializable {

    private GardenerService service;

    private GardenersModel gardenersModel;

    private final GardenerFactory factory;

    @Inject
    public GardenerList(GardenerFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(GardenerService service) {
        this.service = service;
    }

    public GardenersModel getGardeners() {
        if (gardenersModel == null) {
            gardenersModel = factory.getModel(service.getGardeners());
        }
        return gardenersModel;
    }

    public void deleteAction(GardenersModel.Gardener gardener) {
        try{
            service.deleteGardener(gardener.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gardenersModel = null;
    }
}
