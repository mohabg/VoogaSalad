package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import gameElements.Sprite;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import resources.ResourcesReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

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


    public String reflectionTest(Sprite mySprite){

        return "";
    }

    public static void main(String[] args){



    }



    public TabPane getMyTabs (Sprite mySprite){
        TabPane myTabs = new TabPane();
        Field[] fields = mySprite.getClass().getDeclaredFields();
        for (Field f : fields) {
            Tab myTab = new Tab("Tab");
            AnchorPane myTempPane = new AnchorPane();
            f.setAccessible(true);
            try {
                Class clazz = Class.forName(f.getGenericType().getTypeName());
                    for(Constructor constructor: clazz.getConstructors()) {
                        if (constructor.getParameterCount() == longestConstructor(clazz)) {
                            for(Parameter param: constructor.getParameters()){
                                if(param.getType().equals(int.class) || param.getType().equals(double.class)){
                                    Spinner mySpinner = new Spinner();
                                    SpinnerValueFactory factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0);
                                    mySpinner.setValueFactory(factory);
                                    mySpinner.setEditable(true);
                                    myTempPane.getChildren().add(mySpinner);
                                }
                                if(param.getType().isAssignableFrom(String.class)){
                                    Label label1 = new Label("This:");
                                    TextField textField = new TextField(f.toString());
                                    HBox hb = new HBox();
                                    hb.getChildren().addAll(label1, textField);
                                    hb.setSpacing(10);
                                    myTempPane.getChildren().add(hb);
                                }
                            }
                        }
                    }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            myTab.setContent(myTempPane);
            myTabs.getTabs().add(myTab);
        }

        return myTabs;
    }


    /**
     * try {


     //TODO: Replace these with class names found in properties files
     for(Constructor constructor: clazz.getConstructors()){
     if (constructor.getParameterCount() == longestConstructor(clazz)) {
     for(Parameter param: constructor.getParameters()){
     if(param.getType().equals(int.class) || param.getType().equals(double.class)){
     //TODO: take the objects here and integrate them to be displayed in the settings window

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
     */








    /**
     *         List<HBox> myBoxes = new ArrayList<HBox>();
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

     */

}
