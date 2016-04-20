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
		//	if(!f.getName().equalsIgnoreCase("myCollisionsNoob") && !f.getName().equalsIgnoreCase("myBehaviorsNoob") && !f.getName().equalsIgnoreCase("userPressBehaviorsNoob") && !f.getName().equalsIgnoreCase("userReleaseBehaviorsNoob")) {
				f.setAccessible(true);
				myTabs.getTabs().add(getOneTab(f, mySprite));
			//}
		}
		//
		return myTabs;
	}


	private Tab getOneTab(Field f, Object mySprite) {
		String tabName = f.getName();
		Tab myTab = new Tab(tabName);
		VBox myBox = new VBox();
		ScrollPane myScrollPane = new ScrollPane();
		AnchorPane myAnchorPane = new AnchorPane();
		 
		myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		myScrollPane.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");
	    myAnchorPane.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");

	    myBox = makePropBoxForFieldTab(f, mySprite);
	    
        myAnchorPane.getChildren().add(myBox);
		myScrollPane.setContent(myAnchorPane);
       
        
		

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
			//system.out.println(pt.getRawType().getTypeName());
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
					myH.getChildren().add(doubleParamType(paramClass0, paramClass1, ptProperty));
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


	private <R> ChangeListener<SimpleEntry<Class<R>, R>> makeChangeCBListListener(ListProperty lpr) {
		ChangeListener<SimpleEntry<Class<R>, R>> boxChangeListener = (o, ov, nv) -> {
			ObjectProperty<SimpleEntry<Class<R>, R>> objProp = (ObjectProperty<SimpleEntry<Class<R>, R>>) o;
			ComboBox subclassBox = (ComboBox) objProp.getBean();
			Pane myComboBoxParent = (Pane) subclassBox.getParent();

			myComboBoxParent.getChildren().clear();
			
			// switch corresponding instances	
			Class<R> newClassType = nv.getKey();
			
			if (nv.getValue() == null) {		
				nv.setValue((R) newClassInstance(newClassType));
				o.getValue().setValue(nv.getValue());
			}
			
			if (ov != null) {
				lpr.remove(ov.getValue());
				lpr.add(o.getValue().getValue());
			}		
					
			// populate combobox parent with new params
			myComboBoxParent.getChildren().setAll(subclassBox);
			myComboBoxParent.getChildren().addAll(makeBoxesAndBindFields(o.getValue().getValue(), newClassType));			
		};
		
		return boxChangeListener;
	}
	
	private <R> VBox singleParamType(Class<R> rType, Object prop) {
		VBox singleParamVBox = new VBox();
		
		if(prop instanceof ListProperty) {
			ListProperty<R> lpr = (ListProperty<R>) prop;

			// display any items already in the list
			for (R rListElement : lpr) {
				VBox elementBox = new VBox();
				
				ComboBox<SimpleEntry<Class<R>, R>> mySubclassBox = makeSubclassComboBox(rType);
				ChangeListener<SimpleEntry<Class<R>, R>> boxChangeListener = makeChangeCBListListener(lpr);
				mySubclassBox.valueProperty().addListener(boxChangeListener);
				elementBox.getChildren().add(mySubclassBox);
				
				
				updateComboBoxValue((Class<R>) rListElement.getClass(), rListElement, mySubclassBox);
							
				singleParamVBox.getChildren().add(elementBox);			
			}
			
			
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
		
		ComboBox<SimpleEntry<Class<R>, R>> mySubclassBox = makeSubclassComboBox(rType);
		retVBox.getChildren().add(mySubclassBox);
		
		ChangeListener<SimpleEntry<Class<R>, R>> boxChangeListener = makeChangeCBListListener(lpr);
		
		mySubclassBox.valueProperty().addListener(boxChangeListener);
		
		// get a proper subclass of R if necessary
		if (rType.isInterface() || Modifier.isAbstract(rType.getModifiers())) {
			rType = (Class<R>) getSubclassWithoutInstance(rType);	
		}
		
		rObj = (R) newClassInstance(rType);		
		updateComboBoxValue(rType, rObj, mySubclassBox);
		lpr.add(rObj);	// listener won't add it to the list when it's first added

		
		return retVBox;
	}

	private <R> void updateComboBoxValue(Class<R> rType, R rObj, ComboBox<SimpleEntry<Class<R>, R>> mySubclassBox) {
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
	}

	private <R> Set<HBox> makeBoxesAndBindFields(R rObj, Class<R> rType) {
		Set<HBox> paramProps = new HashSet<HBox>();
		Set<Field> rAllFields = getAllFields(new HashSet<Field>(), rType);
		
		for (Field rField : rAllFields) {			
				rField.setAccessible(true);
				Object rFieldObj = fieldGetObject(rField, rObj);	
				String rFieldObjName = rField.getName();
				
				paramProps.addAll(makePropertyBoxes(rField, rFieldObj, rFieldObjName, new HashSet<HBox>()));			
		}
		
		return paramProps;
	}
	
	
	
	
	private <R, T> VBox doubleParamType(Class<R> rType, Class<T> tType, Property prop) {
		VBox doubleParamVBox = new VBox();
		
		if(prop instanceof MapProperty) {
			MapProperty<R,T> mprt = (MapProperty<R,T>) prop;
//			
			// display any items already in the map
			for (R rListElement : mprt.keySet()) {
				VBox elementBoxKey = new VBox();
				T tListElement = mprt.get(rListElement);
				
				final ComboBox<SimpleEntry<Class<R>, R>> mySubclassBoxKey = makeSubclassComboBox(rType);
				ChangeListener<SimpleEntry<Class<R>, R>> boxChangeListenerKey = (o, ov, nv) -> {
					Pane myComboBoxParent = (Pane) mySubclassBoxKey.getParent();

					myComboBoxParent.getChildren().clear();
					
					// switch corresponding instances	
					Class<R> newClassType = nv.getKey();
					
					if (nv.getValue() == null) {		
						nv.setValue((R) newClassInstance(newClassType));
						o.getValue().setValue(nv.getValue());
					}
					
					if (ov != null) {
						T val = mprt.get(ov.getValue());
						mprt.remove(ov.getValue());
						mprt.put(o.getValue().getValue(), val);
					}			
					
					// populate combobox parent with new params
					myComboBoxParent.getChildren().setAll(mySubclassBoxKey);
					myComboBoxParent.getChildren().addAll(makeBoxesAndBindFields(o.getValue().getValue(), newClassType));			
				};	
				mySubclassBoxKey.valueProperty().addListener(boxChangeListenerKey);				
				elementBoxKey.getChildren().add(mySubclassBoxKey);
				
				List<SimpleEntry<Class<R>, R>> boxItemsKey = mySubclassBoxKey.getItems();
				SimpleEntry<Class<R>, R> rBoxItemKey = null;				
				for (SimpleEntry<Class<R>, R> item : boxItemsKey) {
					if (item.getKey().equals(rListElement.getClass())) {
						rBoxItemKey = item;
						rBoxItemKey.setValue(rListElement);
					}
				}		
				mySubclassBoxKey.setValue(rBoxItemKey);
				
				
				VBox elementBoxValue = new VBox();
				final ComboBox<SimpleEntry<Class<T>, T>> mySubclassBoxValue = makeSubclassComboBox(tType);
				ChangeListener<SimpleEntry<Class<T>, T>> boxChangeListenerValue = (o, ov, nv) -> {
					Pane myComboBoxParent = (Pane) mySubclassBoxValue.getParent();

					myComboBoxParent.getChildren().clear();
					
					// switch corresponding instances	
					Class<T> newClassType = nv.getKey();
					
					if (nv.getValue() == null) {		
						nv.setValue((T) newClassInstance(newClassType));
						o.getValue().setValue(nv.getValue());
					}
					
					if (ov != null) {
						Pane myComboBoxGrandParent = (Pane) myComboBoxParent.getParent();
						ComboBox<SimpleEntry<Class<R>, R>> mySubclassBoxKeyCopy = (ComboBox<SimpleEntry<Class<R>, R>>) myComboBoxGrandParent.getChildren().get(0);
						R key = mySubclassBoxKeyCopy.getValue().getValue();
						mprt.put(key, o.getValue().getValue());		
					}			
					
					// populate combobox parent with new params
					myComboBoxParent.getChildren().setAll(mySubclassBoxValue);
					myComboBoxParent.getChildren().addAll(makeBoxesAndBindFields(o.getValue().getValue(), newClassType));			
				};	
				mySubclassBoxValue.valueProperty().addListener(boxChangeListenerValue);				
				elementBoxValue.getChildren().add(mySubclassBoxValue);
				
				List<SimpleEntry<Class<T>, T>> boxItemsValue = mySubclassBoxValue.getItems();
				SimpleEntry<Class<T>, T> tBoxItemValue = null;				
				for (SimpleEntry<Class<T>, T> item : boxItemsValue) {
					if (item.getKey().equals(tListElement.getClass())) {
						tBoxItemValue = item;
						tBoxItemValue.setValue(tListElement);
					}
				}		
				mySubclassBoxValue.setValue(tBoxItemValue);
				
				HBox keyval = new HBox(elementBoxKey, elementBoxValue);
				doubleParamVBox.getChildren().add(keyval);
			}
			
			Button addButton = ButtonFactory.makeButton("Add", e -> {
				System.out.println("RTYPE " + rType.getName());
				doubleParamVBox.getChildren().add(doubleParamVBox.getChildren().size()-1, addDoubleParameter(rType, tType, mprt));
			});
			
			doubleParamVBox.getChildren().add(addButton);
		}
		
		return doubleParamVBox;
	}
	
	private <R,T> HBox addDoubleParameter(Class<R> rType, Class<T> tType, MapProperty<R,T> mprt) {
		HBox retHBox = new HBox();
		R rListElement = null;
		T tListElement = null;

				
		if (rType == null) {
			System.out.println(" RRRRRR NULLL");
		}
		
		if (rType == null) {
			System.out.println("TTTTTT NULLL");
		}
		
		VBox elementBoxKey = new VBox();
		
		final ComboBox<SimpleEntry<Class<R>, R>> mySubclassBoxKey = makeSubclassComboBox(rType);
		ChangeListener<SimpleEntry<Class<R>, R>> boxChangeListenerKey = (o, ov, nv) -> {
			Pane myComboBoxParent = (Pane) mySubclassBoxKey.getParent();

			myComboBoxParent.getChildren().clear();
			
			// switch corresponding instances	
			Class<R> newClassType = nv.getKey();
			
			if (nv.getValue() == null) {		
				nv.setValue((R) newClassInstance(newClassType));
				o.getValue().setValue(nv.getValue());
			}
			
			if (ov != null) {
				T val = mprt.get(ov.getValue());
				mprt.remove(ov.getValue());
				mprt.put(o.getValue().getValue(), val);
			}			
			
			// populate combobox parent with new params
			myComboBoxParent.getChildren().setAll(mySubclassBoxKey);
			myComboBoxParent.getChildren().addAll(makeBoxesAndBindFields(o.getValue().getValue(), newClassType));			
		};	
		mySubclassBoxKey.getSelectionModel().selectedItemProperty().addListener(boxChangeListenerKey);				
		elementBoxKey.getChildren().add(mySubclassBoxKey);
		
		// get a proper subclass of R if necessary
				if (rType.isInterface() || Modifier.isAbstract(rType.getModifiers())) {
					rType = (Class<R>) getSubclassWithoutInstance(rType);	
				}
				rListElement = (R) newClassInstance(rType);
		
		List<SimpleEntry<Class<R>, R>> boxItemsKey = mySubclassBoxKey.getItems();
		SimpleEntry<Class<R>, R> rBoxItemKey = null;				
		for (SimpleEntry<Class<R>, R> item : boxItemsKey) {
			if (item.getKey().equals(rListElement.getClass())) {
				rBoxItemKey = item;
				rBoxItemKey.setValue(rListElement);
			}
		}		
		mySubclassBoxKey.setValue(rBoxItemKey);
		
		
		VBox elementBoxValue = new VBox();
		final ComboBox<SimpleEntry<Class<T>, T>> mySubclassBoxValue = makeSubclassComboBox(tType);
		ChangeListener<SimpleEntry<Class<T>, T>> boxChangeListenerValue = (o, ov, nv) -> {
			Pane myComboBoxParent = (Pane) mySubclassBoxValue.getParent();

			myComboBoxParent.getChildren().clear();
			
			// switch corresponding instances	
			Class<T> newClassType = nv.getKey();
			
			if (nv.getValue() == null) {		
				nv.setValue((T) newClassInstance(newClassType));
				o.getValue().setValue(nv.getValue());
			}
			
			if (ov != null) {
				Pane myComboBoxGrandParent = (Pane) myComboBoxParent.getParent();
				ComboBox<SimpleEntry<Class<R>, R>> mySubclassBoxKeyCopy = (ComboBox<SimpleEntry<Class<R>, R>>) myComboBoxGrandParent.getChildren().get(0);
				R key = mySubclassBoxKeyCopy.getValue().getValue();
				mprt.put(key, o.getValue().getValue());		
			}			
			
			// populate combobox parent with new params
			myComboBoxParent.getChildren().setAll(mySubclassBoxValue);
			myComboBoxParent.getChildren().addAll(makeBoxesAndBindFields(o.getValue().getValue(), newClassType));			
		};	
		mySubclassBoxValue.getSelectionModel().selectedItemProperty().addListener(boxChangeListenerValue);				
		elementBoxValue.getChildren().add(mySubclassBoxValue);
		
		// get a proper subclass of T if necessary
		if (tType.isInterface() || Modifier.isAbstract(tType.getModifiers())) {
			tType = (Class<T>) getSubclassWithoutInstance(tType);	
		}
		tListElement = (T) newClassInstance(tType);
		
		List<SimpleEntry<Class<T>, T>> boxItemsValue = mySubclassBoxValue.getItems();
		SimpleEntry<Class<T>, T> tBoxItemValue = null;				
		for (SimpleEntry<Class<T>, T> item : boxItemsValue) {
			if (item.getKey().equals(tListElement.getClass())) {
				tBoxItemValue = item;
				tBoxItemValue.setValue(tListElement);
			}
		}		
		mySubclassBoxValue.setValue(tBoxItemValue);
		
		return retHBox;
	}


	private <R> VBox makeFieldVBox(Field f, Object parentObj, ComboBox<SimpleEntry<Class<R>, R>> subclassBox) {

		VBox fieldVBox = new VBox();
		VBox propVBox = new VBox();

		Class<R> clazz = (Class<R>) f.getType();

		if (subclassBox == null) {
			subclassBox = makeSubclassComboBox(clazz);
		}

		fieldVBox.getChildren().add(subclassBox);


		Field[] fChildren = f.getType().getDeclaredFields();
		for (Field p : fChildren) {
			p.setAccessible(true);
			//System.out.print(p.getName() + "  ");
			Object o = fieldGetObject(f, parentObj);
			String parentName = f.getName();
			Set<HBox> props = makePropertyBoxes(p, o, parentName, new HashSet<HBox>());
			propVBox.getChildren().addAll(props);
		}
		fieldVBox.getChildren().add(propVBox);

		return propVBox;
	}


	private <R> ComboBox<SimpleEntry<Class<R>, R>> makeSubclassComboBox(Class<R> clazz) {
		ComboBox<SimpleEntry<Class<R>, R>> subclassBox = new ComboBox<SimpleEntry<Class<R>, R>>();
		
		Map<String, Class<R>> allSubclasses = SubclassEnumerator.getAllSubclasses(clazz);
		List<String> toRemove = new ArrayList<String>();
		
		// remove interfaces because they dont have instance vars
		for (String subName : allSubclasses.keySet()) {
			Class<?> sub = allSubclasses.get(subName);
			if (isAbstractOrInterface(sub)) {
				toRemove.add(subName);
			}
		}
		
		// in separate for loop to avoid concurrency issues
		for (String remove : toRemove) {
			allSubclasses.remove(remove);
		}

		
		// init combo box with values
		List<SimpleEntry<Class<R>, R>> allSubKeyset = new ArrayList<SimpleEntry<Class<R>, R>>();
		for (Class<R> rClass : allSubclasses.values()) {
			SimpleEntry<Class<R>, R> classEntry = new SimpleEntry<Class<R>, R>(rClass, null);
			allSubKeyset.add(classEntry);
		}		
		subclassBox.getItems().addAll(allSubKeyset);

		
		StringConverter<SimpleEntry<Class<R>, R>> comboBoxConverter = makeNewComboBoxStrConverter(clazz);
		subclassBox.setConverter(comboBoxConverter);
		
		return subclassBox;
	}
	
	private <R> StringConverter<SimpleEntry<Class<R>, R>> makeNewComboBoxStrConverter(Class<R> rClass) {
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
		
		return comboBoxConverter;
	}
	
	private Set<HBox> makePropertyBoxes(Field p, Object parent, String parentName, Set<HBox> properties) {
		if (parent instanceof Property) {
			// the parent is a Property, we can make a settings element
			HBox settingsHBox = makeSettingsObject(parent, p.getName());
			properties.add(settingsHBox);
			return properties;
		}  else if (parent instanceof gameElements.Sprite) {
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
			// prevents us from trying to initialize java classes
			if (myProjectClassNames.contains(p.getType().getName())) {
				Set<Field> allFields = getAllFields(new HashSet<Field>(), p.getType());
				// parent is probably an abstract class and therefore

				// impossible to make an instance
				if (parent == null) {
					parent = getSubclass(p.getType());
				}
				
				for (Field otherField : allFields) {
					otherField.setAccessible(true);
					Object o = fieldGetObject(otherField, parent);
					String pName = otherField.getName();
					
					properties.addAll(makePropertyBoxes(otherField, o, pName, properties));
				}
			}
		}
		return properties;
	}

	private Set<Field> getAllFields(Set<Field> fields, Class<?> type) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		if (type.getSuperclass() != null && myProjectClassNames.contains(type.getSuperclass().getTypeName())) {
			fields = getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}
	
	private <R> R newClassInstance(Class<R> clazz) {
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
		Class<R> sub = getSubclassWithoutInstance(clazz);
		return newClassInstance(sub);
	}

	private <R> Class<R> getSubclassWithoutInstance(Class<R> clazz) {
		// find an available subclass otherwise print an exception
		Map<String, Class<R>> parentSubclasses = SubclassEnumerator.getAllSubclasses(clazz);
		// make sure the picked class isn't abstract
		for (Class<R> sub : parentSubclasses.values()) {
			if (!isAbstractOrInterface(sub)) {
				return sub;
			}
		}
		// default
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
			//propHBox.getChildren().addAll(propLabelName, makeTableView(lp));
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
	
	

}
