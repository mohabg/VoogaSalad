package authoringEnvironment.settingsWindow.ObjectEditorFactory;

import java.util.List;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.ObjectEditorConstants;

public class ObjectEditorController {
	ObjectEditorConstants myConstants;
	VisualFactory myVisualFactory;
	
	public ObjectEditorController(List<String> packageNames) {	
		myConstants = ObjectEditorConstants.getInstance();
		myConstants.setPackageNames(packageNames);
		
		myVisualFactory = new VisualFactory();
	}
}
