package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import spriteProperties.Behavior;
import spriteProperties.NumProperty;
import spriteProperties.WeaponProperty;

import java.util.*;
/**
 * Created by davidyan on 4/9/16.
 */
public class VisualFactory {
    List<Behavior> myBehaviorList;
    Settings mySettings;

    public VisualFactory(List<Behavior> myBehaviors){
        myBehaviorList = myBehaviors;
        mySettings = new Settings();
    }

    private List<HBox> getHBoxes(List<Behavior> myBehaviors){
        List<HBox> myBoxes = new ArrayList<HBox>();
        myBehaviors.forEach(behavior -> {
            if(behavior instanceof NumProperty){
                HBox myTempBox = new HBox();
                myTempBox.setPadding(new Insets(20,0,0,0));
                Label myLabel = new Label(behavior.toString());
                Slider mySlider = new Slider(0, 100, ((NumProperty) behavior).getMyValue());
                mySettings.setSliderSettings(mySlider);
                mySlider.valueProperty().bindBidirectional(((NumProperty) behavior).getDouble());
                myTempBox.getChildren().addAll(myLabel, mySlider);
                myTempBox.setAlignment(Pos.CENTER);
                myBoxes.add(myTempBox);
            }
            else if(behavior instanceof WeaponProperty){

            }

        }

        );


        return myBoxes;
    }

}
