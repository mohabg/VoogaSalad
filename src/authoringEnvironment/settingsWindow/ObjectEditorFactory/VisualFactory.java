package authoringEnvironment.settingsWindow.ObjectEditorFactory;

import authoringEnvironment.Settings;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.SetFieldName;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Exceptions.FieldTypeException;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.GUIMakers.SettingsObjectMaker;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.GUIMakers.SubclassComboBoxMaker;
import gameplayer.ButtonFactory;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.util.StringConverter;
import resources.FrontEndData;
import resources.ResourcesReader;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;


/**
 * @author David Yan, Joe Jacob, Huijia Yu
 */
public class VisualFactory {

	private static List<String> myProjectClassNames;
	
	public VisualFactory() {
		SubclassEnumerator.getInstance();
		myProjectClassNames = SubclassEnumerator.getAllSimpleClassNames();
	}


	public TabPane getMyTabs(Object model) {
		TabPane myTabs = new TabPane();
		myTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);	// cant close tabs

//		myTabs.getStylesheets().add("authoringEnvironment/settingsWindow/settingsWindow.css");
//        myTabs.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");
        myTabs.getStylesheets().add(FrontEndData.STYLESHEET);


		Field[] fields = model.getClass().getDeclaredFields();

		for (Field f : fields) {
			f.setAccessible(true);
			
			if(!f.isAnnotationPresent(IgnoreField.class)) {
				if(f.getType().isPrimitive()) {
					throw new FieldTypeException("Field " + f.getType().getName() + " " + f.getName() + " in " + f.getDeclaringClass().getName() + " is a primitive");
				}
				
				myTabs.getTabs().add(getOneTab(f, model));
			}
			
			
		}

