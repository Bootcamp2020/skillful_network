package fr.uca.cdr.skillful_network;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class JSONLoader<T> {

	public static final String SKILLFUL_NETWORK_SERVER_PATH = "/skillful_network_server/";

	private final String path;

	private final Class<T[]> arrayType;

	private final JpaRepository<T, Long> repository;

	public JSONLoader(String path, Class<T[]> arrayType, JpaRepository<T, Long> repository) {
		this.path = path;
		this.arrayType = arrayType;
		this.repository = repository;
	}

	public List<T> load() {
		Gson gson = new Gson();
		final String resourceDir = this.getClass().getResource("/").getPath();
		final String cwdDirectory = resourceDir.substring(0, resourceDir.lastIndexOf(SKILLFUL_NETWORK_SERVER_PATH));
		final String url = cwdDirectory + SKILLFUL_NETWORK_SERVER_PATH + this.path;
		try (final JsonReader reader = new JsonReader(new FileReader(url))) {
			List<T> elements = Arrays.asList(gson.fromJson(reader, this.arrayType));
			this.repository.saveAll(elements);
			this.repository.findAll().forEach(System.out::println);
			return elements;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}