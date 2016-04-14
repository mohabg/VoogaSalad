package authoringEnvironment.settingsWindow;

import authoringEnvironment.Settings;
import authoringEnvironment.SubclassEnumerator;
import gameElements.Sprite;
import javafx.beans.binding.NumberExpressionBase;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import resources.ResourcesReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author David Yan, Joe Jacob, Huijia Yu
 */
public class VisualFactory {
	private Settings mySettings;
	private ResourcesReader myReader;
	private int poo;

	public VisualFactory() {
		mySettings = new Settings();
	}

	public String reflectionTest(Sprite mySprite) {

		return "";
	}

	public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		if (type.getSuperclass() != null) {
			fields = getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}

	public Constructor[] getConstructors(Class myClass) {
		return myClass.getConstructors();
	}

	public static HBox makeTextInputBox(Parameter myParam, Field f) {
		Label label1 = new Label("This:");
		TextField textField = new TextField(f.toString());
		HBox hb = new HBox();
		hb.getChildren().addAll(label1, textField);
		hb.setSpacing(10);
		// TODO: Bind this to parameter if possible
		return hb;
	}

	private <T> List<T> makeList(Class<T> type) {
		List<T> pp = new ArrayList<T>();
		return new ArrayList<T>();
	}

	// TODO: Binding and figuring out list of objects in reflection
	public TabPane getMyTabs(Sprite mySprite) {
		TabPane myTabs = new TabPane();

		Field[] fields = mySprite.getClass().getDeclaredFields();

		for (Field f : fields) {
			myTabs.getTabs().add(getOneTab(f, mySprite));
		}
		//
		return myTabs;
	}

	private Tab getOneTab(Field f, Sprite mySprite) {
		f.setAccessible(true);
		String tabName = f.getName();
		Tab myTab = new Tab(tabName);
		VBox myBox = new VBox();
		AnchorPane myPane = new AnchorPane();
		System.out.println(f.getGenericType());
		Set<HBox> props = makePropertyBoxes(f, mySprite, new HashSet<HBox>());
		System.out.println("me poops " + props);

		myBox.getChildren().addAll(props);
		// myBox.getChildren().add(oneSpinner(f, mySprite));
		// Field[] properties = f.getType().getDeclaredFields();
		// for (Field p: properties){
		// myBox.getChildren().add(oneSpinner(p));
		// }
		// TODO I HAVE TO FIGURE OUT HOW I WOULD KNOW IT'S SPRITEPROPERTIES
		myPane.getChildren().add(myBox);
		myTab.setContent(myPane);
		return myTab;
	}

