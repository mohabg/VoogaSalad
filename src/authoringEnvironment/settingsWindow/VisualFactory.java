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
import javafx.scene.layout.Pane;
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

import com.sun.jmx.mbeanserver.ModifiableClassLoaderRepository;

/**
 * @author David Yan, Joe Jacob, Huijia Yu
 */
public class VisualFactory {
	private static final int HBOX_INSET = 10;
	private Settings mySettings;
	private ResourcesReader myReader;
	private final String SCROLL_PANE_CSS = "-fx-border-width: 1 1 1 1; -fx-border-color: white transparent transparent transparent ;";

	public VisualFactory() {
		mySettings = new Settings();
	}


	// TODO: Binding and figuring out list of objects in reflection
	// TODO: CAN WE GET WRAPAROUND FOR TABS
	public TabPane getMyTabs(Sprite mySprite) {
		TabPane myTabs = new TabPane();
		myTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);	// cant close tabs
        myTabs.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");

		Field[] fields = mySprite.getClass().getDeclaredFields();

		for (Field f : fields) {
			f.setAccessible(true);
			myTabs.getTabs().add(getOneTab(f, mySprite));
		}
		//
		return myTabs;
	}
	
	private Tab getOneTab(Field f, Sprite mySprite) {
		String tabName = f.getName();
		Tab myTab = new Tab(tabName);
		
		VBox myBox = new VBox();
		ScrollPane myScrollPane = new ScrollPane();
		myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		
        AnchorPane myAnchorPane = new AnchorPane();
        
		// this is for things like Lists and Maps
        
		if (f.getGenericType() instanceof ParameterizedType) {
			// TODO make instance of the actual type (bind it) i.e. List, Map, etc.
			HBox myH = new HBox();
			ParameterizedType pt = (ParameterizedType) f.getGenericType();
			Type[] params = pt.getActualTypeArguments();
			
			for (Type p : params) {
				VBox myV = makeParamTypeVBox(p, null);
				myH.getChildren().add(myV);
			}
			
			myBox.getChildren().add(myH); 
		} else if (isAProperty(f)) {
			Property fObject = (Property) fieldGetObject(f, mySprite);
			String fObjectName = f.getName();				
			Set<HBox> props = makePropertyBoxes(f, fObject, fObjectName, new HashSet<HBox>());
			myBox.getChildren().addAll(props);
		} else {
			// populate pulldown with all subclasses
			VBox propVBox = makeFieldVBox(f, mySprite, null);
			myBox.getChildren().addAll(propVBox);
		}
		System.out.println();
		
		myAnchorPane.getChildren().add(myBox);
		myScrollPane.setContent(myAnchorPane);
        myScrollPane.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");
        myAnchorPane.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");
        
        myTab.setContent(myScrollPane);
		return myTab;
	}


	private VBox makeFieldVBox(Field f, Object parentObj, ComboBox<String> subclassBox) {
		VBox fieldVBox = new VBox();	
		VBox propVBox = new VBox();
		
		Class<?> clazz = f.getType();
		
		if (subclassBox == null) {
			subclassBox = makeSubclassComboBox(clazz);
		}
		
		fieldVBox.getChildren().add(subclassBox);
		
		
		Field[] fChildren = f.getType().getDeclaredFields();
		for (Field p : fChildren) {
			p.setAccessible(true);
			System.out.print(p.getName() + "  ");
			Object o = fieldGetObject(f, parentObj);
			String parentName = f.getName();					
			Set<HBox> props = makePropertyBoxes(p, o, parentName, new HashSet<HBox>());
			propVBox.getChildren().addAll(props);
		}
		fieldVBox.getChildren().add(propVBox);
		
		return propVBox;
	}

	private VBox makeParamTypeVBox(Type p, ComboBox<String> subclassBox) {
		VBox myV = new VBox();
		// populate pulldown with all subclasses
		
		Class<?> clazz = getClass(p.getTypeName());
		
		if (subclassBox == null) {
			subclassBox = makeSubclassComboBox(clazz);
		}
		
		myV.getChildren().add(subclassBox);
		
		System.out.println(p.getTypeName());
		
		// populate panel with combobox value's instance vars
		Type boxType = getClass(subclassBox.getValue());

		VBox fieldVBox = makeParameterPropBoxes(boxType);
		myV.getChildren().add(fieldVBox);
		return myV;
	}
	
	private ComboBox<String> makeSubclassComboBox(Class<?> clazz) {
		Map<String, Class<?>> allSubclasses = SubclassEnumerator.getAllSubclasses(clazz);
		List<String> toRemove = new ArrayList<String>();
		
		// remove interfaces because they dont have instance vars
		for (String subName : allSubclasses.keySet()) {
			Class<?> sub = allSubclasses.get(subName);
			if (sub.isInterface() || Modifier.isAbstract(sub.getModifiers())) {
				toRemove.add(subName);
			}
		}
		
		// in separate for loop to avoid concurrency issues
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
		
		subclassBox.setOnAction(event -> {
			// change the corresponding prop boxes
			// should work for param
			Pane myComboBoxParent = (Pane) subclassBox.getParent();
			Pane myGrandParent = (Pane) myComboBoxParent.getParent();
			myGrandParent.getChildren().remove(myComboBoxParent);
			String newClassName = subclassBox.getValue();
			Class<?> newClass = getClass(newClassName);
			myGrandParent.getChildren().add(makeParamTypeVBox(newClass, subclassBox));
		});
		
		return subclassBox;
	}
	
	private VBox makeParameterPropBoxes(Type t) {
		Class<?> tClass= getClass(t.getTypeName());
	
		Set<HBox> myFields = new HashSet<HBox>();
		
		List<String> myProjectClassNames = SubclassEnumerator.getAllReadableClasses();		
		
		Set<Field> testtFields = getAllFields(new HashSet<Field>(), tClass, myProjectClassNames);
		
		if (myProjectClassNames.contains(tClass.getName())) {
			Object tClassInstance = null;
			try {
				tClassInstance = tClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			for (Field k : testtFields) {
				k.setAccessible(true);
				System.out.println(k.getName());
				System.out.println("poooooo " + tClass.getName());
				Object o = fieldGetObject(k, tClassInstance);
				
				String parentName = tClass.getTypeName();
				Set<HBox> props = makePropertyBoxes(k, o, parentName, new HashSet<HBox>());			
				myFields.addAll(props);
			}
		}
		
		VBox newV = new VBox();
		newV.getChildren().addAll(myFields);
		
		return newV;
	}
	
	
	private Set<HBox> makePropertyBoxes(Field p, Object parent, String parentName, Set<HBox> properties) {
		if (parent instanceof Property) {
			// the parent is a Property, we can make a settings element
			HBox settingsHBox = makeSettingsObject(parent, p.getName());
			properties.add(settingsHBox);
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
		boolean isProperty = isAProperty(p);
		
		if (isProperty) {
			Property pObject = (Property) fieldGetObject(p, parent);
			String pObjectName = p.getName();				
			properties.addAll(makePropertyBoxes(p, pObject, pObjectName, properties));
		} else {
			List<String> myProjectClassNames = SubclassEnumerator.getAllReadableClasses();
	
			// prevents us from trying to initialize java classes
			if (myProjectClassNames.contains(p.getType().getName())) {
				Set<Field> allFields = getAllFields(new HashSet<Field>(), p.getType(), myProjectClassNames);
				// parent is probably an abstract class and therefore 
				
				// impossible to make an instance
				if (parent == null) {
					parent = getSubclass(p.getType());
				}
				
				for (Field otherField : allFields) {
					otherField.setAccessible(true);
					Object o = fieldGetObject(otherField, parent);
					System.out.println(p.getGenericType() + " "  + otherField.getGenericType());
		
					String pName = otherField.getName();
					properties.addAll(makePropertyBoxes(otherField, o, pName, properties));		
				}
			}
		}
		return properties;
	}
	
	private Set<Field> getAllFields(Set<Field> fields, Class<?> type, List<String> allProjectClasses) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		if (type.getSuperclass() != null && allProjectClasses.contains(type.getSuperclass().getTypeName())) {
			fields = getAllFields(fields, type.getSuperclass(), allProjectClasses);
		}

		return fields;
	}
	
	private Class<?> getClass(String className) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clazz;
	}
	
	private Object getSubclass(Class<?> clazz) {
		// find an available subclass otherwise print an exception
		Map<String, Class<?>> parentSubclasses = SubclassEnumerator.getAllSubclasses(clazz);
		// make sure the picked class isn't abstract
		for (Class<?> sub : parentSubclasses.values()) {
			if (!Modifier.isAbstract(sub.getModifiers())) {
				try {
					return sub.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO throw exception saying that there are no abstract subclasses
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	private Class<?> getSubclassWithoutInstance(Class<?> clazz) {
		// find an available subclass otherwise print an exception
				Map<String, Class<?>> parentSubclasses = SubclassEnumerator.getAllSubclasses(clazz);
				// make sure the picked class isn't abstract
				for (Class<?> sub : parentSubclasses.values()) {
					if (!Modifier.isAbstract(sub.getModifiers())) {
						return sub;
					}
				}
				
				return null;
	}
	
	private Object fieldGetObject(Field childField, Object parentObject) {
		Object o = null;
		try {
			o = childField.get(parentObject);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	private boolean isAProperty(Field p) {
		return Property.class.isAssignableFrom(p.getType());
	}
	
	private HBox makeSettingsObject(Object myProp, String propName) {
		HBox propHBox = new HBox();
        propHBox.setPadding(new Insets(20,20,20,20));
        String labelText = convertCamelCase(propName);
		Label propLabelName = new Label(labelText);
		
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

	private String convertCamelCase(String camelCaseString) {
		String[] words = camelCaseString.split("(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])");
		String converted = "";
		
		for (String word : words) {
			String convertedWord = Character.toUpperCase(word.charAt(0)) + word.substring(1);
			converted = converted + convertedWord + " ";
		}
		
		return converted;
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
