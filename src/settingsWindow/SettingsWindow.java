package settingsWindow;

import spriteProperties.Health;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import spriteProperties.Attack;
import spriteProperties.Defense;

import java.awt.*;
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

	public void setContent(Health myHealth, Attack myAttack, Defense myDefense){
        myDisplay.getChildren().removeAll();
		HBox myTempBox = new HBox();
		Label myLabel = new Label(myHealth.toString());
		Slider mySlider = new Slider(0,myHealth.getHealth(),50);
		mySlider.setShowTickMarks(true);
		mySlider.setShowTickLabels(true);

        HBox myTempBox2 = new HBox();
        Label myLabel2 = new Label(myAttack.toString());
        Slider mySlider2 = new Slider(0,myAttack.getMyAttack(),50);
        mySlider2.setShowTickMarks(true);
        mySlider2.setShowTickLabels(true);

        HBox myTempBox3 = new HBox();
        Label myLabel3 = new Label(myDefense.toString());
        Slider mySlider3 = new Slider(0,myDefense.getMyDefense(),50);
        mySlider3.setShowTickMarks(true);
        mySlider3.setShowTickLabels(true);


        myTempBox.getChildren().addAll(myLabel, mySlider);
        myTempBox2.getChildren().addAll(myLabel2, mySlider2);
        myTempBox3.getChildren().addAll(myLabel3, mySlider3);


        myDisplay.getChildren().add(myTempBox);
        myDisplay.getChildren().add(myTempBox2);
        myDisplay.getChildren().add(myTempBox3);

    }
	public VBox getBox(){
		return myDisplay;
	}

}
