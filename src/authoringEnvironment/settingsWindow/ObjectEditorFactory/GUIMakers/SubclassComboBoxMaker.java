package authoringEnvironment.settingsWindow.ObjectEditorFactory.GUIMakers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;
import java.util.AbstractMap.SimpleEntry;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.VisualFactory;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Utilities.SettingsReflectUtils;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Utilities.ClassEnumerator;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class SubclassComboBoxMaker {
	
	// we're making a combobox for the class of the childField
	public static <R> ComboBox<SimpleEntry<Class<R>, R>> makeSubclassComboBox(Object parent, Field childField, Class<R> clazz, Property... property) {	
		ComboBox<SimpleEntry<Class<R>, R>> comboBox = makeComboBox(clazz);
		ChangeListener<SimpleEntry<Class<R>, R>> comboBoxListener = makeCBListener(parent, childField, property);
		comboBox.valueProperty().addListener(comboBoxListener);
		
		return comboBox;
	}
	
	private static <R, T> ChangeListener<SimpleEntry<Class<R>, R>> makeCBListener(Object parent, Field childField, Property... property) {
		ChangeListener<SimpleEntry<Class<R>, R>> boxChangeListener = (o, ov, nv) -> {
			ObjectProperty<SimpleEntry<Class<R>, R>> objProp = (ObjectProperty<SimpleEntry<Class<R>, R>>) o;
			ComboBox subclassBox = (ComboBox) objProp.getBean();
			Pane myComboBoxParent = (Pane) subclassBox.getParent();
			myComboBoxParent.getChildren().clear();
			
			// switch corresponding instances	
			Class<R> newClassType = nv.getKey();
			
			if (nv.getValue() == null) {		
				nv.setValue(SettingsReflectUtils.newClassInstance(newClassType));
			}
			
			o.getValue().setValue(nv.getValue());
			
			if (childField != null) {
				SettingsReflectUtils.fieldSetObject(childField, parent, nv.getValue());
			}
			
			// only if it's a property
			if (ov != null && property.length == 1) {
				Property prop = property[0];
				if(prop instanceof ListProperty) {
					ListProperty<R> lpr = (ListProperty<R>) prop;
					lpr.remove(ov.getValue());
					lpr.add(o.getValue().getValue());
				} else if(prop instanceof MapProperty) {
					MapProperty<R,T> mprt = (MapProperty<R,T>) prop;
					
					Class<?> type = null;
					for(R pr : mprt.keySet()) {
						type = pr.getClass();
					}
								
					// if ov corresponds to a map key
					if(type.isAssignableFrom(ov.getValue().getClass())) {
						T val = (T) mprt.get(ov.getValue());
						mprt.remove(ov.getValue());
						mprt.put(o.getValue().getValue(), val);
					} else {
						Pane myComboBoxGrandParent = (Pane) myComboBoxParent.getParent();
						ComboBox<SimpleEntry<Class<R>, R>> mySubclassBoxKeyCopy = (ComboBox<SimpleEntry<Class<R>, R>>) ((VBox) myComboBoxGrandParent.getChildren().get(0)).getChildren().get(0);
						R key = mySubclassBoxKeyCopy.getValue().getValue();				
						mprt.put(key, (T) o.getValue().getValue());	
					}
				}
			}		
			
			myComboBoxParent.getChildren().setAll(subclassBox);
			if (!o.getValue().getKey().isEnum()) {
				myComboBoxParent.getChildren().addAll(VisualFactory.makePropertyBoxes(parent, childField, o.getValue().getValue(), o.getValue().getKey(), false));
			}
		};
		
		return boxChangeListener;
	}

	private static <R> ComboBox<SimpleEntry<Class<R>, R>> makeComboBox(Class<R> clazz) {
		ComboBox<SimpleEntry<Class<R>, R>> subclassBox = GUIObjectMaker.makeComboBox();
		
		Map<String, Class<R>> allSubclasses = ClassEnumerator.getAllSubclasses(clazz);
		List<String> toRemove = new ArrayList<String>();
		
		// remove interfaces/abstract because they dont have instance vars
		for (String subName : allSubclasses.keySet()) {
			Class<?> sub = allSubclasses.get(subName);
			if (SettingsReflectUtils.isAbstractOrInterface(sub)) {
				toRemove.add(subName);
			}
		}
		
		// in separate for loop to avoid concurrency issues
		for (String remove : toRemove) {
			allSubclasses.remove(remove);
		}
	
		// init combo box with values
		List<SimpleEntry<Class<R>, R>> allSubKeyset = new ArrayList<SimpleEntry<Class<R>, R>>();
		
		if (Property.class.isAssignableFrom(clazz)) {
			clazz = (Class<R>) SettingsReflectUtils.getPropertySubclass(clazz);
			allSubKeyset.add(new SimpleEntry<Class<R>, R>(clazz, null));
		} else if (clazz.isEnum()) {
			R[] enumVals =  clazz.getEnumConstants();
			for (R val : enumVals) {
				allSubKeyset.add(new SimpleEntry<Class<R>, R>((Class<R>)val.getClass(), (R) val));
			}
		} else {
			for (Class<R> rClass : allSubclasses.values()) {
				allSubKeyset.add(new SimpleEntry<Class<R>, R>(rClass, null));
			}	
		}
			
		subclassBox.getItems().addAll(allSubKeyset);
		
		StringConverter<SimpleEntry<Class<R>, R>> comboBoxConverter = makeNewComboBoxStrConverter(clazz);
		subclassBox.setConverter(comboBoxConverter);
		
		
		return subclassBox;
	}
	
	private static <R> StringConverter<SimpleEntry<Class<R>, R>> makeNewComboBoxStrConverter(Class<R> rClass) {
		StringConverter<SimpleEntry<Class<R>, R>> comboBoxConverter = new StringConverter<SimpleEntry<Class<R>, R>>() {
			@Override
			public String toString(SimpleEntry<Class<R>, R> object) {
				String displayString = "";
				if (object.getKey().isEnum()) {
					displayString = object.getValue().toString();
				} else {
					Class<R> clazz = object.getKey();
					displayString = clazz.getSimpleName();
				}
				
				String convertedDisplayString = SettingsObjectMaker.convertCamelCase(displayString);
				return convertedDisplayString;
			}

			@Override
			public SimpleEntry<Class<R>, R> fromString(String string) {
				// not editable
				return null;
			}			
		};	
		
		return comboBoxConverter;
	}
}
