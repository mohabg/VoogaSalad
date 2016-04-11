package authoringEnvironment;

import java.io.File;

import gameplayer.GameWindow;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import loading.GameLoader;

public class StartOptionsWindow {

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
		Button playButton = new Button("Play A Game");
		playButton.setOnAction(e -> {
			GameWindow myGameWindow = new GameWindow();
			myStage.setScene(myGameWindow.getScene());
			myStage.show();
		});
		return playButton;
	}

	public Button createEditButton() {
		// TODO MOVE TO RESOURCES FILE
		Button editButton = new Button("Edit An Existing Game");
		editButton.setOnAction(e -> {
			MainAuthoringWindow myMainAuthoringWindow = new MainAuthoringWindow();

			// MOVE OR SOMERHING
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			File file =fileChooser.showOpenDialog(new Stage());

			GameLoader g = new GameLoader();

			myMainAuthoringWindow.getGameMakerWindow().populateEditingFromSave(g.parseAndLoadGame(file));

			myStage.setScene(myMainAuthoringWindow.getScene());
			myStage.show();
			myStage.centerOnScreen();
		});
		return editButton;
	}

	public Button createNewButton() {
		// TODO MOVE TO RESOURCES FILE
		Button newButton = new Button("Create A New Game");
		newButton.setOnAction(e -> {
			MainAuthoringWindow myMainAuthoringWindow = new MainAuthoringWindow();
			myStage.setScene(myMainAuthoringWindow.getScene());
			myStage.show();
			myStage.centerOnScreen();
		});
		return newButton;
	}

	public Scene getScene() {
		return new Scene(myPane, myPane.getPrefWidth(), myPane.getPrefHeight());
	}

}
