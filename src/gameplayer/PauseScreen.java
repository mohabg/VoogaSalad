package gameplayer;

import java.io.File;
import java.util.List;

import authoringEnvironment.LevelModel;
import authoringEnvironment.Settings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * IScreen that is displayed when the game is paused. Has buttons for
 * navigation.
 * 
 * @author Huijia
 *
 */
public class PauseScreen extends Screen {

	private GameLoader myGameLoader;
	private final String CONTINUE_GAME = "Continue Game";
	private final String RESTART_GAME = "Restart Game";
	private final String SWITCH_GAME = "Switch Games";
	private final String SAVE_GAME = "Save Current Game";
	private final String CHANGE_SETTINGS = "Edit Game Settings";
	private final String BACK = "Back to Start";


	public PauseScreen(Screen parent) {
		super(parent);
		myGameLoader = new GameLoader();
	}

	public void initBorderPane(List<LevelModel> gameLevels) {
		VBox buttons = makePauseScreenButtons(gameLevels);
		Settings.setStartWindowSettings(buttons);
		myPane.getChildren().add(buttons);
	}

	private VBox makePauseScreenButtons(List<LevelModel> gameLevels) {
		VBox box = new VBox();

		Button cont = ButtonFactory.makeButton(CONTINUE_GAME, a -> {
			switchScene(parentScreen);
		});

		Button restart = ButtonFactory.makeButton(RESTART_GAME, a -> {
			File currGameFile = ((PlayScreen) parentScreen).getGameFile();
			switchScene(myGameLoader.newGame(currGameFile));

		});

		Button switchgame = ButtonFactory.makeButton(SWITCH_GAME, a -> {
			switchScene(new GamePlayingFileScreen());
		});

		Button save = ButtonFactory.makeButton(SAVE_GAME, a -> {
			myGameLoader.saveGame(gameLevels);
		});

		Button settings = ButtonFactory.makeButton(CHANGE_SETTINGS, a -> {
			switchScene(new SettingsScreen());
		});
		
		Button back = ButtonFactory.makeButton(BACK, a-> {returnToStart();});

		box.getChildren().addAll(cont, restart, switchgame, save, settings, back);

		return box;
	}

}
