package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import resources.ResourcesReader;
import spriteProperties.Behavior;
import spriteProperties.NumProperty;
import spriteProperties.WeaponProperty;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidyan on 4/9/16.
 */
public class VisualFactory {
    private Settings mySettings;
    private ResourcesReader myReader;

    public VisualFactory(){
        mySettings = new Settings();
    }

    public void getAllKeysFromProperties(){


    }

    public static int longestConstructor(Class clazz){
        int max = 0;
        for(Constructor aConstructor: clazz.getConstructors()){
            if(aConstructor.getParameterCount() > max){
                max = aConstructor.getParameterCount();
            }
        }
        return max;
    }

    public static void main(String[] args){
        try {
            Class clazz = Class.forName("authoringEnvironment.ExampleAttack");
            //TODO: Replace these with class names found in properties files
            for(Constructor constructor: clazz.getConstructors()){
                if (constructor.getParameterCount() == longestConstructor(clazz)) {
                    for(Parameter param: constructor.getParameters()){
                        if(param.getType().equals(int.class) || param.getType().equals(double.class)){
                            //TODO: take the objects here and integrate them to be displayed in the settings window
//                            Spinner mySpinner = new Spinner();
//                            SpinnerValueFactory factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
//                            mySpinner.setValueFactory(factory);
//                            mySpinner.setEditable(true);
//                        factory.valueProperty().bindBidirectional();

                            System.out.println("yes number");
                        }
                        if(param.getType().equals(boolean.class)){
                            System.out.println("yes boolean");
                        }
                        if(param.getType().isAssignableFrom(String.class)){
                            System.out.println("yes string");

                        }
                    }
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
