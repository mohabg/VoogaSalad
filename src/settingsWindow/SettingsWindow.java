package settingsWindow;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import spriteProperties.NumProperty;

import java.awt.*;
import java.util.List;

public class SettingsWindow {
	private VBox myDisplay;
	private static final int myScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int myScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

	public SettingsWindow(){
		myDisplay = new VBox();
		HBox myTempBox = new HBox();
		Label myLabel = new Label("Example Property:");
		Slider mySlider = new Slider(0,100,50);
		mySlider.setShowTickMarks(true);
		mySlider.setShowTickLabels(true);
		myTempBox.getChildren().addAll(myLabel, mySlider);
		myDisplay.setPrefWidth(myScreenWidth*0.3);
		myDisplay.setPrefHeight(myScreenHeight);
		myDisplay.getChildren().add(myTempBox);
	}

	public void setContent(List<NumProperty> myProperties){
        myDisplay.getChildren().clear();
        for(NumProperty aProp: myProperties){
            HBox myTempBox = new HBox();
            Label myLabel = new Label(aProp.toString());
            Slider mySlider = new Slider(0,aProp.getMyValue(),aProp.getMyValue()/2);
            mySlider.setShowTickMarks(true);
            mySlider.setShowTickLabels(true);
            myTempBox.getChildren().addAll(myLabel, mySlider);
            myDisplay.getChildren().add(myTempBox);
        }

    }
	public VBox getBox(){
		return myDisplay;
	}

}
