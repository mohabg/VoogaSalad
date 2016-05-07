package authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * constants singleton class used by utility for CSS stylesheets and class names in order to
 * reduce reflection overhead
 * @author joejacob
 */
public class ObjectEditorConstants {
	private List<String> PACKAGE_NAMES;
	private List<String> SIMPLE_CLASS_NAMES;
	private List<Class<?>> ALL_CLASSES;
	private Map<StylesheetType, String> STYLESHEETS;

	private static ObjectEditorConstants instance = null;

	
	private ObjectEditorConstants() {
		PACKAGE_NAMES = new ArrayList<String>();
		SIMPLE_CLASS_NAMES = new ArrayList<String>();
		ALL_CLASSES = new ArrayList<Class<?>>();
		
		initStylesheets();
	}

	public static ObjectEditorConstants getInstance() {
		if (instance == null) {
			instance = new ObjectEditorConstants();
		}
		return instance;
	}

	private void initStylesheets() {
		STYLESHEETS = new HashMap<StylesheetType, String>();
		for (StylesheetType type : StylesheetType.values()) {
			STYLESHEETS.put(type, "");
		}
	}
	
	/**
	 * gets Stylesheet correlated to type
	 * @param type
	 * @return
	 */
	public String getStylesheet(StylesheetType type) {
		return STYLESHEETS.get(type);
	}
	
	/**
	 * sets Stylesheet correlated to type
	 * @param type
	 * @param stylesheet
	 */
	public void setStylesheet(StylesheetType type, String stylesheet) {
		STYLESHEETS.put(type, stylesheet);
	}
	
	/**
	 * returns the names of all the packages defined in the ObjectEditorController constructor
	 * @return
	 */
	public List<String> getPackageNames() {
		return PACKAGE_NAMES;
	}

	/**
	 * sets the list of package names 
	 * @param PACKAGE_NAMES
	 */
	public void setPackageNames(List<String> PACKAGE_NAMES) {
		this.PACKAGE_NAMES = PACKAGE_NAMES;
	}

	/**
	 * returns readable names of the classes in the project in the package names defined
	 * @return
	 */
	public List<String> getSimpleClassNames() {
		return SIMPLE_CLASS_NAMES;
	}

	/**
	 * sets the readable class names 
	 * @param SIMPLE_CLASS_NAMES
	 */
	public void setSimpleClassNames(List<String> SIMPLE_CLASS_NAMES) {
		this.SIMPLE_CLASS_NAMES = SIMPLE_CLASS_NAMES;
	}

	/**
	 * returns all of the Class objects representing all the classes in the packages denoted by PACKAGE_NAMES
	 * @return
	 */
	public List<Class<?>> getAllClasses() {
		return ALL_CLASSES;
	}

	/**
	 * sets all of the Class objects representing all the classes in the packages denoted by PACKAGE_NAMES
	 * @param ALL_CLASSES
	 */
	public void setAllClasses(List<Class<?>> ALL_CLASSES) {
		this.ALL_CLASSES = ALL_CLASSES;
	}
}
