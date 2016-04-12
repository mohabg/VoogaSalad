package gameplayer;

import java.io.File;

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

public class PauseScreen implements IScreen {
	private Pane myPane;
	private Scene myScene;
	private IScreen parentScreen;
	
	private GameLoader myGameLoader;
	private final String CONTINUE_GAME = "Continue Game";
	private final String RESTART_GAME = "Restart Game";
	private final String SWITCH_GAME = "Switch Games";
	private final String SAVE_GAME = "Save Current Game";
	private final String CHANGE_SETTINGS = "Edit Game Settings";
	
	
	
	public PauseScreen() {
		myPane = new Pane();
		Settings.setGamePlayingSettings(myPane);

		myScene = new Scene(myPane);
		myGameLoader = new GameLoader();
		initBorderPane();
	}
	
	private void initBorderPane() {
		VBox buttons = makePauseScreenButtons();
		Settings.setStartWindowSettings(buttons);
		myPane.getChildren().add(buttons);
	}
	
	private VBox makePauseScreenButtons() {
		VBox box = new VBox();
		
		Button cont = ButtonFactory.makeButton(CONTINUE_GAME, a -> {
			switchScene(parentScreen);
		});
		
		Button restart = ButtonFactory.makeButton(RESTART_GAME, a -> {
			File currGameFile = ((PlayScreen) parentScreen).getGameFile();
			IScreen playScreen = myGameLoader.restartGame(currGameFile);
			switchScene(playScreen);
			
		});
		
		Button switchgame = ButtonFactory.makeButton(SWITCH_GAME, a -> {
			switchScene(new GamePlayingFileScreen());
		});
		
		Button save = ButtonFactory.makeButton(SAVE_GAME, a -> {
//			myGameLoader.saveGame();
		});
		
		Button settings = ButtonFactory.makeButton(CHANGE_SETTINGS, a -> {
			switchScene(new SettingsScreen());
		});

		box.getChildren().addAll(cont, restart, switchgame, save, settings);
		
		return box;
	}

	@Override
	public Scene getScene() {
		return myPane.getScene();
		//return new Scene(myPane, myPane.getPrefWidth(), myPane.getPrefHeight());
	}

	@Override
	public void switchScene(IScreen screen) {
		((Stage) myPane.getScene().getWindow()).setScene(screen.getScene());	
	}

	@Override
	public void setParentScreen(IScreen screen) {
		parentScreen = screen;	
	}

}
