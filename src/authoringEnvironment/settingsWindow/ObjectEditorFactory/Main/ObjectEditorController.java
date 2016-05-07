package authoringEnvironment.settingsWindow.ObjectEditorFactory.Main;

import java.util.List;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.ObjectEditorConstants;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.StylesheetType;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Utilities.ClassEnumerator;
import javafx.scene.control.TabPane;

/**
 * controller class for ObjectEditorFactory utility
 * @author joejacob
 */
public class ObjectEditorController {
	ObjectEditorConstants myConstants;
	VisualFactory myVisualFactory;
	
	/**
	 * initializes controller with the package names in the project
	 * @param packageNames
	 */
	public ObjectEditorController(List<String> packageNames) {	
		myConstants = ObjectEditorConstants.getInstance();
		myConstants.setPackageNames(packageNames);
		myConstants.setAllClasses(ClassEnumerator.getAllClasses());
		myConstants.setSimpleClassNames(ClassEnumerator.getAllSimpleClassNames());
		
		
		myVisualFactory = new VisualFactory();
	}
	
	/**
	 * adds a stylesheet for a specified GUI object type
	 * @param type
	 * @param stylesheet
	 */
	public void addObjectStylesheet(StylesheetType type, String stylesheet) {
		myConstants.setStylesheet(type, stylesheet);
	}
	
	/**
	 * returns the tabPane for a corresponding object
	 * displays all instance vars in a readable/editable manner
	 * @param obj
	 * @return
	 */
	public TabPane makeObjectEditorTabPane(Object obj) {
		return myVisualFactory.getMyTabs(obj);
	}
}
