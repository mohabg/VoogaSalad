package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import gameElements.Sprite;
import javafx.beans.property.Property;
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
 * @author David Yan, Joe Jacob, Huijia Yu
 */
public class VisualFactory {
	private Settings mySettings;
	private ResourcesReader myReader;

	public VisualFactory() {
		mySettings = new Settings();
	}

	public String reflectionTest(Sprite mySprite) {

		return "";
	}

	public static void main(String[] args) {

	}

	public Constructor[] getConstructors(Class myClass) {
		return myClass.getConstructors();
	}

	// public HBox makeNewSpinner(Parameter myParam){
	// HBox myBox = new HBox(8);
	// myBox.setPadding(new Insets(20,20,20,20));
	// Label myLabel = new Label(myParam.getName());
	// Spinner mySpinner = new Spinner();
	// SpinnerValueFactory factory = new
	// SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0);
	// mySpinner.setValueFactory(factory);
	// mySpinner.setEditable(true);
	// //TODO: Possibly use param to bind spinner to?
	// factory.valueProperty().bindBidirectional(mySprite.getMySpriteProperties().getMyY());
	//
	//
	// myBox.getChildren().addAll(myLabel,mySpinner);
	// return myBox;
	//
	//
	// Spinner mySpinner2 = new Spinner();
	// SpinnerValueFactory factory2 = new
	// SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 0 );
	// mySpinner2.setValueFactory(factory2);
	// mySpinner2.setEditable(true);
	//
	//
	//
	// }

	public static HBox makeTextInputBox(Parameter myParam, Field f) {
		Label label1 = new Label("This:");
		TextField textField = new TextField(f.toString());
		HBox hb = new HBox();
		hb.getChildren().addAll(label1, textField);
		hb.setSpacing(10);
		// TODO: Bind this to parameter if possible
		return hb;
	}

	private <T> List<T> makeList(Class<T> type) {
		List<T> pp = new ArrayList<T>();
		return new ArrayList<T>();
	}

	// TODO: Binding and figuring out list of objects in reflection
	public TabPane getMyTabs(Sprite mySprite) {
		TabPane myTabs = new TabPane();

		Field[] fields = mySprite.getClass().getDeclaredFields();

		for (Field f : fields) {
			myTabs.getTabs().add(getOneTab(f, mySprite));

		}
		//
		return myTabs;
	}

	private Tab getOneTab(Field f, Sprite mySprite) {
		f.setAccessible(true);
		Tab myTab = new Tab(f.getName());
		VBox myBox = new VBox();
		AnchorPane myPane = new AnchorPane();
//		myBox.getChildren().add(oneSpinner(f, mySprite));
		 Field[] properties = f.getType().getDeclaredFields();
		 for (Field p: properties){
				p.setAccessible(true);

		 try {
			myBox.getChildren().add(oneSpinner(p, f.get(mySprite)));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 }

		myPane.getChildren().add(myBox);
		myTab.setContent(myPane);
		return myTab;
	}

	private Spinner oneSpinner(Field p, Object object) {

		Spinner mySpinner = new Spinner();
		SpinnerValueFactory factory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 0);
		mySpinner.setValueFactory(factory);
		mySpinner.setEditable(true);

		// p.getType().asSubclass(Property.class)
//			System.out.println("----" + p.getType().toGenericString());

			try {
				Property thisproperty = (Property) p.get(object);
				factory.valueProperty().bindBidirectional(thisproperty);
			} catch (Exception e){
//				e.printStackTrace();
			}
		
		return mySpinner;

	}

}
