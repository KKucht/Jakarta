package com.example.lab1.gardener;

import com.example.lab1.gardener.factory.GardenerFactory;
import com.example.lab1.gardener.models.GardenerModel;
import com.example.lab1.gardener.models.GardenersModel;
import com.example.lab1.gardener.models.NewGardenerModel;
import jakarta.inject.Inject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

public class GardenerService {

    private final GardenerRepository gardenerRepository;

    private final ImageService imageService;

    private final GardenerFactory factory;

    @Inject
    public GardenerService(GardenerRepository gardenerRepository, ImageService imageService, GardenerFactory factory) {
        this.gardenerRepository = gardenerRepository;
        this.imageService = imageService;
        this.factory = factory;
    }

    public void createGardener(NewGardenerModel model) throws IOException {
        if (gardenerRepository.find(model.getId()).isPresent()){
            throw new IOException("Gardener with the specified UUID exits");
        }
        gardenerRepository.create(factory.getEntityFromModel(model));
    }

    public GardenerModel getGardener(UUID uuid) throws IOException {
        Optional<GardenerEntity> entity = gardenerRepository.find(uuid);
        if (entity.isEmpty()) {
            throw new IOException("Gardener with the specified UUID not found");
        }
        return factory.getModelFromEntity(entity.get());
    }

    public GardenersModel getGardeners() {
        return factory.getModelFromEntity(gardenerRepository.findAll());
    }

    public byte[] getGardenerImage(UUID uuid) throws IOException {
        Optional<GardenerEntity> gardener = gardenerRepository.find(uuid);
        if (gardener.isEmpty()) {
            throw new IOException("Gardener with the specified UUID not found");
        }
        byte[] image = imageService.getImage(uuid);
        if (image == null) {
            throw new IOException("Image not found");
        }
        return  image;
    }

    public void createGardenerImage(UUID uuid, InputStream is) throws IOException {
        Optional<GardenerEntity> gardener = gardenerRepository.find(uuid);
        if (gardener.isEmpty()) {
            throw new IOException("Gardener with the specified UUID not found");
        }
        try {
            imageService.createImage(uuid, is);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void updateGardenerImage(UUID uuid, InputStream is) throws IOException {
        Optional<GardenerEntity> gardener = gardenerRepository.find(uuid);
        if (gardener.isEmpty()) {
            throw new IOException("Gardener with the specified UUID not found");
        }
        try {
            imageService.updateImage(uuid, is);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void removeGardenerImage(UUID uuid) throws IOException {
        Optional<GardenerEntity> gardener = gardenerRepository.find(uuid);
        if (gardener.isEmpty()) {
            throw new IOException("Gardener with the specified UUID not found");
        }
        imageService.removeImage(uuid);
        gardenerRepository.update(uuid, gardener.get());
    }

}
