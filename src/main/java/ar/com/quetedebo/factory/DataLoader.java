package ar.com.quetedebo.factory;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataLoader {
	private final ObjectMapper objectMapper = new ObjectMapper();
	File directoryLoader;
	
	public DataLoader(String path) {
		directoryLoader = new File(path);
    }

	public <T> List<T> loadDataFromJson(Class<T> clazz) throws IOException {
		String fileName = directoryLoader + File.separator + clazz.getSimpleName() + ".json";

		return objectMapper.readValue(new File(fileName),
				objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
	}
}
