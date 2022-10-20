package org.example.infra.persistence;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OnDiskJSONPersistence<T> {
    private final Gson serde;
    private final Path directory;

    public OnDiskJSONPersistence(String directory) {
        this.serde = new Gson();
        this.directory = Path.of(directory);
        try {
            Files.createDirectory(this.directory);
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void save(String name, T obj) throws IOException {
        Path path = computePath(name);
        String json = this.serde.toJson(obj);
        System.out.printf("Saving %s into %s%n", json, path);
        Files.writeString(path, json);
    }

    public T load(String name, Class<T> type) throws IOException {
        Path path = computePath(name);
        String json = Files.readString(path);
        return this.serde.fromJson(json, type);
    }

    private Path computePath(String name) {
        return Paths.get(this.directory.toString(), String.format("%s.json", name));
    }
}