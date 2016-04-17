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
import resources.FrontEndData;

/**
 * IScreen that is displayed when the game is paused. Has buttons for
 * navigation.
 * 
 * @author Huijia
 *
 */
public class PauseScreen extends Screen {

	private GameLoader myGameLoader;

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

		Button cont = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("continuegame"), a -> {
			switchScene(parentScreen);
		});

		Button restart = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("restartgame"), a -> {
			File currGameFile = ((PlayScreen) parentScreen).getGameFile();
			switchScene(myGameLoader.newGame(currGameFile));

		});

		Button switchgame = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("switchgame"), a -> {
			switchScene(new GamePlayingFileScreen());
		});

		Button save = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("savegame"), a -> {
			myGameLoader.saveGame(gameLevels);
		});

		Button settings = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("changesettings"), a -> {
			switchScene(new SettingsScreen());
		});

		Button back = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("backtostart"), a -> {
			returnToStart();
		});

		box.getChildren().addAll(cont, restart, switchgame, save, settings, back);

		return box;
	}

}
