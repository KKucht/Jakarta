package com.example.lab1.gardener;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletContext;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class ImageService {


    private final String path;

    @Inject
    public ImageService(ServletContext servletContext) {
        this.path = servletContext.getInitParameter("imagesPath");
    }

    public byte[] getImage(UUID uuid) throws IOException {
        Path source = Path.of(this.path + uuid.toString() + ".png");
        try {
            return Files.readAllBytes(source);
        } catch (NoSuchFileException e) {
            return null;
        }
    }

    public void createImage(UUID uuid, InputStream is) throws IOException {
        Path destination = Path.of(this.path + uuid.toString() + ".png");
        Files.createFile(destination);
        Files.copy(is, destination, StandardCopyOption.REPLACE_EXISTING);
    }

    public void updateImage(UUID uuid, InputStream is) throws IOException {
        Path destination = Path.of(this.path + uuid.toString() + ".png");
        Files.copy(is, destination, StandardCopyOption.REPLACE_EXISTING);
    }

    public void removeImage(UUID uuid) throws IOException {
        Path filePath = Path.of(this.path + uuid.toString() + ".png");
        Files.delete(filePath);
    }
}
