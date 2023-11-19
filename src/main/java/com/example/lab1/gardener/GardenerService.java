package com.example.lab1.gardener;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class GardenerService {

    private final GardenerRepository gardenerRepository;

    private final ImageService imageService;

    @Inject
    public GardenerService(GardenerRepository gardenerRepository, ImageService imageService) {
        this.gardenerRepository = gardenerRepository;
        this.imageService = imageService;
    }

    public void createGardener(GardenerEntity entity) throws IOException {
        if (gardenerRepository.find(entity.getId()).isPresent()){
            throw new IOException("Gardener with the specified UUID exits");
        }
        gardenerRepository.create(entity);
    }

    public GardenerEntity getGardener(UUID uuid) throws IOException {
        Optional<GardenerEntity> entity = gardenerRepository.find(uuid);
        if (entity.isEmpty()) {
            throw new IOException("Gardener with the specified UUID not found");
        }
        return entity.get();
    }

    public List<GardenerEntity> getGardeners() {
        return gardenerRepository.findAll();
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
