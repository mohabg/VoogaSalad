package authoringEnvironment.settingsWindow.ObjectEditorFactory;

import java.util.List;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.Constants;

public class ObjectEditorController {
	Constants myConstants;
	VisualFactory myVisualFactory;
	
	public ObjectEditorController(List<String> packageNames) {	
		myConstants = Constants.getInstance();
		myConstants.setPackageNames(packageNames);
		
		myVisualFactory = new VisualFactory();
	}
}
