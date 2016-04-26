	package authoringEnvironment.settingsWindow.ObjectEditorFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
	private static List<String> packageNames = Arrays.asList("authoringEnvironment", "behaviors", "collisions", "game", "gameElements",
			"gameplayer", "goals", "highscoretable", "HUD", "interfaces", "keyboard", "level",
			"spriteProperties");
	private static SubclassEnumerator instance = null;
	
	
	
	public SubclassEnumerator(List<String> packageNames) {
		this.packageNames = packageNames;
	}
	
	public SubclassEnumerator getInstance() {
		if (instance == null) {
			instance = new SubclassEnumerator(Arrays.asList("authoringEnvironment", "behaviors", "collisions", "game", "gameElements",
					"gameplayer", "goals", "highscoretable", "HUD", "interfaces", "keyboard", "level",
					"spriteProperties"));
		}
		
		return instance;
	}
	
	public static <R>  Map<String, Class<R>> getSubclasses(String packageName, Class<R> superclass) {
		Map<String, Class<R>> subclassNameMap = new HashMap<String, Class<R>>();
		List<Class<?>> packageClasses = new ArrayList<Class<?>>();
		try {
			packageClasses = getClasses(packageName);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		
		for (Class<?> candidateClass : packageClasses) {
		    if (superclass.isAssignableFrom(candidateClass)) {
		        String subclassName = candidateClass.getTypeName();
				subclassNameMap.put(subclassName, (Class<R>) candidateClass);
		    }
		}
		
		return subclassNameMap;
	}
	
	public static <R>  Map<String, Class<R>> getAllSubclasses(Class<R> clazz) {
		Map<String, Class<R>> allSubclasses = new HashMap<String, Class<R>>();
		
		for (String p : packageNames) {
			allSubclasses.putAll(getSubclasses(p, clazz));
		}
		
		return allSubclasses;
	}
	
	public static List<String> getAllSimpleClassNames() {
		List<String> allClassNames = new ArrayList<String>();
				
		for (String p : packageNames) {
			allClassNames.addAll(getSimpleClassNames(p));
		}
		
		return allClassNames;
	}
	
	public static List<String> getSimpleClassNames(String packageName) {
		List<Class<?>> clazzes = new ArrayList<Class<?>>();
		try {
			clazzes = getClasses(packageName);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		List<String> readableClasses = new ArrayList<String>();
		for(Class<?> c : clazzes) {
			readableClasses.add(c.getName());
		}
		
		return readableClasses;
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
