package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import authoringEnvironment.SubclassEnumerator;
import gameElements.Sprite;
import javafx.beans.property.*;
import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import resources.ResourcesReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author David Yan, Joe Jacob, Huijia Yu
 */
public class VisualFactory {
	private static final int HBOX_INSET = 10;
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
	// TODO: CAN WE GET WRAPAROUND FOR TABS
	public TabPane getMyTabs(Sprite mySprite) {
		TabPane myTabs = new TabPane();
		myTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);	// cant close tabs
        myTabs.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");

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
        myPane.setStyle("-fx-border-width: 1 1 1 1; -fx-border-color: white transparent transparent transparent ;");
        // System.out.println(f.getGenericType());
		Field[] fChildren = f.getType().getDeclaredFields();
		
		
		// TODO make it work for single properties in Sprite
		
		// this is for things like Lists and Maps
		if (f.getGenericType() instanceof ParameterizedType) {
			HBox myH = new HBox();
			System.out.println("parameterized type " + f.getName());
			ParameterizedType pt = (ParameterizedType) f.getGenericType();
			Type[] params = pt.getActualTypeArguments();
			for (Type p : params) {
				//if(!f.getName().equalsIgnoreCase("myCollisions")) {
					// populate pulldown with all subclasses
					Class<?> clazz = null;
					try {
						clazz = Class.forName(p.getTypeName());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ComboBox<String> subclassBox = makeSubclassComboBox(clazz);
					myBox.getChildren().add(subclassBox);
					System.out.println(p.getTypeName());
					Type boxType = null;
					try {
						System.out.println("boxType " + subclassBox.getValue());
						if (subclassBox.getValue().equalsIgnoreCase("MoveVertically")) {
							boxType = Class.forName("behaviors." + subclassBox.getValue());
						} else {
							boxType = Class.forName(subclassBox.getValue());
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Type boxType = Class.forName(subclassBox.getValue()).
					
					
					VBox fieldVBox = makeOtherPropBoxes(boxType);
					myH.getChildren().add(fieldVBox);
				//}
			}
			// TODO gravity needs to take into account angle
			myBox.getChildren().add(myH);
		} else {
			// populate pulldown with all subclasses
			ComboBox<String> subclassBox = makeSubclassComboBox(f.getType());
			myBox.getChildren().add(subclassBox);
			for (Field p : fChildren) {
				System.out.print(p.getName() + "  ");
				try {
					// o is the actual instance of f in the sprite
					Object o = f.get(mySprite);
					String parentName = f.getName();
					// System.out.println("parent name " + parentName);
					p.setAccessible(true);
					Set<HBox> props = makePropertyBoxes(p, o, parentName, new HashSet<HBox>());
					myBox.getChildren().addAll(props);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println();
		
		
		myPane.getChildren().add(myBox);
        myPane.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");
        myTab.setContent(myPane);
		return myTab;
	}
	
	private ComboBox<String> makeSubclassComboBox(Class<?> clazz) {
		Map<String, Class<?>> allSubclasses = SubclassEnumerator.getAllSubclasses(clazz);
		List<String> toRemove = new ArrayList<String>();
		
		for (String subName : allSubclasses.keySet()) {
			Class<?> sub = allSubclasses.get(subName);
			if (sub.isInterface()) {
				toRemove.add(subName);
				System.out.println(subName);
			}
		}
		
		for (String remove : toRemove) {
			allSubclasses.remove(remove);
		}
		
		ComboBox<String> subclassBox = new ComboBox<String>();
		List<String> allSubKeyset = new ArrayList<String>();
		allSubKeyset.addAll(allSubclasses.keySet());
		subclassBox.getItems().addAll(allSubKeyset);
		if (allSubKeyset.size() > 0) {
			subclassBox.setValue(allSubKeyset.get(0));
		} else {
			subclassBox.setValue(clazz.getName());
		}
		// TODO MAKE A CAMELCASE CONVERTER SO INSTANCE VARIABLES LOOK NICE ON SETTINGS PANE
		
//		System.out.println("canonical " + allSubclasses.get(allSubKeyset.get(0)).getCanonicalName());
//		System.out.println("simple " + allSubclasses.get(allSubKeyset.get(0)).getSimpleName());
//		System.out.println("typename " + allSubclasses.get(allSubKeyset.get(0)).getTypeName());
//		System.out.println("tostring " + allSubclasses.get(allSubKeyset.get(0)).toString());
		
		return subclassBox;
	}
	
	private VBox makeOtherPropBoxes(Type t) {
		Class<?> tClass = null;
		try {
			tClass = Class.forName(t.getTypeName());
		} catch (SecurityException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Object tClassInstance = new Object();

		
		Field[] tFields = tClass.getDeclaredFields();
		//System.out.println(t.getTypeName());
		Set<HBox> myFields = new HashSet<HBox>();
		
		List<String> myProjectClassNames = SubclassEnumerator.getAllReadableClasses();
		
		if (myProjectClassNames.contains(tClass.getName())) {
			for (Field k : tFields) {
				k.setAccessible(true);
				System.out.println(k.getName());
				try {
					// o is the actual instance of f in the sprite
					tClassInstance = Class.forName(t.getTypeName()).newInstance();
					Object o = k.get(tClassInstance);
					String parentName = tClass.getTypeName();
					Set<HBox> props = makePropertyBoxes(k, o, parentName, new HashSet<HBox>());			
					myFields.addAll(props);
				} catch (IllegalArgumentException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println(k.getName());
					e.printStackTrace();
				}
			}
		}
		VBox newV = new VBox();
		newV.getChildren().addAll(myFields);
		
		return newV;
	}
	
	private Set<HBox> makePropertyBoxes(Field p, Object parent, String parentName, Set<HBox> properties) {
		//System.out.println(p.getName());
		if (parent instanceof Property) {
			// the parent is a Property, we can make a settings element
			// System.out.println("pass2323");
			HBox settingsHBox = makeSettingsObject(parent, parentName);
			properties.add(settingsHBox);
			//// System.out.println("GOT TO FIRST THING");
			return properties;
		} else if (parent instanceof List) {
			System.out.println("LIST");
			System.out.println(p.getName());
		} else if (parent instanceof Map) {
			
		} else if (parent instanceof gameElements.Sprite) {
			// results in infinite recursion for collision right now 
			return properties;
		}
		
		// is Field p a Property????
		boolean isProperty = isAProperty(p, parent);
		
		if (isProperty) {
			try {
				//// System.out.println("IS A PROPERTY PASSED");
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
					// System.out.println("wtf just happened");
					e.printStackTrace();
				}
				String pName = otherField.getName();
				properties.addAll(makePropertyBoxes(otherField, o, pName, properties));		
			}
			// System.out.println("THIS DIDNT HAVE ANY FIELDS");
		}
		
		return properties;
	}
	
	private boolean isAProperty(Field p, Object o) {
		// System.out.println(p.getType().getName());
		if (Property.class.isAssignableFrom(p.getType())) {
			// System.out.println("im a porpppp");
			return true;
		}
		return false;
	}
	
	private HBox makeSettingsObject(Object myProp, String propName) {
		HBox propHBox = new HBox();
        propHBox.setPadding(new Insets(20,20,20,20));
		Label propLabelName = new Label(propName);
        propLabelName.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");
        propLabelName.setAlignment(Pos.CENTER);
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
			HBox textHBox = new HBox();
			textHBox.getChildren().addAll(propLabelName, makeTextField(sp));
			HBox.setHgrow(textHBox, Priority.ALWAYS);
			VBox.setVgrow(textHBox, Priority.ALWAYS);
			
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
		VBox.setVgrow(textField, Priority.ALWAYS);
		HBox.setHgrow(textField, Priority.ALWAYS);
		textField.autosize();
		
		return textField;
	}
}
