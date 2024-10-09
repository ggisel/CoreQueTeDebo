package ar.com.quetedebo.factory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Discoverer<T> {
	private File directoryJars;
	
	public Discoverer(String path) {
		directoryJars = new File(path);
    }

	public T buildExtension(Class<T> classInterface) {
		T resultImplementation = null;
		
		File[] files = directoryJars.listFiles();
		
		for(File fileJar : files) {
	        try {
	        	resultImplementation = findInJar(classInterface, fileJar);
			} catch (InvocationTargetException | IOException e) {
				e.printStackTrace();
			}
		}

		if (resultImplementation == null) {
	        throw new IllegalStateException("No implementation found for interface: " + classInterface.getName());
	    }

		return resultImplementation;
	}
	
	private T findInJar(Class<T> classInterface, File fileJar) throws IOException, InvocationTargetException {
		JarFile jarFile = new JarFile(fileJar);
        Enumeration<JarEntry> entries = jarFile.entries();
		
        URL[] urls = { new URL("jar:file:" + fileJar+"!/") };
        URLClassLoader classLoader = new URLClassLoader(urls);
        
        T resultImplementation = null;

        while (entries.hasMoreElements() && resultImplementation == null) {
            JarEntry entry = entries.nextElement();

            if (entry.getName().endsWith(".class")) {
                String className = entry.getName().replace("/", ".").replace(".class", "");

                try {
                    Class<?> cls = classLoader.loadClass(className);

                    if(cls != null && !cls.isInterface() && classInterface.isAssignableFrom(cls)) {
                    	resultImplementation = (T) cls.getDeclaredConstructor().newInstance();
        			}

                } catch (ClassNotFoundException e) {
                    System.err.println("Clase no encontrada: " + className);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

        jarFile.close();
        classLoader.close();
        
        return resultImplementation;
	}
}
