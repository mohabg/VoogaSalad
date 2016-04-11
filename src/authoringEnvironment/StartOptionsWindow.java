package authoringEnvironment;

import java.io.File;

import gameplayer.ButtonFactory;
import gameplayer.GameEditingFileScreen;
import gameplayer.GameFileScreen;
import gameplayer.GameLoader;
import gameplayer.GamePlayingFileScreen;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartOptionsWindow implements IScreen {

	private Stage myStage;
	private BorderPane myPane;
	private VBox startWindowBox;
	private Settings settings;
	private IScreen parentScreen;

	private final String CREATE_GAME = "Create A New Game";
	private final String EDIT_GAME = "Edit An Existing Game";
	private final String PLAY_GAME = "Play a Game";

	private final String DEFAULT_IMAGE = "pictures/gaming.png";

	public StartOptionsWindow(Stage currStage) {
		myStage = currStage;
		myPane = new BorderPane();
		startWindowBox = new VBox();
		settings = new Settings();

		settings.setStartWindowSettings(startWindowBox);

		makeAndSetStartBox();
	}

	private void makeAndSetStartBox() {
		// TODO CHANGE THIS TO USE REFLECTION
		ImageView myLogo = new ImageView(DEFAULT_IMAGE);

		Button createNewButton = ButtonFactory.makeButton(CREATE_GAME, (e -> {
			switchScene(new MainAuthoringWindow());
			myStage.centerOnScreen();
		}));

		Button editButton = ButtonFactory.makeButton(EDIT_GAME, (e -> {

			switchScene(new GameEditingFileScreen());
		}));

		Button playButton = ButtonFactory.makeButton(PLAY_GAME, (e -> {
			switchScene(new GamePlayingFileScreen());
		}));

		startWindowBox.getChildren().addAll(myLogo, createNewButton, editButton, playButton);
		myPane.setCenter(startWindowBox);
	}

	public void switchScene(IScreen screen) {
		// ((Stage) myPane.getScene().getWindow()).setScene(screen.getScene());
		myStage.setScene(screen.getScene());
		myStage.show();
	}

	public void setParentScreen(IScreen screen) {
		parentScreen = screen;
	}

	public Scene getScene() {
		return new Scene(myPane, myPane.getPrefWidth(), myPane.getPrefHeight());
	}

}
