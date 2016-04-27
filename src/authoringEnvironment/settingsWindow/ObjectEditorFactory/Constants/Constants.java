package authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
	private List<String> PACKAGE_NAMES;
	private List<String> SIMPLE_CLASS_NAMES;
	
	private static Constants instance = null;
	
	private Constants() {
		PACKAGE_NAMES = Arrays.asList("authoringEnvironment", "behaviors", "collisions", "game", "gameElements",
				"gameplayer", "goals", "highscoretable", "HUD", "interfaces", "keyboard", "level",
				"spriteProperties");
		setSimpeClassNames(new ArrayList<String>());
	}
	
	public static Constants getInstance() {
		if (instance == null) {
			instance = new Constants();
		}
		return instance;
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
