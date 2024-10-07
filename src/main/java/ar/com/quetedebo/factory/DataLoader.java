package ar.com.quetedebo.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataLoader {
	private final ObjectMapper objectMapper = new ObjectMapper();

	public <T> List<T> loadDataFromJson(Class<T> clazz) throws IOException {
		String fileName = clazz.getSimpleName() + ".json";
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        
        if (inputStream == null) {
            throw new IOException("No se pudo encontrar el archivo: " + fileName);
        }
		return objectMapper.readValue(inputStream,
				objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
	}
}
