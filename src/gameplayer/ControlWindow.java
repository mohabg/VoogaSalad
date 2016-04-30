package gameplayer;

import authoringEnvironment.Settings;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.File;

public class ControlWindow{
	private PlayScreen myPlayScreen;
	private FlowPane myFlowPane;
	
	public ControlWindow(PlayScreen myPlayScreen) {
		this.myPlayScreen =  myPlayScreen;
		myFlowPane = new FlowPane();
		myFlowPane.setId("myFlowPane");
		Settings.setControlWindowSettings(myFlowPane);
		myFlowPane.getStylesheets().add("gameplayer/controlWindow.css");
		createFlowPane();
	}
	
	private void createFlowPane(){
		Button pauseButton = new Button("Pause");
		pauseButton.setOnAction(e -> {
			myPlayScreen.getEngine().pauseGameLoop();
		});
		Button playButton = new Button("Play");
		playButton.setOnAction(e -> {
			myPlayScreen.getEngine().playGameLoop();
		});
		Button restartButton = new Button("Restart");
		restartButton.setOnAction(e -> {
			File currGameFile = myPlayScreen.getGameFile();
//			myPlayScreen = (PlayScreen) GameLoader.newGame(currGameFile);
		});
		myFlowPane.getChildren().addAll(pauseButton, playButton, restartButton);
	}
	
	public Pane getPane(){
		return myFlowPane;
	}
	

//	public void initBorderPane(Collection<Level> gameLevels) {
//		VBox buttons = makePauseScreenButtons(gameLevels);
//		Settings.setStartWindowSettings(buttons);
//	}
//
//	private VBox makePauseScreenButtons(Collection<Level> gameLevels) {
//		
//		
//		
//		VBox box = new VBox();
//
//		Button cont = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("continuegame"), a -> {
//			switchScene(parentScreen);
//			((PlayScreen) parentScreen).play();
//		});
//
//		Button restart = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("restartgame"), a -> {
//			File currGameFile = ((PlayScreen) parentScreen).getGameFile();
//			switchScene(GameLoader.newGame(currGameFile));
//
//		});
//
//		Button switchgame = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("switchgame"), a -> {
//			switchScene(new GamePlayingFileScreen());
//		});
//
//		Button save = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("savegame"), a -> {
//			File currGameFile = ((PlayScreen) parentScreen).getGameFile();
//			GameLoader.savePlayedGame( currGameFile.getName().replace(".xml", ""), gameLevels);
//		});
//
//		Button settings = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("changesettings"), a -> {
//			switchScene(new SettingsScreen());
//		});
//
//		Button back = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("backtostart"), a -> {
//			returnToStart();
//		});
//
//		box.getChildren().addAll(cont, restart, switchgame, save, settings, back);
//
//		return box;
	
}
