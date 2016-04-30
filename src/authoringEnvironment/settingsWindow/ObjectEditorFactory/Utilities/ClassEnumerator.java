package authoringEnvironment.settingsWindow.ObjectEditorFactory.Utilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.ObjectEditorConstants;

public class ClassEnumerator {
	
	public static List<Class<?>> getAllClasses() {
		List<Class<?>> allClasses = new ArrayList<Class<?>>();
		
		for (String packageName : ObjectEditorConstants.getInstance().getPackageNames()) {
			try {
				allClasses.addAll(getClasses(packageName));
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		
		return allClasses;
	}
	
	@SuppressWarnings("unchecked")
	public static <R>  Map<String, Class<R>> getAllSubclasses(Class<R> superclass) {
		Map<String, Class<R>> subclassNameMap = new HashMap<String, Class<R>>();
		List<Class<?>> packageClasses = ObjectEditorConstants.getInstance().getAllClasses();
	
		for (Class<?> candidateClass : packageClasses) {
		    if (superclass.isAssignableFrom(candidateClass)) {
		        String subclassName = candidateClass.getTypeName();
				subclassNameMap.put(subclassName, (Class<R>) candidateClass);
		    }
		}
		
		return subclassNameMap;
	}
	

	public static List<String> getAllSimpleClassNames() {
		List<String> readableClasses = new ArrayList<String>();
		for(Class<?> c : ObjectEditorConstants.getInstance().getAllClasses()) {
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
            String relative = new File(System.getProperty("user.dir")
            		.replaceAll(" ", "%20"))
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
