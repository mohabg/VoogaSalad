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

import javafx.beans.property.*;

import javafx.scene.control.*;

import javafx.scene.layout.*;


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
			if(!f.isAnnotationPresent(IgnoreField.class) && !f.getType().isAnnotationPresent(IgnoreField.class)) {
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
		AnchorPane myAnchorPane = GUIObjectMaker.makeAnchorPane();
		 

	    myBox = populateTab(f, model);
	    
        myAnchorPane.getChildren().add(myBox);
		myScrollPane.setContent(myAnchorPane);


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
	

	private <R> VBox makeFieldVBox(Field f, Object model) {
		VBox propVBox = GUIObjectMaker.makeVBox();
		
		//Class<R> clazz = (Class<R>) f.getType();
		R fObj = (R) SettingsReflectUtils.fieldGetObject(f,model);
		System.out.println(fObj.getClass());
		List<HBox> props = makePropertyBoxes(model, f, fObj, (Class<R>) f.getType(), true);
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
				
				ComboBox<SimpleEntry<Class<R>, R>> mySubclassBox = SubclassComboBoxMaker.makeSubclassComboBox(lpr, null, rType, lpr);
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
		
		ComboBox<SimpleEntry<Class<R>, R>> mySubclassBox = SubclassComboBoxMaker.makeSubclassComboBox(lpr, null, rType, lpr);
		retVBox.getChildren().add(mySubclassBox);	
		
		// get a proper subclass of R if necessary
		if (rType.isInterface() || Modifier.isAbstract(rType.getModifiers())) {
			rType = (Class<R>) SettingsReflectUtils.getSubclass(rType);	
		}				
		rObj = (R) SettingsReflectUtils.getSubclassInstance(rType);	
		lpr.add(rObj);	// listener won't add it to the list when it's first added
		
		updateComboBoxValue((Class<R>) rObj.getClass(), rObj, mySubclassBox); 
	
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
				ComboBox<SimpleEntry<Class<R>, R>> mySubclassBoxKey = SubclassComboBoxMaker.makeSubclassComboBox(mprt, null, rType, mprt);	
				elementBoxKey.getChildren().add(mySubclassBoxKey);				
				updateComboBoxValue((Class<R>) rListElement.getClass(), rListElement, mySubclassBoxKey);
				
				
				VBox elementBoxValue = GUIObjectMaker.makeVBox();
				ComboBox<SimpleEntry<Class<T>, T>> mySubclassBoxValue = SubclassComboBoxMaker.makeSubclassComboBox(mprt, null, tType, mprt);
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
		ComboBox<SimpleEntry<Class<R>, R>> mySubclassBoxKey = SubclassComboBoxMaker.makeSubclassComboBox(mprt, null, rType, mprt);		
		elementBoxKey.getChildren().add(mySubclassBoxKey);
		
		// get a proper subclass of R if necessary
		if (SettingsReflectUtils.isAbstractOrInterface(rType)) {
			rType = (Class<R>) SettingsReflectUtils.getSubclass(rType);	
		} 
		rListElement = (R) SettingsReflectUtils.newClassInstance(rType);
		updateComboBoxValue((Class<R>) rListElement.getClass(), rListElement, mySubclassBoxKey);
		
		
		VBox elementBoxValue = GUIObjectMaker.makeVBox();	
		ComboBox<SimpleEntry<Class<T>, T>> mySubclassBoxValue = SubclassComboBoxMaker.makeSubclassComboBox(mprt, null, tType, mprt);	
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

		
	public static <R, K> List<HBox> makePropertyBoxes(Object parent, Field field, R fieldObject, Class<R> fieldClass, boolean makeBox) {
		HBox fieldVBoxHBox = GUIObjectMaker.makeHBox();		
		List<HBox> properties = new ArrayList<HBox>();
		
		String fieldName = field != null ? field.getName() : fieldObject.getClass().getName();
		
		
		if (fieldObject == null) {
			fieldClass = SettingsReflectUtils.getSubclass(fieldClass);
			fieldObject = (R) SettingsReflectUtils.newClassInstance(fieldClass);
			if (field != null) {
				try {
					field.set(fieldObject, parent);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
	
		if (Property.class.isAssignableFrom(fieldClass)) {
			fieldVBoxHBox.getChildren().addAll(SettingsObjectMaker.makeSettingsObject(fieldObject, fieldName));
			properties.add(fieldVBoxHBox);
			return properties;
		}
		
	
		// make subclass combobox if necessary
		if (makeBox && SubclassEnumerator.hasSubclasses(fieldClass)) {
			ComboBox<SimpleEntry<Class<R>, R>> subclassBox = null;
			subclassBox = SubclassComboBoxMaker.makeSubclassComboBox(parent, field, fieldClass);

			VBox vb = GUIObjectMaker.makeVBox(subclassBox);
			fieldVBoxHBox.getChildren().add(vb);
			System.out.println(fieldClass + " " + fieldObject.getClass() + " " + fieldName);
			updateComboBoxValue((Class<R>) fieldObject.getClass(), fieldObject, subclassBox);
			properties.add(fieldVBoxHBox);
			return properties;	
		}
		
		// prevents us from trying to initialize java classes	
		//System.out.println(fieldClass.getName());
		if (ObjectEditorConstants.getInstance().getSimpleClassNames().contains(fieldClass.getName())) {			
			Label propLabel = GUIObjectMaker.makeLabel(SettingsObjectMaker.convertCamelCase(fieldName));
			VBox fieldVBox = GUIObjectMaker.makeVBox(propLabel);			
			List<HBox> fieldHBoxes = new ArrayList<HBox>();
			
			List<Field> allFields = SettingsReflectUtils.getAllFields(new ArrayList<Field>(), fieldClass);
			for (Field childField : allFields) {
				childField.setAccessible(true);			
				if (!childField.isAnnotationPresent(IgnoreField.class) && !childField.getType().isAnnotationPresent(IgnoreField.class)) {
					if(childField.getType().isPrimitive()) {
						// TODO UNCOMMENT
						//throw new FieldTypeException("Field " + otherField.getType().getName() + " " + otherField.getName() + " in " + otherField.getDeclaringClass().getName() + " is a primitive");
					} else {
						K otherFieldObject = (K) SettingsReflectUtils.fieldGetObject(childField, fieldObject);
						
//						if (childField.isAnnotationPresent(SetFieldName.class)) {
//							field = childField.getAnnotation(SetFieldName.class).label();
//						}
						fieldHBoxes.addAll(makePropertyBoxes(fieldObject, childField, otherFieldObject, (Class<K>) childField.getType(), true));
					}
				}
			}
			
			fieldVBox.getChildren().addAll(fieldHBoxes);
			fieldVBoxHBox.getChildren().add(fieldVBox);			
		} 
		
		properties.add(fieldVBoxHBox);
		
		return properties;
	}

}
