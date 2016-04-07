package gameplayer;

import java.awt.Toolkit;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameWindow {
	//	Game myGame;
	//	GameView myGameView;
	//	GameLoader myLoader;
	Pane myPane;
	ButtonFactory myFactory;
	Screen myPlayScreen;
	Screen myPauseScreen;
	GameFileScreen myGameFileScreen;
	Screen mySettingsScreen;

	private static final int myScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int myScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

	public GameWindow() {

		//		Game game
		//		myGame = game;
		myPane = new Pane();
		myFactory = new ButtonFactory(this);
		myPlayScreen =  new PlayScreen(); 
		myPauseScreen =  new PauseScreen();
		myGameFileScreen = new GameFileScreen();
		mySettingsScreen = new SettingsScreen();

		myPlayScreen.add(myFactory.makePause());
		myPauseScreen.add(myFactory.makePauseScreenButtons());

		setGames();
		if(myGameFileScreen.getGameFile()!= null){
			//initialize game
		}

	}
	
	public void setGames(){
		myPane.getChildren().clear();
		myPane.getChildren().add(myGameFileScreen.getPane());
	}

	public void setPlay() {
		//		myGame.start();
		myPane.getChildren().clear();
		myPane.getChildren().add(myPlayScreen.getPane());
	}

	public void setPause() {
		//		myGame.pause();
		myPane.getChildren().clear();
		myPane.getChildren().add(myPauseScreen.getPane());

	}

	public void setSettings() {
		myPane.getChildren().clear();
		myPane.getChildren().add(mySettingsScreen.getPane());
	}


	public Scene getScene(){
		Scene myRetScene = new Scene(myPane, myScreenWidth, myScreenHeight);
		myRetScene.getStylesheets().add("resources/styles.css");
		return myRetScene;
	}

	public void setRestart() {
		//HOW DO I DO THIS--reflection? probably will ask Loader to run again for last 
		myGameFileScreen.getGameFile();
		//new Game from file
	}


}
