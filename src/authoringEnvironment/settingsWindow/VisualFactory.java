package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import authoringEnvironment.SubclassEnumerator;
import gameElements.Sprite;
import gameplayer.ButtonFactory;
import behaviors.*;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import javafx.util.StringConverter;
import resources.ResourcesReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import com.sun.jmx.mbeanserver.ModifiableClassLoaderRepository;


/**
 * @author David Yan, Joe Jacob, Huijia Yu
 */
public class VisualFactory {
	private static final int HBOX_INSET = 10;
	private Settings mySettings;
	private ResourcesReader myReader;
	private final String SCROLL_PANE_CSS = "-fx-border-width: 1 1 1 1; -fx-border-color: white transparent transparent transparent ;";
	private List<String> myProjectClassNames;
	
	public VisualFactory() {
		mySettings = new Settings();
		myProjectClassNames = SubclassEnumerator.getAllReadableClasses();
	}

	// TODO: Binding and figuring out list of objects in reflection
	// TODO: CAN WE GET WRAPAROUND FOR TABS
	public TabPane getMyTabs(Object mySprite) {
		TabPane myTabs = new TabPane();
		myTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);	// cant close tabs
        myTabs.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");

		Field[] fields = mySprite.getClass().getDeclaredFields();

		for (Field f : fields) {
			if(!f.getName().equalsIgnoreCase("myCollisionsNoob") && !f.getName().equalsIgnoreCase("myBehaviorsNoob") && !f.getName().equalsIgnoreCase("userPressBehaviorsNoob") && !f.getName().equalsIgnoreCase("userReleaseBehaviorsNoob")) {
				f.setAccessible(true);
				myTabs.getTabs().add(getOneTab(f, mySprite));
			}
		}
		//
		return myTabs;
	}

	private Tab getOneTab(Field f, Object mySprite) {
		String tabName = f.getName();
		Tab myTab = new Tab(tabName);


		VBox myBox = new VBox();
		ScrollPane myScrollPane = new ScrollPane();
		myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        AnchorPane myAnchorPane = new AnchorPane();

		myBox = makePropBoxForFieldTab(f, mySprite);
		System.out.println();


		// TODO: might want to move this very up
		myAnchorPane.getChildren().add(myBox);
		myScrollPane.setContent(myAnchorPane);
        myScrollPane.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");
        myAnchorPane.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");

        myTab.setContent(myScrollPane);
		return myTab;
	}

	private VBox makePropBoxForFieldTab(Field f, Object mySprite) {
		VBox myBox = new VBox();
		// this is for things like Lists and Maps
		if (f.getGenericType() instanceof ParameterizedType) {
			HBox myH = new HBox();
			ParameterizedType pt = (ParameterizedType) f.getGenericType();
			
			Type[] params = pt.getActualTypeArguments();
			
			// handle parameterized property object types
			System.out.println(pt.getRawType().getTypeName());
			Class<?> rawTypeClass = getClass(pt.getRawType().getTypeName());
			if (Property.class.isAssignableFrom(rawTypeClass)) {
				Property ptProperty = (Property) fieldGetObject(f, mySprite);
				if(params.length == 1) {
					// single param catch (most likely List)
					Class<?> paramClass0 = getClass(params[0].getTypeName());
					myH.getChildren().add(singleParamType(paramClass0, ptProperty));
				} else if (params.length == 2) {
					// double param catch (most likely Map)
					Class<?> paramClass0 = getClass(params[0].getTypeName());
					Class<?> paramClass1 = getClass(params[1].getTypeName());
					doubleParamType(paramClass0, paramClass1, ptProperty);
				}
				
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
		
		return myBox;
	}


	private <R> VBox singleParamType(Class<R> rType, Object prop) {
		VBox singleParamVBox = new VBox();
		
		if(prop instanceof ListProperty) {
			ListProperty<R> lpr = (ListProperty<R>) prop;
			//ComboBox<SimpleEntry<Class<R>, R>> mySubclassBox = new ComboBox<SimpleEntry<Class<R>, R>>();
			
		
			Set<HBox> paramProps = new HashSet<HBox>();
			
			// display any items already in the list
			for (R rListElement : lpr) {
				VBox elementBox = new VBox();
				
				final ComboBox<SimpleEntry<Class<R>, R>> mySubclassBox = makeSubclassComboBoxTest(rType);
				
				ChangeListener<SimpleEntry<Class<R>, R>> boxChangeListener = (o, ov, nv) -> {
					Pane myComboBoxParent = (Pane) mySubclassBox.getParent();

					myComboBoxParent.getChildren().clear();
					
					// switch corresponding instances	
					Class<R> newClassType = nv.getKey();
					
					if (nv.getValue() == null) {		
						nv.setValue((R) newClassInstance(newClassType));
						o.getValue().setValue(nv.getValue());
					}
					
					if (ov != null) {
						System.out.println("HIIII");
						lpr.remove(ov.getValue());
						lpr.add(o.getValue().getValue());
					}
					
					
					// populate combobox parent with new params
					myComboBoxParent.getChildren().setAll(mySubclassBox);
					myComboBoxParent.getChildren().addAll(makeBoxesAndBindFields(o.getValue().getValue(), newClassType));			
				};
				
				mySubclassBox.valueProperty().addListener(boxChangeListener);
				
				elementBox.getChildren().add(mySubclassBox);
				
				List<SimpleEntry<Class<R>, R>> boxItems = mySubclassBox.getItems();
				SimpleEntry<Class<R>, R> rBoxItem = null;
				
				for (SimpleEntry<Class<R>, R> item : boxItems) {
					if (item.getKey().equals(rListElement.getClass())) {
						rBoxItem = item;
						rBoxItem.setValue(rListElement);
					}
				}
				
				mySubclassBox.setValue(rBoxItem);
				
				
				singleParamVBox.getChildren().add(elementBox);
				paramProps.clear();
				
			}
			
			// check GOT TO REFACTOR SINGLEPROP
			// IMPLEMENT MULTIPLE PROP
			// FIX SUBCLASSBOX IMPLEMENTATION (FOR A SINGLE INSTANCE VAR, ONLY SHOW THE INSTANCE
				// VARS' CONCRETE CLASS (IF IT IS) AND ALL CONCRETE SUBCLASSES) SO THAT IT DOESNT
				// CHANGE EVERY TIME A NEW COMBOBOX VALUE IS CHOSEN
			
			// MAYBE ALLOW PEOPLE TO REMOVE STUFF
				// THIS WILL REQUIRE ME ACTUALLY PUTTING MAP/LIST IN THEIR OWN GUI OBJECT
			// MAKE LISTPROPERTY AND MAPPROPERTY CONVERTERS FOR XSTREAM
			// MAYBE REFACTOR THE REST OF THE CLASS 
			// ORDER INSTANCE VARS INTELLIGIBLY AND HAVE A PULLDOWN MENU FOR EACH INSTANCE VAR'S 
				// CONRETE SUBCLASSES (IF APPLICABLE) 
			
			Button addButton = ButtonFactory.makeButton("Add", e -> {
				singleParamVBox.getChildren().add(singleParamVBox.getChildren().size()-1, addSingleParameter(rType, lpr));
			});
			
			singleParamVBox.getChildren().add(addButton);
			
		}
		
		return singleParamVBox;
	}

	private <R> VBox addSingleParameter(Class<R> rType, ListProperty<R> lpr) {
		VBox retVBox = new VBox();
		R rObj = null;
		
		ComboBox<SimpleEntry<Class<R>, R>> mySubclassBox = makeSubclassComboBoxTest(rType);
		retVBox.getChildren().add(mySubclassBox);
		
		ChangeListener<SimpleEntry<Class<R>, R>> boxChangeListener = (o, ov, nv) -> {
			Pane myComboBoxParent = (Pane) mySubclassBox.getParent();

			myComboBoxParent.getChildren().clear();
			
			// switch corresponding instances	
			Class<R> newClassType = nv.getKey();
			
			if (nv.getValue() == null) {		
				nv.setValue((R) newClassInstance(newClassType));
				o.getValue().setValue(nv.getValue());
			}
			
			if (ov != null) {
				lpr.remove(ov.getValue());
			}
			
			lpr.add(o.getValue().getValue());
			
			// populate combobox parent with new params
			myComboBoxParent.getChildren().setAll(mySubclassBox);
			myComboBoxParent.getChildren().addAll(makeBoxesAndBindFields(o.getValue().getValue(), newClassType));			
		};
		
		mySubclassBox.getSelectionModel().selectedItemProperty().addListener(boxChangeListener);
		
		// get a proper subclass of R if necessary
		if (rType.isInterface() || Modifier.isAbstract(rType.getModifiers())) {
			rType = (Class<R>) getSubclassWithoutInstance(rType);	
		}
		
		rObj = (R) newClassInstance(rType);
		
		List<SimpleEntry<Class<R>, R>> boxItems = mySubclassBox.getItems();
		SimpleEntry<Class<R>, R> rBoxItem = null;	
		for (SimpleEntry<Class<R>, R> item : boxItems) {
			if (item.getKey().equals(rType)) {
				rBoxItem = item;
				rBoxItem.setValue(rObj);
				break;
			}
		}
		mySubclassBox.setValue(rBoxItem);
		
		//lpr.add(rObj);

		
		return retVBox;
	}

	private <R> Set<HBox> makeBoxesAndBindFields(R rObj, Class<R> rType) {
		Set<HBox> paramProps = new HashSet<HBox>();
		Set<Field> rAllFields = getAllFields(new HashSet<Field>(), rType, myProjectClassNames);
		System.out.println(rType.getName());
		for (Field rField : rAllFields) {			
				rField.setAccessible(true);
				System.out.println("FIELD NAME " + rField.getType().getName());
				Object rFieldObj = fieldGetObject(rField, rObj);	
				String rFieldObjName = rField.getName();
				System.out.println(rField.getType().getName());
				
				paramProps.addAll(makePropertyBoxes(rField, rFieldObj, rFieldObjName, new HashSet<HBox>()));
			
		}
		return paramProps;
	}
	
	
	private <R, T> void doubleParamType(Class<R> rType, Class<T> tType, Property prop) {
		if(prop instanceof MapProperty) {
			MapProperty<R,T> mprt = (MapProperty<R,T>) prop;
//			try {
//				mprt.put(rType.newInstance(), tType.newInstance());
//				System.out.println("MAP WORKED");
//			} catch (InstantiationException | IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
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

	
	
	



	private <R> ComboBox<String> makeSubclassComboBox(Class<R> clazz) {
		Map<String, Class<R>> allSubclasses = SubclassEnumerator.getAllSubclasses(clazz);
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

		
		if (!isAbstractOrInterface(clazz) || allSubKeyset.size() == 0) {
			subclassBox.setValue(clazz.getName());
		} else if (allSubKeyset.size() > 0) {
			subclassBox.setValue(allSubKeyset.get(0));
		} 

		subclassBox.setOnAction(event -> {
			// change the corresponding prop boxes
			// should work for param
			Pane myComboBoxParent = (Pane) subclassBox.getParent();
			myComboBoxParent.getChildren().remove(1, myComboBoxParent.getChildren().size());
			
			String newClassName = subclassBox.getValue();
			Class<?> newClass = getClass(newClassName);
			
			
			
		});

		return subclassBox;
	}

	private <R> ComboBox<SimpleEntry<Class<R>, R>> makeSubclassComboBoxTest(Class<R> clazz) {
		Map<String, Class<R>> allSubclasses = SubclassEnumerator.getAllSubclasses(clazz);
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

		ComboBox<SimpleEntry<Class<R>, R>> subclassBox = new ComboBox<SimpleEntry<Class<R>, R>>();
		
		List<SimpleEntry<Class<R>, R>> allSubKeyset = new ArrayList<SimpleEntry<Class<R>, R>>();
		for (Class<R> rClass : allSubclasses.values()) {
			SimpleEntry<Class<R>, R> classPair = new SimpleEntry<Class<R>, R>(rClass, null);
			allSubKeyset.add(classPair);
		}
		
		subclassBox.getItems().addAll(allSubKeyset);

		
		StringConverter<SimpleEntry<Class<R>, R>> comboBoxConverter = new StringConverter<SimpleEntry<Class<R>, R>>() {
			@Override
			public String toString(SimpleEntry<Class<R>, R> object) {
				Class<R> clazz = object.getKey();
				return clazz.getSimpleName();
			}

			@Override
			public SimpleEntry<Class<R>, R> fromString(String string) {
				// not editable
				return null;
			}			
		};	
		subclassBox.setConverter(comboBoxConverter);
		

		return subclassBox;
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
					//System.out.println(p.getGenericType() + " "  + otherField.getGenericType());
		

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
	
	private Object newClassInstance(Class<?> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
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

	private <R> Object getSubclass(Class<R> clazz) {
		// find an available subclass otherwise print an exception
		Map<String, Class<R>> parentSubclasses = SubclassEnumerator.getAllSubclasses(clazz);
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

	private <R> Class<R> getSubclassWithoutInstance(Class<R> clazz) {
		// find an available subclass otherwise print an exception
		Map<String, Class<R>> parentSubclasses = SubclassEnumerator.getAllSubclasses(clazz);
		// make sure the picked class isn't abstract
		for (Class<R> sub : parentSubclasses.values()) {
			if (!sub.isInterface() && !Modifier.isAbstract(sub.getModifiers())) {
				return sub;
			}
		}

		return null;
	}

	private boolean isAbstractOrInterface(Class<?> clazz) {
		return clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers());
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
			propHBox.getChildren().addAll(propLabelName, makeTextField(sp));
		} else if (myProp instanceof ListProperty) {
			ListProperty lp = (ListProperty) myProp;
			propHBox.getChildren().addAll(propLabelName, makeTableView(lp));
		} else if (myProp instanceof MapProperty) {
			MapProperty mp = (MapProperty) myProp;
			//propHBox.getChildren().addAll(propLabelName, makeTableView(mp));
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
		SpinnerValueFactory factory = new SpinnerValueFactory.DoubleSpinnerValueFactory(-10000, 10000, 0);
		mySpinner.setValueFactory(factory);
		mySpinner.setEditable(true);
		
		TextFormatter formatter = new TextFormatter(factory.getConverter(), factory.getValue());
		mySpinner.getEditor().setTextFormatter(formatter);
		
		factory.valueProperty().bindBidirectional(dp);
		factory.valueProperty().bindBidirectional(formatter.valueProperty());
		
		return mySpinner;
	}

	private Spinner makeIntegerSpinner(IntegerProperty ip) {
		Spinner mySpinner = new Spinner();
		SpinnerValueFactory factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-10000, 10000, 0);
		mySpinner.setValueFactory(factory);
		mySpinner.setEditable(true);

		TextFormatter formatter = new TextFormatter(factory.getConverter(), factory.getValue());
		mySpinner.getEditor().setTextFormatter(formatter);
		
		factory.valueProperty().bindBidirectional(ip);
		factory.valueProperty().bindBidirectional(formatter.valueProperty());
		
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
		
		TextFormatter formatter = new TextFormatter(TextFormatter.IDENTITY_STRING_CONVERTER);
		textField.setTextFormatter(formatter);
		
		textField.textProperty().bindBidirectional(sp);
		textField.textProperty().bindBidirectional(formatter.valueProperty());
		
		VBox.setVgrow(textField, Priority.ALWAYS);
		HBox.setHgrow(textField, Priority.ALWAYS);
		textField.autosize();

		return textField;
	}
	
	private TableView makeTableView(ListProperty lp) {
		// TODO ALLOW US TO ADD AND REMOVE COLLISIONS
		TableView tv = new TableView<>(lp);
		tv.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");
		
		TableColumn myCol = new TableColumn();
		myCol.setSortable(false);
		tv.getColumns().setAll(myCol);
		tv.itemsProperty().bindBidirectional(lp);
		
		tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		return tv;
	}
	
	private TableView makeTableView(MapProperty mp) {
		// TODO ALLOW US TO ADD AND REMOVE COLLISIONS
		ObservableSet os = FXCollections.observableSet(mp.entrySet());
		SetProperty sp = new SimpleSetProperty(os);
		
		TableView tv = new TableView();
		TableColumn myCol1 = new TableColumn();
		myCol1.setSortable(false);
		TableColumn myCol2 = new TableColumn();
		myCol2.setSortable(false);

		tv.getColumns().setAll(myCol1, myCol2);
		tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return tv;
	}
}
