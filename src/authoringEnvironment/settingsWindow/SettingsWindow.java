package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Main.VisualFactory;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import resources.FrontEndData;

/**
 * @author David Yan
 * Simple class to set the content of the SettingsPane aspect of the Authoring Environment
 */
public class SettingsWindow {
	private VBox myDisplay;
   

	public SettingsWindow(){
		myDisplay = new VBox();
		Settings.setSettingsDisplaySettings(myDisplay);
	}

	/**
     * @param mySpriteBox is a VBox that contains all of the of the editable properties
     * that a user can change in the settings window. The VBox is filled with HBoxes
     * that have been created in the VisualFactor.
     */
	public void setContent(VBox mySpriteBox){
		myDisplay.getChildren().setAll(mySpriteBox);
//        myDisplay.getChildren().clear();
//        myDisplay.getChildren().add(mySpriteBox);
    }
	
	public VBox getBox(){
		return myDisplay;
	}


}
