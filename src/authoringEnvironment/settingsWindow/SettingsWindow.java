package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import javafx.geometry.Insets;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import level.LevelProperties;
import resources.FrontEndData;

/**
 * @author David Yan
 */
public class SettingsWindow {
	private VBox myDisplay;
    private VisualFactory myVisualFactory;
    private LevelProperties myLevelProperties;

	public SettingsWindow(){
        myVisualFactory = new VisualFactory();
		myDisplay = new VBox();
        myDisplay.setPadding(new Insets(FrontEndData.VBOX_SPACING,FrontEndData.VBOX_SPACING,FrontEndData.VBOX_SPACING,FrontEndData.VBOX_SPACING));
		myLevelProperties = new LevelProperties();
        Settings.setSettingsDisplaySettings(myDisplay);
        setContent(setSettingsContent(myLevelProperties));
//		HBox myTempBox = new HBox();
//        myTempBox.setPadding(new Insets(FrontEndData.VBOX_SPACING,FrontEndData.VBOX_SPACING,FrontEndData.VBOX_SPACING,FrontEndData.VBOX_SPACING));
//		Label myLabel = new Label("Example Property:");
//        myLabel.getStylesheets().add(FrontEndData.STYLESHEET);
//		Slider mySlider = new Slider(0,100,50);
//		Settings.setSliderSettings(mySlider);
//		myTempBox.getChildren().addAll(myLabel, mySlider);
//        myTempBox.setAlignment(Pos.CENTER);

//		myDisplay.getChildren().add(myTempBox);
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

    public VBox setSettingsContent(LevelProperties spriteModel) {
        VBox myBox = new VBox(FrontEndData.VBOX_SPACING);
        TabPane propertiesPane = new TabPane();
        propertiesPane = getMyVisualFactory().getMyTabs(spriteModel);
        myBox.getChildren().addAll(propertiesPane);
        return myBox;
    }
	public VBox getBox(){
		return myDisplay;
	}

    public VisualFactory getMyVisualFactory(){
        return myVisualFactory;
    }

}
