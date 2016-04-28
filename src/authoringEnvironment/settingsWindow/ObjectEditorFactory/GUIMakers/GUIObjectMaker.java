package authoringEnvironment.settingsWindow.ObjectEditorFactory.GUIMakers;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.ObjectEditorConstants;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.StylesheetType;
import javafx.css.Styleable;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GUIObjectMaker {
	
	public static TabPane makeStyledTabPane() {
		TabPane tabPane = new TabPane();
		tabPane = (TabPane) applyStylesheet(StylesheetType.TABPANE, tabPane);
		return tabPane;
	}
	
	public static TabPane addTabPaneOptions(TabPane tabPane) {
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		return tabPane;
	}
	
	public static Tab makeStyledTab(String name) {
		Tab tab = new Tab(name);
		tab = (Tab) applyStylesheet(StylesheetType.TAB, tab);
		return tab;
	}
	
	public static Button makeStyledButton(String name, EventHandler<? super MouseEvent> action) {
		Button button = new Button(name);
		button.setOnMouseClicked(action);
		button = (Button) applyStylesheet(StylesheetType.BUTTON, button);
		return button;
	}
	
	public static HBox makeStyledHBox(Node...node) {
		HBox hBox = new HBox(node);
		hBox = (HBox) applyStylesheet(StylesheetType.HBOX, hBox);
		return hBox;
	}
	
	public static HBox addHBoxOptions(HBox hBox) {
		Insets insets = new Insets(20, 20, 20, 20);
		hBox.setPadding(insets);
		return hBox;
	}
	
	public static VBox makeStyledVBox(Node...node) {
		VBox vBox = new VBox(node);
		vBox = (VBox) applyStylesheet(StylesheetType.VBOX, vBox);
		return vBox;
	}
	
	public static VBox addVBoxOptions(VBox vBox) {
		Insets insets = new Insets(20, 20, 20, 20);
		vBox.setPadding(insets);
		return vBox;
	}
	
	public static ComboBox makeStyledComboBox() {
		ComboBox comboBox = new ComboBox();
		comboBox = (ComboBox) applyStylesheet(StylesheetType.COMBOBOX, comboBox);
		return comboBox;
	}

	
	public static ScrollPane makeStyledScrollPane() {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane = (ScrollPane) applyStylesheet(StylesheetType.ANCHORPANE, scrollPane);
		return scrollPane;
	}
	
	public static ScrollPane addScrollPaneOptions(ScrollPane scrollPane) {
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		return scrollPane;
	}
	
	public static AnchorPane makeStyledAnchorPane() {
		AnchorPane anchorPane = new AnchorPane();
		anchorPane = (AnchorPane) applyStylesheet(StylesheetType.ANCHORPANE, anchorPane);
		return anchorPane;
	}
	
	public static Spinner makeStyledSpinner() {
		Spinner spinner = new Spinner();
		spinner = (Spinner) applyStylesheet(StylesheetType.SPINNER, spinner);
		return spinner;
	}
	
	public static Spinner addSpinnerOptions(Spinner spinner) {
		spinner.setEditable(true);
		return spinner;
	}
	
	public static TextField makeStyledTextField(String name) {
		TextField textField = new TextField(name);
		textField = (TextField) applyStylesheet(StylesheetType.TEXTFIELD, textField);
		return textField;
	}
	
	public static TextField addTextFieldOptions(TextField textField) {
		VBox.setVgrow(textField, Priority.ALWAYS);
		HBox.setHgrow(textField, Priority.ALWAYS);
		return textField;
	}
	
	public static CheckBox makeStyledCheckBox() {
		CheckBox checkBox = new CheckBox();
		checkBox = (CheckBox) applyStylesheet(StylesheetType.CHECKBOX, checkBox);
		return checkBox;
	}
	
	public static CheckBox addCheckBoxOptions(CheckBox checkBox) {
		checkBox.setIndeterminate(false);
		return checkBox;
	}
	
	public static Label makeStyledLabel(String name) {
		Label label = new Label(name);
		label = (Label) applyStylesheet(StylesheetType.LABEL, label);
		return label;
	}
	
	public static Label addLabelOptions(Label label) {
		label.setAlignment(Pos.CENTER);
		return label;
	}
	
	private static Styleable applyStylesheet(StylesheetType type, Styleable node) {
		ObjectEditorConstants constants = ObjectEditorConstants.getInstance();
		String stylesheet = constants.getStylesheet(type);
		if (stylesheet != "") {
			if (node instanceof Parent) {
				((Parent) node).getStylesheets().add(stylesheet);
			} else {
				node.getStyleClass().add(stylesheet);
			}
		}
		return node;
	}
}
