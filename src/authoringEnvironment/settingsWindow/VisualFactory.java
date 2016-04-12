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

    private <T> List<T> makeList(Class<T> type) {
    	List<T> pp = new ArrayList<T>();
    	System.out.println(pp.getClass().toGenericString());
    	return new ArrayList<T>();
    }
    //TODO: Binding and figuring out list of objects in reflection
    public TabPane getMyTabs (Sprite mySprite){
        TabPane myTabs = new TabPane();
        Tab myTab = new Tab("TESTING");
        VBox myBox = new VBox();
        AnchorPane myPane = new AnchorPane();
        Spinner mySpinner = new Spinner();
        SpinnerValueFactory factory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 0 );
        mySpinner.setValueFactory(factory);
        mySpinner.setEditable(true);
        factory.valueProperty().bindBidirectional(mySprite.getMySpriteProperties().getMyX());


        Spinner mySpinner2 = new Spinner();
        SpinnerValueFactory factory2 = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 0 );
        mySpinner2.setValueFactory(factory2);
        mySpinner2.setEditable(true);
        factory2.valueProperty().bindBidirectional(mySprite.getMySpriteProperties().getMyY());



        myBox.getChildren().add(mySpinner);
        myBox.getChildren().add(mySpinner2);

        myPane.getChildren().add(myBox);
        myTab.setContent(myPane);
        myTabs.getTabs().add(myTab);
//        Field[] fields = mySprite.getClass().getDeclaredFields();
//        for (Field f : fields) {
//            Tab myTab = new Tab("Tab");
//            AnchorPane myTempPane = new AnchorPane();
//            f.setAccessible(true);
//            VBox myBox = new VBox();
//            try {
//            	Type k = f.getGenericType();
//            	System.out.println(k.getTypeName());
//            	if (k instanceof ParameterizedType) {
//            		ParameterizedType pt = (ParameterizedType) k;
//
//            		System.out.println(((Class) pt.getRawType()));
//            		//System.out.println((Class)(.getClass().getGenericSuperclass()));
//	            	Type[] ts = pt.getActualTypeArguments();
//	            	Class lkk = (Class) Class.forName(ts[0].getTypeName());
//	            	List p = makeList(lkk);
//	            	System.out.println(p.getClass().toGenericString());
//
//	            	for (Type t : ts) {
//	            		System.out.println("poo");
//	            		System.out.println(t.toString());
//	            	}
//	            	System.out.println("dig");
//	            	System.out.println(((ParameterizedType) k).getRawType());
//            	}
//
//                Class clazz = (Class) Class.forName(f.getGenericType().getTypeName());
//                for(Parameter param: getLongestParams(clazz)){
//                    if(param.getType().equals(int.class) || param.getType().equals(double.class)){
//                        System.out.println("here");
//                        myBox.getChildren().add(makeNewSpinner(param));
//                    }
//                    if(param.getType().isAssignableFrom(String.class)){
//                        myTempPane.getChildren().add(makeTextInputBox(param,f));
//                    }
//                }
//            } catch (ClassNotFoundException e) {
//            }
//            myPane.getChildren().add(myBox);
//            myTab.setContent(myPane);
//            myTabs.getTabs().add(myTab);

        return myTabs;
    }


}
