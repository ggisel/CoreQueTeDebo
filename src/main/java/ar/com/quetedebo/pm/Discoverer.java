package ar.com.quetedebo.pm;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Discoverer<T> {

	public Set<T> loadImplementation(Class<T> classInterface) throws IOException, IllegalArgumentException, InvocationTargetException {
		Set<T> result = new HashSet<>();
		
		File jarFipathlePath = new File("resource/extensiones");
		File[] files = jarFipathlePath.listFiles();
		
		for(File file : files) {
			// Cargar el JAR
	        JarFile jarFile = new JarFile(file);
	        Enumeration<JarEntry> entries = jarFile.entries();
			
	        // Crea un ClassLoader para cargar las clases del JAR
	        URL[] urls = { new URL("jar:file:" + file+"!/") };
	        URLClassLoader classLoader = new URLClassLoader(urls);
	        
	        // Iterar a través de las entradas en el JAR
	        while (entries.hasMoreElements()) {
	            JarEntry entry = entries.nextElement();

	            // Verifica si la entrada es un archivo de clase
	            if (entry.getName().endsWith(".class")) {
	                // Reemplaza '/' por '.' para obtener el nombre completo de la clase
	                String className = entry.getName().replace("/", ".").replace(".class", "");

	                try {
	                    // Carga la clase
	                    Class<?> cls = classLoader.loadClass(className);

	                    if(cls != null && !cls.isInterface() && classInterface.isAssignableFrom(cls)) {
	        				result.add((T) cls.getDeclaredConstructor().newInstance());
	        			}

	                } catch (ClassNotFoundException e) {
	                    System.err.println("Clase no encontrada: " + className);
	                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        // Cierra el JAR y el ClassLoader
	        jarFile.close();
	        classLoader.close();
		}

		return result;
	}
}
