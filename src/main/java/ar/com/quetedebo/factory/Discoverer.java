package ar.com.quetedebo.factory;

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
	private File directoryJars;
	
	public Discoverer(String path) {
		directoryJars = new File(path);
    }

	public Set<T> buildExtension(Class<T> classInterface) throws IOException, IllegalArgumentException, InvocationTargetException {
		Set<T> resultImplementations = new HashSet<>();
		
		File[] files = directoryJars.listFiles();
		
		for(File fileJar : files) {
	        findInJar(classInterface, fileJar, resultImplementations);
		}

		return resultImplementations;
	}
	
	private void findInJar(Class<T> classInterface, File fileJar, Set<T> resultImplementations) throws IOException, InvocationTargetException {
		JarFile jarFile = new JarFile(fileJar);
        Enumeration<JarEntry> entries = jarFile.entries();
		
        URL[] urls = { new URL("jar:file:" + fileJar+"!/") };
        URLClassLoader classLoader = new URLClassLoader(urls);

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();

            if (entry.getName().endsWith(".class")) {
                String className = entry.getName().replace("/", ".").replace(".class", "");

                try {
                    Class<?> cls = classLoader.loadClass(className);

                    if(cls != null && !cls.isInterface() && classInterface.isAssignableFrom(cls)) {
                    	resultImplementations.add((T) cls.getDeclaredConstructor().newInstance());
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
	}
}