		return myTabs;
	}


	private Tab getOneTab(Field f, Object model) {
		String tabName = SettingsObjectMaker.convertCamelCase(f.getName());
		if (f.isAnnotationPresent(SetFieldName.class)) {
			tabName = f.getAnnotation(SetFieldName.class).label();
		}
		Tab myFieldTab = new Tab(tabName);
		VBox myBox = new VBox();
		ScrollPane myScrollPane = new ScrollPane();
		AnchorPane myAnchorPane = new AnchorPane();
		 
		myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		
		myScrollPane.getStylesheets().add(FrontEndData.STYLESHEET);
	    myAnchorPane.getStylesheets().add(FrontEndData.STYLESHEET);


	    myBox = populateTab(f, model);
	    
        myAnchorPane.getChildren().add(myBox);
		myScrollPane.setContent(myAnchorPane);


        myFieldTab.setContent(myScrollPane);
		return myFieldTab;
	}

	private VBox populateTab(Field f, Object model) {
		VBox myBox = new VBox();
		// this is for things like Lists and Maps
		if (f.getGenericType() instanceof ParameterizedType) {
			HBox myH = new HBox();
			ParameterizedType pt = (ParameterizedType) f.getGenericType();
			
			Type[] params = pt.getActualTypeArguments();
			
			// handle parameterized property object types
			Class<?> rawTypeClass = SettingsReflectUtils.getClass(pt.getRawType().getTypeName());
			if (Property.class.isAssignableFrom(rawTypeClass)) {
				Property ptProperty = (Property) SettingsReflectUtils.fieldGetObject(f, model);
				
				if(params.length == 1) {
					// single param catch (most likely List)
					Class<?> paramClass0 = SettingsReflectUtils.getClass(params[0].getTypeName());
					myH.getChildren().add(singleParamType(paramClass0, ptProperty));
				} else if (params.length == 2) {
					// double param catch (most likely Map)
					Class<?> paramClass0 = SettingsReflectUtils.getClass(params[0].getTypeName());
					Class<?> paramClass1 = SettingsReflectUtils.getClass(params[1].getTypeName());
					myH.getChildren().add(doubleParamType(paramClass0, paramClass1, ptProperty));
				}		
			}
			
			myBox.getChildren().add(myH);			
		} else {
			// populate pulldown with all subclasses
			VBox propVBox = makeFieldVBox(f, model);
			myBox.getChildren().addAll(propVBox);
		}
		
		return myBox;
	}
	
	
	private <R> VBox singleParamType(Class<R> rType, Object prop) {
		VBox singleParamVBox = new VBox();
		
		if(prop instanceof ListProperty) {
			ListProperty<R> lpr = (ListProperty<R>) prop;

			// display any items already in the list
			for (R rListElement : lpr) {
				VBox elementBox = new VBox();
				
				ComboBox<SimpleEntry<Class<R>, R>> mySubclassBox = SubclassComboBoxMaker.makeSubclassComboBox(rType, lpr);
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
		
		ComboBox<SimpleEntry<Class<R>, R>> mySubclassBox = SubclassComboBoxMaker.makeSubclassComboBox(rType, lpr);
		retVBox.getChildren().add(mySubclassBox);	
		
		// get a proper subclass of R if necessary
		if (rType.isInterface() || Modifier.isAbstract(rType.getModifiers())) {
			rType = (Class<R>) SettingsReflectUtils.getSubclass(rType);	
		}
		
		rObj = (R) SettingsReflectUtils.newClassInstance(rType);		
		updateComboBoxValue(rType, rObj, mySubclassBox);
		lpr.add(rObj);	// listener won't add it to the list when it's first added
	
		return retVBox;
	}

	private static <R> void updateComboBoxValue(Class<R> rType, R rObj, ComboBox<SimpleEntry<Class<R>, R>> mySubclassBox) {
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

	
	private <R, T> VBox doubleParamType(Class<R> rType, Class<T> tType, Property prop) {
		VBox doubleParamVBox = new VBox();
		
		if(prop instanceof MapProperty) {
			MapProperty<R,T> mprt = (MapProperty<R,T>) prop;
//			
			// display any items already in the map
			for (R rListElement : mprt.keySet()) {
				T tListElement = mprt.get(rListElement);
				
				VBox elementBoxKey = new VBox();
				ComboBox<SimpleEntry<Class<R>, R>> mySubclassBoxKey = SubclassComboBoxMaker.makeSubclassComboBox(rType, mprt);	
				elementBoxKey.getChildren().add(mySubclassBoxKey);				
				updateComboBoxValue((Class<R>) rListElement.getClass(), rListElement, mySubclassBoxKey);
				
				
				VBox elementBoxValue = new VBox();
				ComboBox<SimpleEntry<Class<T>, T>> mySubclassBoxValue = SubclassComboBoxMaker.makeSubclassComboBox(tType, mprt);
				elementBoxValue.getChildren().add(mySubclassBoxValue);		
				updateComboBoxValue((Class<T>) tListElement.getClass(), tListElement, mySubclassBoxValue);
				
				HBox keyval = new HBox(elementBoxKey, elementBoxValue);
				doubleParamVBox.getChildren().add(keyval);
			}
			
			Button addButton = ButtonFactory.makeButton("Add", e -> {
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
		
		VBox elementBoxKey = new VBox();	
		ComboBox<SimpleEntry<Class<R>, R>> mySubclassBoxKey = SubclassComboBoxMaker.makeSubclassComboBox(rType, mprt);		
		elementBoxKey.getChildren().add(mySubclassBoxKey);
		
		// get a proper subclass of R if necessary
		if (SettingsReflectUtils.isAbstractOrInterface(rType)) {
			rType = (Class<R>) SettingsReflectUtils.getSubclass(rType);	
		}
		rListElement = (R) SettingsReflectUtils.newClassInstance(rType);	
		updateComboBoxValue((Class<R>) rListElement.getClass(), rListElement, mySubclassBoxKey);
		
		
		VBox elementBoxValue = new VBox();	
		ComboBox<SimpleEntry<Class<T>, T>> mySubclassBoxValue = SubclassComboBoxMaker.makeSubclassComboBox(tType, mprt);	
		elementBoxValue.getChildren().add(mySubclassBoxValue);
		
		// get a proper subclass of T if necessary
		if (SettingsReflectUtils.isAbstractOrInterface(tType)) {
			tType = (Class<T>) SettingsReflectUtils.getSubclass(tType);	
		}
		tListElement = (T) SettingsReflectUtils.newClassInstance(tType);		
		updateComboBoxValue((Class<T>) tListElement.getClass(), tListElement, mySubclassBoxValue);

		retHBox.getChildren().addAll(elementBoxKey, elementBoxValue);
		return retHBox;
	}

		
	// ONLY MAKE COMBO BOX IF THERE IS A SUBCLASS
	// SET DEFAULT VALUE OF COMBOBOX TO BE FIRST VALUE IN OBSERVABLE LIST
	private <R> VBox makeFieldVBox(Field f, Object parentObj) {
		VBox fieldVBox = new VBox();
		VBox propVBox = new VBox();

		Class<R> clazz = (Class<R>) f.getType();

		R fObj = (R) SettingsReflectUtils.fieldGetObject(f, parentObj);		
		Set<HBox> props = makePropertyBoxes(clazz, fObj, fObj.getClass().getName(), new HashSet<HBox>(), true);
		propVBox.getChildren().addAll(props);


		fieldVBox.getChildren().add(propVBox);

		return fieldVBox;
	}
		
	public static <R, K> Set<HBox> makePropertyBoxes(Class<R> clazz, R parent, String parentName, Set<HBox> properties, boolean makeBox) {
		if (Property.class.isAssignableFrom(clazz)) {
			// the parent is a Property, we can make a settings element
			HBox settingsHBox = new HBox(SettingsObjectMaker.makeSettingsObject(parent, parentName));
			properties.add(settingsHBox);
			return properties;
		} else if (makeBox && SubclassEnumerator.hasSubclasses(clazz)) {
			HBox fieldVBoxHBox = new HBox();
			ComboBox<SimpleEntry<Class<R>, R>> subclassBox = SubclassComboBoxMaker.makeSubclassComboBox(clazz);
			updateComboBoxValue(clazz, parent, subclassBox);
			System.out.println(clazz.getName());
			VBox vb = new VBox(subclassBox);
			fieldVBoxHBox.getChildren().add(vb);
			properties.add(fieldVBoxHBox);
			return properties;
		}


		// prevents us from trying to initialize java classes		
		if (myProjectClassNames.contains(clazz.getName())) {
			Set<Field> allFields = SettingsReflectUtils.getAllFields(new HashSet<Field>(), clazz);
			// parent is probably an abstract class and therefore

			// impossible to make an instance
			if (parent == null) {
				parent = (R) SettingsReflectUtils.getSubclassInstance(clazz);
			}
			
			HBox fieldVBoxHBox = new HBox();
			VBox fieldVBox = new VBox();
			Set<HBox> fieldHBoxes = new HashSet<HBox>();
			for (Field otherField : allFields) {
				otherField.setAccessible(true);
				
				if (!otherField.isAnnotationPresent(IgnoreField.class)) {
					if(otherField.getType().isPrimitive()) {
						// TODO UNCOMMENT
						//throw new FieldTypeException("Field " + otherField.getType().getName() + " " + otherField.getName() + " in " + otherField.getDeclaringClass().getName() + " is a primitive");
					}
					String pName = otherField.getName();
					if (otherField.isAnnotationPresent(SetFieldName.class)) {
						pName = otherField.getAnnotation(SetFieldName.class).label();
					}
					
					K o = (K) SettingsReflectUtils.fieldGetObject(otherField, parent);
					fieldHBoxes.addAll(makePropertyBoxes((Class<K>) otherField.getType(), o, pName, properties, true));
				}
			}
			fieldVBox.getChildren().addAll(fieldHBoxes);
			fieldVBoxHBox.getChildren().add(fieldVBox);			
			properties.add(fieldVBoxHBox);
		}

		return properties;
	}

	
	

}
