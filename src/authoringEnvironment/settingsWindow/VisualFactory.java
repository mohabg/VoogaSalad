package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import spriteProperties.Behavior;
import spriteProperties.NumProperty;
import spriteProperties.WeaponProperty;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by davidyan on 4/9/16.
 */
public class VisualFactory {
    Settings mySettings;

    public VisualFactory(){
        mySettings = new Settings();
    }

    public List<HBox> getHBoxes(List<Behavior> myBehaviors){
        List<HBox> myBoxes = new ArrayList<HBox>();
        myBehaviors.forEach(behavior -> {
            if(behavior instanceof NumProperty){
                HBox myTempBox = new HBox();
                myTempBox.setPadding(new Insets(20,0,0,0));
                Label myLabel = new Label(behavior.toString());
                Slider mySlider = new Slider(0, 100, ((NumProperty) behavior).getMyValue()/2);
                mySettings.setSliderSettings(mySlider);
                mySlider.valueProperty().bindBidirectional(((NumProperty) behavior).getDouble());
                myTempBox.getChildren().addAll(myLabel, mySlider);
                myTempBox.setAlignment(Pos.CENTER);
                myBoxes.add(myTempBox);
            }
            else if(behavior instanceof WeaponProperty){
                HBox myTempBox = new HBox();
                myTempBox.setPadding(new Insets(20,0,0,0));
                Label myLabel = new Label(behavior.toString());
                Spinner mySpinner = new Spinner();
                SpinnerValueFactory factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0);
                mySpinner.setValueFactory(factory);
                mySpinner.setEditable(true);
                factory.valueProperty().bindBidirectional(((WeaponProperty) behavior).myNumBulletsProperty());
                myTempBox.getChildren().add(myLabel);
                myTempBox.getChildren().add(mySpinner);
                myTempBox.setAlignment(Pos.CENTER);
                myBoxes.add(myTempBox);

            }

        }

        );


        return myBoxes;
    }

}
