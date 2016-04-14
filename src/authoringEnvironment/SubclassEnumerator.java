package authoringEnvironment;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import behaviors.Behavior;
import collisions.Collision;
import gameElements.Sprite;

public class SubclassEnumerator {
	public static final String BASE_ABS_PATH = "/Volumes/trapSD/Google%20Drive/School/Duke/Sophomore/COMPSCI%20308/voogasalad_TheDuballers";
	public static final String PACKAGE_NAME = "gameElements";
	
	public static void main(String[] args) {
		List<Class<?>> test = new ArrayList<Class<?>>();
		
		try {
			test = getClasses(PACKAGE_NAME);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//findSubclasses(test, Collision.class);
		for (Class<?> c : test) {
			//System.out.println(c.getName());
		}
		getReadableClasses(PACKAGE_NAME);
		//getSubclasses(PACKAGE_NAME, Behavior.class);
	}
	
	public static List<String> getReadableClasses(String packageName) {
		List<Class<?>> clazzes = new ArrayList<Class<?>>();
		try {
			clazzes = getClasses(packageName);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> readableClasses = new ArrayList<String>();
		for(Class<?> c : clazzes) {
			readableClasses.add(c.getName());
		}
		
		return readableClasses;
	}
	
	public static Map<String, Class<?>> getSubclasses(String packageName, Class<?> superclass) {
		Map<String, Class<?>> subclassNameMap = new HashMap<String, Class<?>>();
		List<Class<?>> packageClasses = new ArrayList<Class<?>>();
		try {
			packageClasses = getClasses(packageName);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Class<?>> subclasses = findSubclasses(packageClasses, superclass);
		
		for (Class<?> subclass : subclasses) {
			String subclassName = subclass.getName();
			String readableName = subclassName.substring(subclassName.indexOf(".")+1);
			subclassNameMap.put(readableName, subclass);
		}
		
		return subclassNameMap;
	}
	
	private static List<Class<?>> findSubclasses(List<Class<?>> classes, Class<?> superclass) {
		List<Class<?>> subclasses = new ArrayList<Class<?>>();
		for (Class<?> candidateClass : classes) {
		    if (superclass.isAssignableFrom(candidateClass)) {
		        subclasses.add(candidateClass);
		    }
		}
		return subclasses;
	}
	
	/**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     * 
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            String relative = new File(BASE_ABS_PATH)
            		.toURI()
            		.relativize(new File(resource.getFile()).toURI())
            		.getPath();
            dirs.add(new File(relative));
        }
        
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }
    
    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (!directory.exists()) {
            return classes;
        }
        
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }



}
