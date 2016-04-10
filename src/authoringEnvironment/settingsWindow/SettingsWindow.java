package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class SettingsWindow {
	private Settings settings;
	private VBox myDisplay;
    private VisualFactory myVisualFactory;

	public SettingsWindow(){
		settings = new Settings();
        myVisualFactory = new VisualFactory();
		myDisplay = new VBox();
		settings.setSettingsDisplaySettings(myDisplay);

		HBox myTempBox = new HBox();
		Label myLabel = new Label("Example Property:");
		Slider mySlider = new Slider(0,100,50);
		settings.setSliderSettings(mySlider);
		myTempBox.getChildren().addAll(myLabel, mySlider);
		myDisplay.getChildren().add(myTempBox);
	}

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
