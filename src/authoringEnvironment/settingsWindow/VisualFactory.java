package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import authoringEnvironment.SubclassEnumerator;
import gameElements.Sprite;
import javafx.beans.binding.NumberExpressionBase;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		String tabName = f.getName();
		Tab myTab = new Tab(tabName);
		VBox myBox = new VBox();
		AnchorPane myPane = new AnchorPane();
		System.out.println(f.getGenericType());
		Field[] fChildren = f.getType().getDeclaredFields();
		
		// if one of the first fields is just a Property
		// TODO DOESNT WORK YET
//		if (isAProperty(f, mySprite)) {
//			System.out.println("im hereeee");
//			try {
//				//Property pObject = (Property) p.get(parent);
//				//String pObjectName = p.getName();				
//				//properties.addAll(makePropertyBoxes(p, pObject, pObjectName, properties));
//				
//				Property fObject = (Property) f.get(mySprite);
//				String fObjectName = f.getName();		
//				System.out.println("pass2");
//				Set<HBox> props = makePropertyBoxes(f, fObject, fObjectName, new HashSet<HBox>());
//				//props.add(makeSettingsObject(pObject, pObjectName));
//				System.out.println("pass3444");
//				myBox.getChildren().addAll(props);
//			} catch (IllegalArgumentException | IllegalAccessException e) {}
//		}
		
		for (Field p : fChildren) {
				try {
					// o is the actual instance of f in the sprite
					Object o = f.get(mySprite);
					String parentName = f.getName();
					System.out.println("parent name " + parentName);
					p.setAccessible(true);
					Set<HBox> props = makePropertyBoxes(p, o, parentName, new HashSet<HBox>());
					myBox.getChildren().addAll(props);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		//myBox.getChildren().add(oneSpinner(f, mySprite));
		// Field[] properties = f.getType().getDeclaredFields();
		// for (Field p: properties){
		// myBox.getChildren().add(oneSpinner(p));
		// }
		// TODO I HAVE TO FIGURE OUT HOW I WOULD KNOW IT'S SPRITEPROPERTIES
		myPane.getChildren().add(myBox);
		myTab.setContent(myPane);
		return myTab;
	}
	
	private Set<HBox> makePropertyBoxes(Field p, Object parent, String parentName, Set<HBox> properties) {
		if (parent instanceof Property) {
			// the parent is a Property, we can make a settings element
			System.out.println("pass2323");
			HBox settingsHBox = makeSettingsObject(parent, parentName);
			properties.add(settingsHBox);
			//System.out.println("GOT TO FIRST THING");
			return properties;
		} else if (parent instanceof List) {
			
		} else if (parent instanceof Map) {
			
		} else if (parent instanceof gameElements.Sprite) {
			// results in infinite recursion for collision right now 
			return properties;
		}
		
		// is Field p a Property????
		boolean isProperty = isAProperty(p, parent);
		
		if (isProperty) {
			try {
				//System.out.println("IS A PROPERTY PASSED");
				Property pObject = (Property) p.get(parent);
				String pObjectName = p.getName();				
				properties.addAll(makePropertyBoxes(p, pObject, pObjectName, properties));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Field[] pFields = p.getType().getDeclaredFields();
			for (Field otherField : pFields) {
				otherField.setAccessible(true);
				Object o = new Object();
				try {
					o = otherField.get(p);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					System.out.println("wtf just happened");
					e.printStackTrace();
				}
				String pName = otherField.getName();
				properties.addAll(makePropertyBoxes(otherField, o, pName, properties));		
			}
			System.out.println("THIS DIDNT HAVE ANY FIELDS");
		}
		
		return properties;
	}
	
	private boolean isAProperty(Field p, Object o) {
		System.out.println(p.getType().getName());
		if (Property.class.isAssignableFrom(p.getType())) {
			System.out.println("im a porpppp");
			return true;
		}
//		try {
//			Property pClass = (Property) p.get(o);
//			return true;
//		} catch (IllegalAccessException e) {
//		} 
		return false;
	}
	
	private HBox makeSettingsObject(Object myProp, String propName) {
		HBox propHBox = new HBox();
		Label propLabelName = new Label(propName);
		if (myProp instanceof DoubleProperty) {
			DoubleProperty dp = (DoubleProperty) myProp;			
			propHBox.getChildren().addAll(propLabelName, makeDoubleSpinner(dp));
		} else if (myProp instanceof IntegerProperty) {
			IntegerProperty ip = (IntegerProperty) myProp;
			propHBox.getChildren().addAll(propLabelName, makeIntegerSpinner(ip));
		} else if (myProp instanceof BooleanProperty) {
			BooleanProperty bp = (BooleanProperty) myProp;
			propHBox.getChildren().addAll(propLabelName, makeBooleanCheckbox(bp));
		} else if (myProp instanceof StringProperty) {
			// THIS PROBABLY REFERS TO IMAGE FILES..............
			// DROP DOWN OF IMAGE FILES TO CHOOSE FROM
			StringProperty sp = (StringProperty) myProp;
			propHBox.getChildren().addAll(propLabelName, makeTextField(sp));
		}
		return propHBox;
	}

	private Spinner makeDoubleSpinner(DoubleProperty dp) {
		Spinner mySpinner = new Spinner();
		SpinnerValueFactory factory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 0);
		mySpinner.setValueFactory(factory);
		mySpinner.setEditable(true);
		factory.valueProperty().bindBidirectional(dp);

		return mySpinner;
	}

	private Spinner makeIntegerSpinner(IntegerProperty ip) {
		Spinner mySpinner = new Spinner();
		SpinnerValueFactory factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0);
		mySpinner.setValueFactory(factory);
		mySpinner.setEditable(true);

		factory.valueProperty().bindBidirectional(ip);

		return mySpinner;
	}

	private CheckBox makeBooleanCheckbox(BooleanProperty bp) {
		CheckBox cb = new CheckBox("True/False");
		cb.setIndeterminate(false);

		cb.selectedProperty().bindBidirectional(bp);

		return cb;
	}
	
	private TextField makeTextField(StringProperty sp) {
		TextField textField = new TextField(sp.toString());
		textField.textProperty().bindBidirectional(sp);
		return textField;
	}

}
