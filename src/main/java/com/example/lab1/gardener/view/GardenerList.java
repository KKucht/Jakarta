package com.example.lab1.gardener.view;

import com.example.lab1.gardener.GardenerService;
import com.example.lab1.gardener.factory.GardenerFactory;
import com.example.lab1.gardener.models.jsp.GardenersModel;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;

@RequestScoped
@Named
public class GardenerList {

    private GardenerService service;

    private GardenersModel users;

    private final GardenerFactory factory;

    @Inject
    public GardenerList(GardenerService service, GardenerFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    public GardenersModel getGardeners() {
        if (users == null) {
            users = factory.getModel(service.getGardeners());
        }
        return users;
    }

    public String deleteAction(GardenersModel.Gardener gardener) {
        try{
            service.deleteGardener(gardener.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "gardener_list?faces-redirect=true";
    }
}
