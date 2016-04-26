package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.VisualFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
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
        myDisplay.setPadding(new Insets(FrontEndData.VBOX_SPACING,FrontEndData.VBOX_SPACING,FrontEndData.VBOX_SPACING,FrontEndData.VBOX_SPACING));
		Settings.setSettingsDisplaySettings(myDisplay);

		HBox myTempBox = new HBox();
        myTempBox.setPadding(new Insets(FrontEndData.VBOX_SPACING,FrontEndData.VBOX_SPACING,FrontEndData.VBOX_SPACING,FrontEndData.VBOX_SPACING));
		Label myLabel = new Label("Example Property:");
        myLabel.getStylesheets().add(FrontEndData.STYLESHEET);
		Slider mySlider = new Slider(0,100,50);
		Settings.setSliderSettings(mySlider);
		myTempBox.getChildren().addAll(myLabel, mySlider);
        myTempBox.setAlignment(Pos.CENTER);

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
