package authoringEnvironment.settingsWindow.ObjectEditorFactory;

import authoringEnvironment.Settings;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.SetFieldName;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.ObjectEditorConstants;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Exceptions.FieldTypeException;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.GUIMakers.GUIObjectMaker;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.GUIMakers.SettingsObjectMaker;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.GUIMakers.SubclassComboBoxMaker;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Utilities.SettingsReflectUtils;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Utilities.SubclassEnumerator;
import gameplayer.ButtonFactory;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private final String ADD = "Add";
	
	
	public VisualFactory() {
		
	}


	public TabPane getMyTabs(Object model) {
		TabPane myTabs = GUIObjectMaker.makeTabPane();

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
		Tab myFieldTab = GUIObjectMaker.makeTab(tabName);
		VBox myBox = GUIObjectMaker.makeVBox();
		ScrollPane myScrollPane = GUIObjectMaker.makeScrollPane();
		//AnchorPane myAnchorPane = GUIObjectMaker.makeAnchorPane();
		 

	    myBox = populateTab(f, model);
	    
        //myAnchorPane.getChildren().add(myBox);
		myScrollPane.setContent(myBox);


        myFieldTab.setContent(myScrollPane);
		return myFieldTab;
	}

	private VBox populateTab(Field f, Object model) {
		VBox myBox = null;
		
		// this is for things like Lists and Maps
		if (f.getGenericType() instanceof ParameterizedType) {
			myBox = makeParameterizedVBox(f, model);					
		} else {
			myBox = makeFieldVBox(f, model);
		}
		
		return myBox;
	}


	private VBox makeParameterizedVBox(Field f, Object model) {
		VBox myV = null;
		
		ParameterizedType pt = (ParameterizedType) f.getGenericType();		
		Type[] params = pt.getActualTypeArguments();
		
		// handle parameterized property object types
		Class<?> rawTypeClass = SettingsReflectUtils.getClass(pt.getRawType().getTypeName());
		if (Property.class.isAssignableFrom(rawTypeClass)) {
			Property ptProperty = (Property) SettingsReflectUtils.fieldGetObject(f, model);
			
			if(params.length == 1) {
				// single param catch (most likely List)
				Class<?> paramClass0 = SettingsReflectUtils.getClass(params[0].getTypeName());
				myV = singleParamType(paramClass0, ptProperty);
			} else if (params.length == 2) {
				// double param catch (most likely Map)
				Class<?> paramClass0 = SettingsReflectUtils.getClass(params[0].getTypeName());
				Class<?> paramClass1 = SettingsReflectUtils.getClass(params[1].getTypeName());
				myV = doubleParamType(paramClass0, paramClass1, ptProperty);
			}		
		}
		
		return myV;
	}
	

	private <R> VBox makeFieldVBox(Field f, Object parentObj) {
		VBox propVBox = GUIObjectMaker.makeVBox();

		Class<R> clazz = (Class<R>) f.getType();
		R fObj = (R) SettingsReflectUtils.fieldGetObject(f, parentObj);
		
		Set<HBox> props = makePropertyBoxes(clazz, fObj, clazz.getName(), new HashSet<HBox>(), true);
		propVBox.getChildren().addAll(props);

		return propVBox;
	}

	
	private <R> VBox singleParamType(Class<R> rType, Object prop) {
		VBox singleParamVBox = GUIObjectMaker.makeVBox();
		
		if(prop instanceof ListProperty) {
			ListProperty<R> lpr = (ListProperty<R>) prop;

			// display any items already in the list
			for (R rListElement : lpr) {
				VBox elementBox = GUIObjectMaker.makeVBox();
				
				ComboBox<SimpleEntry<Class<R>, R>> mySubclassBox = SubclassComboBoxMaker.makeSubclassComboBox(rType, lpr);
				elementBox.getChildren().add(mySubclassBox);
				
				updateComboBoxValue((Class<R>) rListElement.getClass(), rListElement, mySubclassBox);
							
				singleParamVBox.getChildren().add(elementBox);			
			}
			
			
			
			Button addButton = GUIObjectMaker.makeButton(ADD, e -> {
				singleParamVBox.getChildren().add(singleParamVBox.getChildren().size() -1, addSingleParameter(rType, lpr));
			});
			singleParamVBox.getChildren().add(addButton);			
		}
		
		return singleParamVBox;
	}

	private <R> VBox addSingleParameter(Class<R> rType, ListProperty<R> lpr) {
		VBox retVBox = GUIObjectMaker.makeVBox();
		R rObj = null;
		
		ComboBox<SimpleEntry<Class<R>, R>> mySubclassBox = SubclassComboBoxMaker.makeSubclassComboBox(rType, lpr);
		retVBox.getChildren().add(mySubclassBox);	
		
		// get a proper subclass of R if necessary
		if (rType.isInterface() || Modifier.isAbstract(rType.getModifiers())) {
			rType = (Class<R>) SettingsReflectUtils.getSubclass(rType);	
		}
		
		rObj = (R) SettingsReflectUtils.newClassInstance(rType);		
		updateComboBoxValue((Class<R>) rObj.getClass(), rObj, mySubclassBox);
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

	
	private <R, T> VBox doubleParamType(Class<R> rType, Class<T> tType, Object prop) {
		VBox doubleParamVBox = GUIObjectMaker.makeVBox();
		
		if(prop instanceof MapProperty) {
			MapProperty<R,T> mprt = (MapProperty<R,T>) prop;
//			
			// display any items already in the map
			for (R rListElement : mprt.keySet()) {
				T tListElement = mprt.get(rListElement);
				
				VBox elementBoxKey = GUIObjectMaker.makeVBox();
				ComboBox<SimpleEntry<Class<R>, R>> mySubclassBoxKey = SubclassComboBoxMaker.makeSubclassComboBox(rType, mprt);	
				elementBoxKey.getChildren().add(mySubclassBoxKey);				
				updateComboBoxValue((Class<R>) rListElement.getClass(), rListElement, mySubclassBoxKey);
				
				
				VBox elementBoxValue = GUIObjectMaker.makeVBox();
				ComboBox<SimpleEntry<Class<T>, T>> mySubclassBoxValue = SubclassComboBoxMaker.makeSubclassComboBox(tType, mprt);
				elementBoxValue.getChildren().add(mySubclassBoxValue);		
				updateComboBoxValue((Class<T>) tListElement.getClass(), tListElement, mySubclassBoxValue);
				
				HBox keyval = GUIObjectMaker.makeHBox(elementBoxKey, elementBoxValue);
				doubleParamVBox.getChildren().add(keyval);
			}
			
			Button addButton = GUIObjectMaker.makeButton(ADD, e -> {
				doubleParamVBox.getChildren().add(doubleParamVBox.getChildren().size()-1, addDoubleParameter(rType, tType, mprt));
			});
			
			doubleParamVBox.getChildren().add(addButton);
		}
		
		return doubleParamVBox;
	}
	
	
	private <R,T> HBox addDoubleParameter(Class<R> rType, Class<T> tType, MapProperty<R,T> mprt) {
		HBox retHBox = GUIObjectMaker.makeHBox();
		R rListElement = null;
		T tListElement = null;
		
		VBox elementBoxKey = GUIObjectMaker.makeVBox();	
		ComboBox<SimpleEntry<Class<R>, R>> mySubclassBoxKey = SubclassComboBoxMaker.makeSubclassComboBox(rType, mprt);		
		elementBoxKey.getChildren().add(mySubclassBoxKey);
		
		// get a proper subclass of R if necessary
		if (SettingsReflectUtils.isAbstractOrInterface(rType)) {
			rType = (Class<R>) SettingsReflectUtils.getSubclass(rType);	
		} 
		rListElement = (R) SettingsReflectUtils.newClassInstance(rType);
		updateComboBoxValue((Class<R>) rListElement.getClass(), rListElement, mySubclassBoxKey);
		
		
		VBox elementBoxValue = GUIObjectMaker.makeVBox();	
		ComboBox<SimpleEntry<Class<T>, T>> mySubclassBoxValue = SubclassComboBoxMaker.makeSubclassComboBox(tType, mprt);	
		elementBoxValue.getChildren().add(mySubclassBoxValue);
		
		// get a proper subclass of T if necessary
		if (SettingsReflectUtils.isAbstractOrInterface(tType)) {
			tType = (Class<T>) SettingsReflectUtils.getSubclass(tType);	
		}
		tListElement = (T) SettingsReflectUtils.newClassInstance(tType);		
		updateComboBoxValue((Class<T>) tListElement.getClass(), tListElement, mySubclassBoxValue);

		mprt.put(rListElement, tListElement);
		retHBox.getChildren().addAll(elementBoxKey, elementBoxValue);
		return retHBox;
	}

		
	public static <R, K> Set<HBox> makePropertyBoxes(Class<R> clazz, R parent, String parentName, Set<HBox> properties, boolean makeBox) {
		HBox fieldVBoxHBox = GUIObjectMaker.makeHBox();
		if (Property.class.isAssignableFrom(clazz)) {
			fieldVBoxHBox.getChildren().addAll(SettingsObjectMaker.makeSettingsObject(parent, parentName));
			properties.add(fieldVBoxHBox);
			return properties;
		} 
		
		
		// parent is probably an abstract class and therefore
		// impossible to make an instance
		if (parent == null) {
			parent = (R) SettingsReflectUtils.getSubclassInstance(clazz);
		}
		
		// make subclass combobox if necessary
		if (makeBox && SubclassEnumerator.hasSubclasses(clazz)) {
			ComboBox<SimpleEntry<Class<R>, R>> subclassBox = SubclassComboBoxMaker.makeSubclassComboBox(clazz);
			updateComboBoxValue(clazz, parent, subclassBox);
			
			VBox vb = GUIObjectMaker.makeVBox(subclassBox);
			fieldVBoxHBox.getChildren().add(vb);
		}
		
		// prevents us from trying to initialize java classes		
		if (ObjectEditorConstants.getInstance().getSimpleClassNames().contains(clazz.getName())) {			
			VBox fieldVBox = GUIObjectMaker.makeVBox();			
			Set<HBox> fieldHBoxes = new HashSet<HBox>();
			
			Set<Field> allFields = SettingsReflectUtils.getAllFields(new HashSet<Field>(), clazz);
			for (Field otherField : allFields) {
				otherField.setAccessible(true);			
				if (!otherField.isAnnotationPresent(IgnoreField.class)) {
					if(otherField.getType().isPrimitive()) {
						// TODO UNCOMMENT
						//throw new FieldTypeException("Field " + otherField.getType().getName() + " " + otherField.getName() + " in " + otherField.getDeclaringClass().getName() + " is a primitive");
					} else {
						String pName = otherField.getName();
						K otherFieldObject = (K) SettingsReflectUtils.fieldGetObject(otherField, parent);
						
						if (otherField.isAnnotationPresent(SetFieldName.class)) {
							pName = otherField.getAnnotation(SetFieldName.class).label();
						}

						fieldHBoxes.addAll(makePropertyBoxes((Class<K>) otherField.getType(), otherFieldObject, pName, properties, true));
					}
				}
			}
			
			fieldVBox.getChildren().addAll(fieldHBoxes);
			fieldVBoxHBox.getChildren().add(fieldVBox);			
		} else {
			// TODO THROW EXCEPTION
			// idk if this is necessary
		}
		
		if (fieldVBoxHBox.getChildren().size() > 1) {
			properties.add(fieldVBoxHBox);
		}
		return properties;
	}

}
