package authoringEnvironment.settingsWindow.ObjectEditorFactory.Utilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.ObjectEditorConstants;

/**
 * Helpful reflection-based functions that deal with finding classes and their subclasses
 * @author joejacob
 */

public class ClassEnumerator {	
	public static String USER_DIR = "user.dir";
	public static String DOT = ".";
	public static String SLASH = "/";
	public static String SPACE = " ";
	public static String REPLACE_SPACE = "/";
	public static String FILE_EXT = ".class";
	
	/**
	 * returns all of the classes in the in this project within the packages specified in the ObjectEditorController
	 * @return
	 */
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
	
	/**
	 *  returns all of the classes in the in this project that are a subclass of superclass
	 * @param superclass
	 * @return
	 */
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
	

	/**
	 * returns all of the class names, retrieved by the Class.getName() method
	 * @return
	 */
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
        String path = packageName.replace(DOT, SLASH);
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            String relative = new File(System.getProperty(USER_DIR)
            		.replaceAll(SPACE, "%20"))
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
                assert !file.getName().contains(DOT);
                classes.addAll(findClasses(file, packageName + DOT + file.getName()));
            } else if (file.getName().endsWith(FILE_EXT)) {
                classes.add(Class.forName(packageName + DOT + file.getName().substring(0, file.getName().length() - FILE_EXT.length())));
            }
        }
        return classes;
    }



}
