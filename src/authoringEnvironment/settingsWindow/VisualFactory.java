package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import resources.ResourcesReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author David Yan, Joe Jacob, Huijia Yu
 */
public class VisualFactory {
	private Settings mySettings;
	private ResourcesReader myReader;
	private int poo;
	
	public VisualFactory() {
		mySettings = new Settings();
	}

	public String reflectionTest(Sprite mySprite) {

		return "";
	}

	public static void main(String[] args) {
		System.out.println("sps");
		Field[] poo = SimpleDoubleProperty.class.getDeclaredFields();
		System.out.println(poo.length);
		for (Field p : poo) {
			p.setAccessible(true);
			System.out.println(p.getGenericType().getTypeName());
		}
		
		List<Field> ls = getAllFields(new ArrayList<Field>(), SimpleDoubleProperty.class);
		System.out.println(ls.size());
	}

	public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
	    fields.addAll(Arrays.asList(type.getDeclaredFields()));

	    if (type.getSuperclass() != null) {
	        fields = getAllFields(fields, type.getSuperclass());
	    }

	    return fields;
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
		System.out.println(pp.getClass().toGenericString());
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
		String tabName = f.getName();
		Tab myTab = new Tab(tabName);
		VBox myBox = new VBox();
		AnchorPane myPane = new AnchorPane();
		myBox.getChildren().addAll(makePropertyBoxes(f), new ArrayList<Node>());
		//myBox.getChildren().add(oneSpinner(f, mySprite));
		// Field[] properties = f.getType().getDeclaredFields();
		// for (Field p: properties){
		// myBox.getChildren().add(oneSpinner(p));
		// }

		myPane.getChildren().add(myBox);
		myTab.setContent(myPane);
		return myTab;
	}
	
	private List<Node> makePropertyBoxes(Field f, List<Node> properties) {
		Constructor<?>[] fieldConstructors = f.getType().getConstructors();
		// sorting comparator
		Comparator<Constructor> byParamNumber= 
				(Constructor c1, Constructor c2) -> c1.getParameterCount() >= c2.getParameterCount() ? 1:-1;
//		Arrays.sort(fieldConstructors, (Constructor c1, Constructor c2) -> {
//			return a.getParameterCount() > b.getParameterCount();
//		});
		
		
		properties.addAll(Arrays.asList(type.getDeclaredFields()));

	    if (type.getSuperclass() != null) {
	        fields = getAllFields(fields, type.getSuperclass());
	    }

	    return properties;
	}
	
	private Spinner oneSpinner(Field p, Sprite mySprite) {
		p.setAccessible(true);
		Spinner mySpinner = new Spinner();
		SpinnerValueFactory factory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 0);
		mySpinner.setValueFactory(factory);
		mySpinner.setEditable(true);
		System.out.println("+++++++" + p.getType().toGenericString());

		// p.getType().asSubclass(Property.class)
//			System.out.println("----" + p.getType().toGenericString());

			try {
				Property thisproperty = (Property) p.get(mySprite);
				factory.valueProperty().bindBidirectional(thisproperty);
			} catch (Exception e){
				e.printStackTrace();
			}
		
		return mySpinner;

	}

}
