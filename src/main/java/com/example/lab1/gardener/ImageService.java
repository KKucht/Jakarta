package com.example.lab1.gardener;

import jakarta.enterprise.context.Dependent;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Dependent
public class ImageService {


    private static final String path = "D:\\Jakarta\\Lab1\\images\\";

    public byte[] getImage(UUID uuid) throws IOException {
        Path source = Path.of(path + uuid.toString() + ".png");
        try {
            return Files.readAllBytes(source);
        } catch (NoSuchFileException e) {
            return null;
        }
    }

    public void createImage(UUID uuid, InputStream is) throws IOException {
        Path destination = Path.of(path + uuid.toString() + ".png");
        Files.createFile(destination);
        Files.copy(is, destination, StandardCopyOption.REPLACE_EXISTING);
    }

    public void updateImage(UUID uuid, InputStream is) throws IOException {
        Path destination = Path.of(path + uuid.toString() + ".png");
        Files.copy(is, destination, StandardCopyOption.REPLACE_EXISTING);
    }

    public void removeImage(UUID uuid) throws IOException {
        Path filePath = Path.of(path + uuid.toString() + ".png");
        Files.delete(filePath);
    }
}
