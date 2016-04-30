package authoringEnvironment.settingsWindow.ObjectEditorFactory.GUIMakers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;

public class SettingsObjectMaker {
	
	public static VBox makeSettingsObject(Object myProp, String propName) {
		VBox propVBox = GUIObjectMaker.makeVBox();
        String labelText = convertCamelCase(propName);
		Label propLabelName = GUIObjectMaker.makeLabel(labelText);


        if (myProp instanceof IntegerProperty) {
			IntegerProperty ip = (IntegerProperty) myProp;
			propVBox.getChildren().addAll(propLabelName, makeIntegerSpinner(ip));
		} else if (myProp instanceof DoubleProperty) {
			DoubleProperty dp = (DoubleProperty) myProp;
			propVBox.getChildren().addAll(propLabelName, makeDoubleSpinner(dp));
		} else if (myProp instanceof BooleanProperty) {
			BooleanProperty bp = (BooleanProperty) myProp;
			propVBox.getChildren().addAll(propLabelName, makeBooleanCheckbox(bp));
		} else if (myProp instanceof StringProperty) {
			// THIS PROBABLY REFERS TO IMAGE FILES..............
			// DROP DOWN OF IMAGE FILES TO CHOOSE FROM
			StringProperty sp = (StringProperty) myProp;
			propVBox.getChildren().addAll(propLabelName, makeTextField(sp));
		} 
        
		return propVBox;
	}
	
	public static String convertCamelCase(String camelCaseString) {
		if (camelCaseString.indexOf(".") != -1) {
			camelCaseString = camelCaseString.substring(camelCaseString.lastIndexOf(".") + 1);
		}
		String[] words = camelCaseString.split("(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])");
		String converted = "";

		for (String word : words) {
			String convertedWord = Character.toUpperCase(word.charAt(0)) + word.substring(1);
			converted = converted + convertedWord + " ";
		}

		return converted;
	}
	
	public static Spinner<Double> makeDoubleSpinner(DoubleProperty dp) {
		Spinner<Double> mySpinner = GUIObjectMaker.makeSpinner();
		SpinnerValueFactory<Double> factory = new SpinnerValueFactory.DoubleSpinnerValueFactory(-10000, 10000, 0);
		mySpinner.setValueFactory(factory);
		
		TextFormatter<Double> formatter = new TextFormatter<Double>(factory.getConverter(), factory.getValue());
		mySpinner.getEditor().setTextFormatter(formatter);
		
		factory.valueProperty().bindBidirectional(dp.asObject());
		factory.valueProperty().bindBidirectional(formatter.valueProperty());
		
		return mySpinner;
	}

	public static Spinner<Integer> makeIntegerSpinner(IntegerProperty ip) {
		Spinner<Integer> mySpinner = GUIObjectMaker.makeSpinner();
		SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-10000, 10000, 0);
		mySpinner.setValueFactory(factory);

		TextFormatter<Integer> formatter = new TextFormatter<Integer>(factory.getConverter(), factory.getValue());
		mySpinner.getEditor().setTextFormatter(formatter);
		
		factory.valueProperty().bindBidirectional(ip.asObject());
		factory.valueProperty().bindBidirectional(formatter.valueProperty());

		return mySpinner;
	}

	public static CheckBox makeBooleanCheckbox(BooleanProperty bp) {
		CheckBox cb = GUIObjectMaker.makeCheckBox();

		cb.selectedProperty().bindBidirectional(bp);

		return cb;
	}


	public static TextField makeTextField(StringProperty sp) {
		TextField textField = GUIObjectMaker.makeTextField(sp.toString());
		
		TextFormatter<String> formatter = new TextFormatter<String>(TextFormatter.IDENTITY_STRING_CONVERTER);
		textField.setTextFormatter(formatter);
		
		textField.textProperty().bindBidirectional(sp);
		textField.textProperty().bindBidirectional(formatter.valueProperty());


		return textField;
	}
}
