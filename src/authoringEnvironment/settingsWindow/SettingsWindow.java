package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import resources.FrontEndData;

/**
 * @author David Yan
 */
public class SettingsWindow {
	private VBox myDisplay;
    private VisualFactory myVisualFactory;

	public SettingsWindow(){
        myVisualFactory = new VisualFactory();
		myDisplay = new VBox();
		Settings.setSettingsDisplaySettings(myDisplay);
	}

	/**
     * @param mySpriteBox is a VBox that contains all of the of the editable properties
     * that a user can change in the settings window. The VBox is filled with HBoxes
     * that have been created in the VisualFactor.
     */
	public void setContent(VBox mySpriteBox){
        myDisplay.getChildren().clear();
        myDisplay.getChildren().add(mySpriteBox);
    }
	
	public VBox getBox(){
		return myDisplay;
	}

    public VisualFactory getMyVisualFactory(){
        return myVisualFactory;
    }

}
