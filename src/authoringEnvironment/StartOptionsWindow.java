package authoringEnvironment;

import gameplayer.ButtonFactory;
import gameplayer.GameWindow;
import gameplayer.IScreen;
/**
 * @author davidyan
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartOptionsWindow implements IScreen{

	private Stage myStage;
	private BorderPane myPane;
	private VBox startWindowBox;

	public StartOptionsWindow(Stage useStage) {
		myStage = useStage;
		myPane = new BorderPane();
		startWindowBox = new VBox();
		Settings settings = new Settings();
		settings.setStartWindowSettings(startWindowBox);
		
		makeAndSetStartBox();
	}

	private void makeAndSetStartBox() {
		// TODO CHANGE THIS TO USE REFLECTION
		ImageView myLogo = new ImageView("pictures/gaming.png");
		Button createNewButton = createNewButton();
		Button editButton = createEditButton();
		Button playButton = createPlayButton();
		startWindowBox.getChildren().addAll(myLogo, createNewButton, editButton, playButton);
		myPane.setCenter(startWindowBox);
	}
	
	public Button createPlayButton() {
		// TODO MOVE TO RESOURCES FILE
		Button playButton = ButtonFactory.makeButton("Play a Game", (e -> {
			switchScene(new GameWindow());
		}));
		return playButton;
	}

	public Button createEditButton() {
		// TODO MOVE TO RESOURCES FILE
		Button editButton = ButtonFactory.makeButton("Edit An Existing Game", (e -> {
			System.out.println("EDIT");
		}));
		return editButton;
	}

	public Button createNewButton() {
		// TODO MOVE TO RESOURCES FILE
		Button newButton = ButtonFactory.makeButton("Create A New Game", (e -> {
			switchScene(new MainAuthoringWindow());
			// TODO might want to remove line below
			myStage.centerOnScreen();
		}));
		return newButton;
	}
	
	private void switchScene(IScreen screen) {
		myStage.setScene(screen.getScene());
		myStage.show();
	}

	public Scene getScene() {
		return new Scene(myPane, myPane.getPrefWidth(), myPane.getPrefHeight());
	}

}
