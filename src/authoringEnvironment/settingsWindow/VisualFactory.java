package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import gameElements.Sprite;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    public Constructor[] getConstructors(Class myClass){
        return myClass.getConstructors();
    }

    public HBox makeNewSpinner(Parameter myParam){
        HBox myBox = new HBox(8);
        myBox.setPadding(new Insets(20,20,20,20));
        Label myLabel = new Label(myParam.getName());
        Spinner mySpinner = new Spinner();
        SpinnerValueFactory factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0);
        mySpinner.setValueFactory(factory);
        mySpinner.setEditable(true);
        //TODO: Possibly use param to bind spinner to?
        myBox.getChildren().addAll(myLabel,mySpinner);
        return myBox;
    }

    public static HBox makeTextInputBox(Parameter myParam, Field f){
        Label label1 = new Label("This:");
        TextField textField = new TextField(f.toString());
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField);
        hb.setSpacing(10);
        //TODO: Bind this to parameter if possible
        return hb;
    }

    public static Parameter[] getLongestParams(Class myClass){
        for(Constructor constructor: myClass.getConstructors()){
            if(constructor.getParameterCount() == longestConstructor(myClass)){
                return constructor.getParameters();
            }
        }
        return new Parameter[0];

    }


    //TODO: Binding and figuring out list of objects in reflection
    public TabPane getMyTabs (Sprite mySprite){
        TabPane myTabs = new TabPane();
//        Tab myTab = new Tab("TESTING");
//        AnchorPane myPane = new AnchorPane();
//        Spinner mySpinner = new Spinner();
//        SpinnerValueFactory factory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 0 );
//        mySpinner.setValueFactory(factory);
//        mySpinner.setEditable(true);
//        factory.valueProperty().bindBidirectional(mySprite.getMySpriteProperties().getMyX());
//        myPane.getChildren().add(mySpinner);
//        myTab.setContent(myPane);
//        myTabs.getTabs().add(myTab);
        Field[] fields = mySprite.getClass().getDeclaredFields();
        for (Field f : fields) {
            Tab myTab = new Tab("Tab");
            AnchorPane myTempPane = new AnchorPane();
            f.setAccessible(true);
            VBox myBox = new VBox();
            try {
                Class clazz = Class.forName(f.getGenericType().getTypeName());

            } catch (ClassNotFoundException e) {
            }
            myTempPane.getChildren().add(myBox);
            myTab.setContent(myTempPane);
            myTabs.getTabs().add(myTab);
        }

        return myTabs;
    }


}
