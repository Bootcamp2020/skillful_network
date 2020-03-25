package fr.uca.cdr.skillful_network.tools.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.stream.JsonReader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

public class JSONLoader<T> {

    public static final String SKILLFUL_NETWORK_SERVER_PATH = "/skillful_network_server/";

    private final String path;

    private final Class<T[]> arrayType;

    private final JpaRepository<T, Long> repository;

    private Class<T> classType;

    private JsonDeserializer<T> adapter;

    public JSONLoader(String path, Class<T[]> arrayType, JpaRepository<T, Long> repository) {
        this.path = path;
        this.arrayType = arrayType;
        this.repository = repository;
    }

    public JSONLoader(String path,
                      Class<T[]> arrayType,
                      Class<T> classType,
                      JpaRepository<T, Long> repository,
                      JsonDeserializer<T> adapter) {
        this(path, arrayType, repository);
        this.adapter = adapter;
        this.classType = classType;
    }

    private Gson getGson() {
        if (this.adapter != null) {
            return new GsonBuilder().setPrettyPrinting()
                    .registerTypeAdapter(this.classType, this.adapter)
                    .create();
        } else {
            return new GsonBuilder().setPrettyPrinting().create();
        }
    }

    public List<T> load() {
        final String resourceDir = this.getClass().getResource("/").getPath();
        final String cwdDirectory = resourceDir.substring(0, resourceDir.lastIndexOf(SKILLFUL_NETWORK_SERVER_PATH));
        final String url = cwdDirectory + SKILLFUL_NETWORK_SERVER_PATH + this.path;
        try (final JsonReader reader = new JsonReader(new FileReader(url))) {
            final Gson gson = this.getGson();
            List<T> elements =  Arrays.asList(gson.fromJson(reader, this.arrayType));
            this.repository.saveAll(elements);
            this.repository.findAll().forEach(System.out::println);
            this.save(elements);
            return elements;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void save(List<T> toBeSaved) {
        final String resourceDir = JSONLoader.class.getResource("/").getPath();
        final String cwdDirectory = resourceDir.substring(0, resourceDir.lastIndexOf(SKILLFUL_NETWORK_SERVER_PATH));
        final String url = cwdDirectory + SKILLFUL_NETWORK_SERVER_PATH + this.path;
        try (FileWriter writer = new FileWriter(new File(url), false)) {
            final Gson gson = this.getGson();
            gson.toJson(toBeSaved, writer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