	private Set<HBox> makePropertyBoxes(Field f, Object parent, Set<HBox> properties) {
		f.setAccessible(true);
		Type fType = f.getGenericType();
		String fParamName = f.getName();
		System.out.println(fType.getTypeName());
		if (fType.getTypeName().equals("gameElements.Sprite")) {
			return properties;
		}
		System.out.println("POOOOO");
		// f.setAccessible(true);
		// Class<?> fClass = f.getType();
		Class<?> fClass = Object.class;
		try {
			fClass = Class.forName(((ParameterizedType) fType).getRawType().getTypeName());
		} catch (Exception e) {

			try {
				fClass = Class.forName(fType.getTypeName());
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}

		String fName = fClass.getName();
		// String fParamName = f.getName();
		System.out.println("STARTTTT " + fName);

		List<String> projClasses = SubclassEnumerator.getReadableClasses("gameElements");
		projClasses.addAll(SubclassEnumerator.getReadableClasses("authoringEnvironment"));

		if (!projClasses.contains(fName)) {
			if (fClass.isAssignableFrom(DoubleProperty.class)) {
				DoubleProperty dp = null;
				try {
					dp = (DoubleProperty) f.get(parent);
					HBox propHBox = new HBox();
					propHBox.getChildren().addAll(new Label(fParamName), makeDoubleSpinner(dp));
					properties.add(propHBox);
					return properties;
				} catch (Exception e) {
					
				}
				
			} else if (fClass.isAssignableFrom(IntegerProperty.class)) {
				IntegerProperty ip = null;
				try {
					ip = (IntegerProperty) f.get(parent);
					HBox propHBox = new HBox();
					propHBox.getChildren().addAll(new Label(fParamName), makeIntegerSpinner(ip));
					properties.add(propHBox);
					return properties;
				} catch (Exception e) {
					
				}
				// try {
				// ip = (IntegerProperty) fClass.newInstance();
				// } catch (InstantiationException | IllegalAccessException e) {
				// e.printStackTrace();
				// }

				
			} else if (fClass.isAssignableFrom(BooleanProperty.class)) {
//				BooleanProperty bp = null;
//				try {
//					bp = (BooleanProperty) f.get(parent);
//				} catch (IllegalArgumentException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				// try {
//				// bp = (BooleanProperty) fClass.newInstance();
//				// } catch (InstantiationException | IllegalAccessException e) {
//				// e.printStackTrace();
//				// }
//				HBox propHBox = new HBox();
//				propHBox.getChildren().addAll(new Label(fParamName), makeBooleanCheckbox(bp));
//				properties.add(propHBox);
				return properties;
			} else if (fClass.isAssignableFrom(String.class)) {
				System.out.println("HI IM A STRING");
				// THIS PROBABLY REFERS TO IMAGE FILES..............
				return properties;
			} else {
				// IT'S A JAVA CLASS
				// it's a list
				if (fClass.isAssignableFrom(List.class)) {
					Type pType = ((ParameterizedType) fType).getActualTypeArguments()[0];
					String pName = pType.getTypeName();
					System.out.println(pType + " \n" + pName);
					List thisList = null;
					try {
						thisList = (List) f.get(parent);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(Object p: thisList){
//						properties.addAll(makePropertyBoxes, thisList, properties));

					}
					return properties;
					// ParameterizedType pt = (ParameterizedType)
					// f.getGenericType();
					// Type paramType = pt.getActualTypeArguments()[0];
					// Field listField = new Field();
					// System.out.println(paramType.getTypeName());
				} else if (fClass.isAssignableFrom(Map.class)) {
					System.out.println("Map");
				} else {
					System.out.println("FOOOOOOOOOOOOOOOOOOK");
				}

				// it's a map
				return properties;
			}

		} else {
			// recurse
			System.out.println("RECURSE " + fClass.getName());
			Field[] fields = fClass.getDeclaredFields();

			for (Field field : fields) {
				System.out.println(field.getName());
				properties.addAll(makePropertyBoxes(field, f, properties));
			}
			System.out.println("done with recurse");
			return properties;
		}
		return properties;
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

	private Spinner oneSpinner(Field p, Sprite mySprite) {
		p.setAccessible(true);
		Spinner mySpinner = new Spinner();
		SpinnerValueFactory factory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 0);
		mySpinner.setValueFactory(factory);
		mySpinner.setEditable(true);

		// p.getType().asSubclass(Property.class)
		// System.out.println("----" + p.getType().toGenericString());

		// try {
		// Property thisproperty = (Property) p.get(Object);
		// factory.valueProperty().bindBidirectional(thisproperty);
		// } catch (Exception e){
		//// e.printStackTrace();
		// }

		return mySpinner;

	}

	// Constructor<?>[] fieldConstructors = f.getType().getConstructors();
	// // sorting comparator
	// Comparator<Constructor> byParamNumber=
	// (Constructor c1, Constructor c2) -> c1.getParameterCount() >=
	// c2.getParameterCount() ? 1:-1;
	// Arrays.sort(fieldConstructors, byParamNumber);
	// System.out.println(f.getName());
	// System.out.println(f.getGenericType());

	// finding constructors and their params
	// for(Constructor c : fieldConstructors) {
	// System.out.println(c.getParameterCount());
	// Class<?>[] paramClasses = c.getParameterTypes();
	// for(Class<?> param : paramClasses) {
	// System.out.println(param.getName());
	// }
	// }

	// DoubleProperty, BooleanProperty, Integer
	// System.out.println(fieldConstructors[0].getParameterCount());
	// System.out.println(fieldConstructors[fieldConstructors.length-1].getParameterCount());
	// Arrays.sort(fieldConstructors, (Constructor c1, Constructor c2) -> {
	// return a.getParameterCount() > b.getParameterCount();
	// });

	// properties.addAll(Arrays.asList(type.getDeclaredFields()));

	// if (type.getSuperclass() != null) {
	// fields = getAllFields(fields, type.getSuperclass());
	// }

	// return properties;

}
