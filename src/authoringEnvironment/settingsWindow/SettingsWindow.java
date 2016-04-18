package authoringEnvironment.settingsWindow;

import authoringEnvironment.FrontEndData;
import authoringEnvironment.Settings;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

		HBox myTempBox = new HBox();
		Label myLabel = new Label("Example Property:");
        myLabel.getStylesheets().add(FrontEndData.STYLESHEET);
		Slider mySlider = new Slider(0,100,50);
		Settings.setSliderSettings(mySlider);
		myTempBox.getChildren().addAll(myLabel, mySlider);
		myDisplay.getChildren().add(myTempBox);
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
