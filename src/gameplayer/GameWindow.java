package gameplayer;

public class GameWindow {
	Game myGame;
	GameView myGameView;
	GameLoader myLoader;
	ButtonFactory myFactory;
	IScreen myPauseScreen;
	IScreen myPlayScreen;
	IScreen mySettingsScreen;

	public GameWindow(Game game) {
		myGame = game;
		myPlayScreen = new PlayScreen(myGameView);
		myPauseScreen = new PauseScreen(myFactory.makePauseScreenButtons());
		mySettingsScreen = new SettingsScreen();
	}

	public void setPlayWindow() {
		myGame.start();
		myPlayScreen.setStage();
	}

	public void setPauseWindow() {
		myGame.pause();
		myPauseScreen.setStage();

	}

	public void setSettingsWindow() {
		myGame.pause();
		mySettingsScreen.setStage();
	}

	public void setNewPlayWindow() {
		// TODO Auto-generated method stub

	}

}
