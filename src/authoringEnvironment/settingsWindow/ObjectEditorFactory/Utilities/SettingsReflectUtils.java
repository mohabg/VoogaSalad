package authoringEnvironment.settingsWindow.ObjectEditorFactory.Utilities;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.ObjectEditorConstants;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Exceptions.ReflectUtilsException;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Helpful reflection utilities used throughout the project
 * @author joejacob
 */
public class SettingsReflectUtils {
	private static final String FIELD_GET_EXCEPTION = "Cannot get field";
	private static final String FIELD_SET_EXCEPTION = "Cannot set object to field";
	private static String NEW_CLASS_INSTANCE_EXCEPTION = "Class instance cannot be made";
	private static String CLASS_INSTANCE_EXCEPTION = "Class name not found";
	private static String NO_SUBCLASS_EXCEPTION = "No subclasses for the class can be found";
	private static String NO_PROPERTY_EXCEPTION = "No subclass found for the Property class";
	
	/**
	 * wrapper for Class.newInstance()
	 * returns a new class instance of clazz
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <R> R newClassInstance(Class<R> clazz) {
		if (clazz.isEnum()) {
			return (R) clazz;
		}
		
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ReflectUtilsException(NEW_CLASS_INSTANCE_EXCEPTION);
		}
	}
	
	
	/**
	 * wrapper for Class.forName()
	 * returns a Class<?> object for className
	 * @param className
	 * @return
	 */
	public static Class<?> getClass(String className) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new ReflectUtilsException(CLASS_INSTANCE_EXCEPTION);
		}
		return clazz;
	}

	/**
	 * Returns any subclass instance of clazz
	 * @param clazz
	 * @return
	 */
	public static <R> Object getSubclassInstance(Class<R> clazz) {
		Class<R> sub = getSubclass(clazz);
		return newClassInstance(sub);
	}

	/**
	 * Returns a subclass Class<R> object for class
	 * supports user-made classes and Property and enums
	 * for enums, the enum class is return
	 * for Property, the corresponding SimpleProperty is returned
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <R> Class<R> getSubclass(Class<R> clazz) {
		// if it's not a user-made class, probably a java property
		List<String> myProjectClassNames = ObjectEditorConstants.getInstance().getSimpleClassNames();
		if (!myProjectClassNames.contains(clazz.getName())) {
			if (Property.class.isAssignableFrom(clazz)) {
				return (Class<R>) getPropertySubclass(clazz);
			} else if(clazz.isEnum()) {
				return clazz;
			}
			return clazz;
		}
				
		Map<String, Class<R>> parentSubclasses = ClassEnumerator.getAllSubclasses(clazz);
		for (Class<R> sub : parentSubclasses.values()) {
			if (!isAbstractOrInterface(sub)) {
				return sub;
			}
		}
		
		throw new ReflectUtilsException(NO_SUBCLASS_EXCEPTION);
	}
	
	/**
	 * check's if a class has subclasses
	 * only checks for user-made classes and returns true if enum
	 * @param clazz
	 * @return
	 */
	public static boolean hasSubclasses(Class<?> clazz) {
		if (ClassEnumerator.getAllSubclasses(clazz).size() > 1) {
			return true;
		} else if (clazz.isEnum()) {
			return true;
		}
		
		return false;
	}

	/**
	 * Returns a subclass for a Property
	 * @param clazz
	 * @return
	 */
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
		} else {
			throw new ReflectUtilsException(NO_PROPERTY_EXCEPTION);
		}
	}
	
	/**
	 * Returns whether or not the given Class<?> is abstract or an interface
	 * @param clazz
	 * @return
	 */
	public static boolean isAbstractOrInterface(Class<?> clazz) {
		return clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers());
	}
	
	/**
	 * Checks if the given field is a property
	 * @param p
	 * @return
	 */
	public static boolean isAProperty(Field p) {
		return Property.class.isAssignableFrom(p.getType());
	}
	
	/**
	 * Wrapper for Field.get 
	 * @param childField
	 * @param parentObject
	 * @return
	 */
	public static Object fieldGetObject(Field childField, Object parentObject) {
		Object o = null;
		try {
			o = childField.get(parentObject);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ReflectUtilsException(FIELD_GET_EXCEPTION);
		}
		return o;
	}
	
	/**
	 * wrapper for Field.set
	 * @param childField
	 * @param childObject
	 * @param parentObject
	 */
	public static void fieldSetObject(Field childField, Object childObject, Object parentObject) {
		try {
			childField.set(childObject, parentObject);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ReflectUtilsException(FIELD_SET_EXCEPTION);
		}
	}
	
	/**
	 * Returns a List<Field> of all fields in the given class and its superclasses
	 * @param fields
	 * @param type
	 * @return
	 */
	public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));
		
		List<String> myProjectClassNames = ClassEnumerator.getAllSimpleClassNames();
		if (type.getSuperclass() != null && myProjectClassNames.contains(type.getSuperclass().getTypeName())) {
			fields = getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}


}
