package authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public String getStylesheet(StylesheetType type) {
		return STYLESHEETS.get(type);
	}
	
	public void setStylesheet(StylesheetType type, String stylesheet) {
		STYLESHEETS.put(type, stylesheet);
	}
	
	public List<String> getPackageNames() {
		return PACKAGE_NAMES;
	}

	public void setPackageNames(List<String> PACKAGE_NAMES) {
		this.PACKAGE_NAMES = PACKAGE_NAMES;
	}

	public List<String> getSimpleClassNames() {
		return SIMPLE_CLASS_NAMES;
	}

	public void setSimpleClassNames(List<String> SIMPLE_CLASS_NAMES) {
		this.SIMPLE_CLASS_NAMES = SIMPLE_CLASS_NAMES;
	}

	public List<Class<?>> getAllClasses() {
		return ALL_CLASSES;
	}

	public void setAllClasses(List<Class<?>> ALL_CLASSES) {
		this.ALL_CLASSES = ALL_CLASSES;
	}
}
