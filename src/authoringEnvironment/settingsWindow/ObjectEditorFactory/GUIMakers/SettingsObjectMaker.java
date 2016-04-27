package authoringEnvironment.settingsWindow.ObjectEditorFactory.GUIMakers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SettingsObjectMaker {
	
	public static VBox makeSettingsObject(Object myProp, String propName) {
		VBox propVBox = new VBox();
        propVBox.setPadding(new Insets(20,20,20,20));
        String labelText = convertCamelCase(propName);
		Label propLabelName = new Label(labelText);

        propLabelName.getStylesheets().add("authoringEnvironment/itemWindow/styles.css");
        propLabelName.setAlignment(Pos.CENTER);

        if (myProp instanceof DoubleProperty) {
			DoubleProperty dp = (DoubleProperty) myProp;
			propVBox.getChildren().addAll(propLabelName, makeDoubleSpinner(dp));
		} else if (myProp instanceof IntegerProperty) {
			IntegerProperty ip = (IntegerProperty) myProp;
			propVBox.getChildren().addAll(propLabelName, makeIntegerSpinner(ip));
		} else if (myProp instanceof BooleanProperty) {
			BooleanProperty bp = (BooleanProperty) myProp;
			propVBox.getChildren().addAll(propLabelName, makeBooleanCheckbox(bp));
		} else if (myProp instanceof StringProperty) {
			// THIS PROBABLY REFERS TO IMAGE FILES..............
			// DROP DOWN OF IMAGE FILES TO CHOOSE FROM
			StringProperty sp = (StringProperty) myProp;
			propVBox.getChildren().addAll(propLabelName, makeTextField(sp));
		} else if (myProp instanceof ListProperty) {
			ListProperty lp = (ListProperty) myProp;
			//propHBox.getChildren().addAll(propLabelName, makeTableView(lp));
		} else if (myProp instanceof MapProperty) {
			MapProperty mp = (MapProperty) myProp;
			//propHBox.getChildren().addAll(propLabelName, makeTableView(mp));
		} else if (myProp instanceof Enum<?>) {
		
		}
		return propVBox;
	}
	
	public static String convertCamelCase(String camelCaseString) {
		if (camelCaseString.indexOf(".") != -1) {
			camelCaseString = camelCaseString.substring(camelCaseString.lastIndexOf("."));
		}
		String[] words = camelCaseString.split("(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])");
		String converted = "";

		for (String word : words) {
			String convertedWord = Character.toUpperCase(word.charAt(0)) + word.substring(1);
			converted = converted + convertedWord + " ";
		}

		return converted;
	}
	
	public static Spinner makeDoubleSpinner(DoubleProperty dp) {
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

	public static Spinner makeIntegerSpinner(IntegerProperty ip) {
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

	public static CheckBox makeBooleanCheckbox(BooleanProperty bp) {
		CheckBox cb = new CheckBox("True/False");
		cb.setIndeterminate(false);

		cb.selectedProperty().bindBidirectional(bp);

		return cb;
	}


	public static TextField makeTextField(StringProperty sp) {
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
