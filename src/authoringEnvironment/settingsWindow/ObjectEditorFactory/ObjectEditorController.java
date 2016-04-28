package authoringEnvironment.settingsWindow.ObjectEditorFactory;

import java.util.List;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.ObjectEditorConstants;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.StylesheetType;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Utilities.SubclassEnumerator;
import javafx.scene.control.TabPane;

public class ObjectEditorController {
	ObjectEditorConstants myConstants;
	VisualFactory myVisualFactory;
	
	public ObjectEditorController(List<String> packageNames) {	
		myConstants = ObjectEditorConstants.getInstance();
		myConstants.setPackageNames(packageNames);
		myConstants.setSimpleClassNames(SubclassEnumerator.getAllSimpleClassNames());
		myVisualFactory = new VisualFactory();
	}
	
	public void addObjectStylesheet(StylesheetType type, String stylesheet) {
		myConstants.setStylesheet(type, stylesheet);
	}
	
	public TabPane makeObjectEditorTabPane(Object obj) {
		return myVisualFactory.getMyTabs(obj);
	}
}