package authoringEnvironment;

import gameplayer.ButtonFactory;
import gameplayer.GameEditingFileScreen;
import gameplayer.GamePlayingFileScreen;
import gameplayer.Screen;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Huijia Yu, Joe Jacob
 */

public class StartOptionsWindow extends Screen {

	private Stage myStage;
	private VBox startWindowBox;

	private final String CREATE_GAME = "Create A New Game";
	private final String EDIT_GAME = "Edit An Existing Game";
	private final String PLAY_GAME = "Play a Game";

	private final String DEFAULT_IMAGE = "pictures/gaming.png";

	public StartOptionsWindow(Stage currStage) {
		super();
		myStage = currStage;
		myPane = new BorderPane();
		startWindowBox = new VBox();
		Settings.setStartWindowSettings(startWindowBox);

		makeAndSetStartBox();
	}

	private void makeAndSetStartBox() {
		// TODO CHANGE THIS TO USE REFLECTION
		ImageView myLogo = new ImageView(DEFAULT_IMAGE);

		Button createNewButton = ButtonFactory.makeButton(CREATE_GAME, (e -> {
			switchScene(new MainAuthoringWindow(this));
			myStage.centerOnScreen();
		}));

		Button editButton = ButtonFactory.makeButton(EDIT_GAME, (e -> {

			switchScene((Screen) new GameEditingFileScreen());
		}));

		Button playButton = ButtonFactory.makeButton(PLAY_GAME, (e -> {
			switchScene((Screen) new GamePlayingFileScreen());
		}));

		startWindowBox.getChildren().addAll(myLogo, createNewButton, editButton, playButton);
		((BorderPane) myPane).setCenter(startWindowBox);
	}

}
