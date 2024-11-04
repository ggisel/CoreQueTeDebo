package ar.com.quetedebo.core;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataLoader {
	private final ObjectMapper objectMapper = new ObjectMapper();
	File file;
	
	public DataLoader(String path) {
		file = new File(path);
    }

	public <T> List<T> loadDataFromJson(Class<T> clazz) throws IOException {
		return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
	}
}
