package authoringEnvironment.settingsWindow.ObjectEditorFactory.GUIMakers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;
import java.util.AbstractMap.SimpleEntry;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Utilities.SettingsReflectUtils;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Main.VisualFactory;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Utilities.ClassEnumerator;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

/**
 * makes the combobox that displays all subclasses of a given object
 * that's used for generating different properties
 * @author joejacob
 */
public class SubclassComboBoxMaker {
	
	
	/**
	 * makes a combobox for the corresponding Class<R> clazz and an added listener
	 * property is null if the combobox doesn't need to be associated with a ListProperty or a MapProperty
	 * @param parent
	 * @param childField
	 * @param clazz
	 * @param property
	 * @return
	 */
	public static <R, K> ComboBox<SimpleEntry<Class<R>, R>> makeSubclassComboBox(K parent, Field childField, Class<R> clazz, Property... property) {	
		ComboBox<SimpleEntry<Class<R>, R>> comboBox = makeComboBox(clazz);
		ChangeListener<SimpleEntry<Class<R>, R>> comboBoxListener = makeCBListener(parent, childField, property);
		comboBox.valueProperty().addListener(comboBoxListener);
		
		return comboBox;
	}
	
	/**
	 * creates a ChangeListener for a combobox that displays a given class's  so that whenever the value is changed to a new subclass,
	 * the corresponding property boxes are generated
	 * @param parent
	 * @param childField
	 * @param property
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <R, T, K> ChangeListener<SimpleEntry<Class<R>, R>> makeCBListener(K parent, Field childField, Property<?>... property) {
		ChangeListener<SimpleEntry<Class<R>, R>> boxChangeListener = (o, ov, nv) -> {
			// get the calling combobox and its surrounding Pane and clear it
			ObjectProperty<SimpleEntry<Class<R>, R>> objProp = (ObjectProperty<SimpleEntry<Class<R>, R>>) o;
			ComboBox<?> subclassBox = (ComboBox<?>) objProp.getBean();
			Pane myComboBoxParent = (Pane) subclassBox.getParent();
			myComboBoxParent.getChildren().clear();
			
			
			// update the values in the combobox and bind if a new instance is made	
			Class<R> newClassType = nv.getKey();			
			if (nv.getValue() == null) {		
				nv.setValue(SettingsReflectUtils.newClassInstance(newClassType));
			}	
			
			if (childField != null) {
				SettingsReflectUtils.fieldSetObject(childField, parent, nv.getValue());
			}
			o.getValue().setValue(nv.getValue());	
			
			
			// update listproperty/mapproperty objects if the property is a property
			if (ov != null && property.length == 1) {
				Property<?> prop = property[0];
				
				if(prop instanceof ListProperty) {
					ListProperty<R> lpr = (ListProperty<R>) prop;
					lpr.remove(ov.getValue());
					lpr.add(o.getValue().getValue());
				} else if(prop instanceof MapProperty) {
					MapProperty<R,T> mprt = (MapProperty<R,T>) prop;
					
					// find out whether the change is for the key or value of the map
					Class<?> type = null;
					for(R pr : mprt.keySet()) {
						type = pr.getClass();
					}
								
					// update key-value in map
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
			
			// re-add combobox and generate new property boxes
			myComboBoxParent.getChildren().setAll(subclassBox);
			if (!o.getValue().getKey().isEnum()) {
				myComboBoxParent.getChildren().addAll(VisualFactory.makePropertyBoxes(parent, childField, o.getValue().getValue(), o.getValue().getKey(), false));
			}
		};
		
		return boxChangeListener;
	}

	
	/**
	 * makes a subclass combobox for Class<R> clazz
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <R> ComboBox<SimpleEntry<Class<R>, R>> makeComboBox(Class<R> clazz) {
		ComboBox<SimpleEntry<Class<R>, R>> subclassBox = GUIObjectMaker.makeComboBox();
		Map<String, Class<R>> allSubclasses = ClassEnumerator.getAllSubclasses(clazz);
		
		
		// remove interfaces/abstract because they can't be instantiated
		allSubclasses = deleteAbstractAndInterfaces(allSubclasses);
	
		
		// init combo box with initial key-values
		List<SimpleEntry<Class<R>, R>> allSubKeyset = makeComboBoxInitValues(clazz, allSubclasses);		
		subclassBox.getItems().addAll(allSubKeyset);
		
		// make string converter for combobox
		StringConverter<SimpleEntry<Class<R>, R>> comboBoxConverter = makeNewComboBoxStrConverter(clazz);
		subclassBox.setConverter(comboBoxConverter);
		
		return subclassBox;
	}

	@SuppressWarnings("unchecked")
	private static <R> List<SimpleEntry<Class<R>, R>> makeComboBoxInitValues(Class<R> clazz, Map<String, Class<R>> allSubclasses) {
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
		
		return allSubKeyset;
	}

	private static <R> Map<String, Class<R>> deleteAbstractAndInterfaces(Map<String, Class<R>> allSubclasses) {
		List<String> toRemove = new ArrayList<String>();
		for (String subName : allSubclasses.keySet()) {
			Class<?> sub = allSubclasses.get(subName);
			if (SettingsReflectUtils.isAbstractOrInterface(sub)) {
				toRemove.add(subName);
			}
		}
		for (String remove : toRemove) {
			allSubclasses.remove(remove);
		}
		
		return allSubclasses;
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
