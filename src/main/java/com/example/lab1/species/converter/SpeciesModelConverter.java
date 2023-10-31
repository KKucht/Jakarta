package com.example.lab1.species.converter;

import com.example.lab1.species.SpeciesService;
import com.example.lab1.species.factory.SpeciesFactory;
import com.example.lab1.species.models.SimpleSpeciesModel;
import com.example.lab1.species.models.SpeciesModel;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.UUID;

@FacesConverter(forClass = SimpleSpeciesModel.class, managed = true)
public class SpeciesModelConverter implements Converter<SimpleSpeciesModel> {

    private final SpeciesService service;

    private final SpeciesFactory factory;

    @Inject
    public SpeciesModelConverter(SpeciesService service, SpeciesFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public SimpleSpeciesModel getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        SpeciesModel model = null;
        try {
            model = factory.getModelFromEntity(service.getSpecies(UUID.fromString(value)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new SimpleSpeciesModel(model.getId(), model.getName(), model.getType());
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, SimpleSpeciesModel value) {
        return value == null ? "" : value.getId().toString();
    }
}
