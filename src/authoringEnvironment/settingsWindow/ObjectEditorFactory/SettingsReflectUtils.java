package authoringEnvironment.settingsWindow.ObjectEditorFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class SettingsReflectUtils {
	
	public static <R> R newClassInstance(Class<R> clazz) {
		if (clazz.isEnum()) {
			return (R) clazz;
		}
		
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static Class<?> getClass(String className) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clazz;
	}

	public static <R> Object getSubclassInstance(Class<R> clazz) {
		Class<R> sub = getSubclass(clazz);
		return newClassInstance(sub);
	}

	public static <R> Class<R> getSubclass(Class<R> clazz) {
		// if it's not a user-made class, probably a java property
		List<String> myProjectClassNames = SubclassEnumerator.getAllSimpleClassNames();
		if (!myProjectClassNames.contains(clazz.getName())) {
			if (Property.class.isAssignableFrom(clazz)) {
				return (Class<R>) getPropertySubclass(clazz);
			} else if(clazz.isEnum()) {
				return clazz;
			}
			return clazz;
		}
		
		// TODO MAKE AN EXCEPTION
		// find an available subclass otherwise print an exception
		Map<String, Class<R>> parentSubclasses = SubclassEnumerator.getAllSubclasses(clazz);
		// make sure the picked class isn't abstract
		for (Class<R> sub : parentSubclasses.values()) {
			if (!isAbstractOrInterface(sub)) {
				return sub;
			}
		}
		
		return null;
	}

	public static Class<?> getPropertySubclass(Class<?> clazz) {
		if (clazz.isAssignableFrom(SimpleIntegerProperty.class)) {
			return SimpleIntegerProperty.class;
		} else if (clazz.isAssignableFrom(SimpleDoubleProperty.class)) {
			return SimpleDoubleProperty.class;
		} else if (clazz.isAssignableFrom(SimpleBooleanProperty.class)) {
			return SimpleBooleanProperty.class;
		} else if (clazz.isAssignableFrom(SimpleStringProperty.class)) {
			return SimpleStringProperty.class;
		} else if (clazz.isAssignableFrom(SimpleObjectProperty.class)) {
			return SimpleObjectProperty.class;
		}
		// TODO throw error
		return null;
	}
	
	public static boolean isAbstractOrInterface(Class<?> clazz) {
		return clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers());
	}
	
	public static Object fieldGetObject(Field childField, Object parentObject) {
		Object o = null;
		try {
			o = childField.get(parentObject);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return o;
	}

	public static boolean isAProperty(Field p) {
		return Property.class.isAssignableFrom(p.getType());
	}
}
