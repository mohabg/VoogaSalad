package authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectEditorConstants {
	private List<String> PACKAGE_NAMES;
	private List<String> SIMPLE_CLASS_NAMES;
	private Map<StylesheetType, String> STYLESHEETS;

	private static ObjectEditorConstants instance = null;

	private ObjectEditorConstants() {
		PACKAGE_NAMES = Arrays.asList("authoringEnvironment", "behaviors", "collisions", "game", "gameElements",
				"gameplayer", "goals", "highscoretable", "HUD", "interfaces", "keyboard", "level",
				"spriteProperties");
		SIMPLE_CLASS_NAMES = new ArrayList<String>();

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
	
	public List<String> getPackageNames() {
		return PACKAGE_NAMES;
	}

	public void setPackageNames(List<String> PACKAGE_NAMES) {
		this.PACKAGE_NAMES = PACKAGE_NAMES;
	}

	public List<String> getSimpleClassNames() {
		return SIMPLE_CLASS_NAMES;
	}

	public void setSimpeClassNames(List<String> SIMPLE_CLASS_NAMES) {
		this.SIMPLE_CLASS_NAMES = SIMPLE_CLASS_NAMES;
	}
}
