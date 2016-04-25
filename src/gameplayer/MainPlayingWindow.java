package gameplayer;

import javafx.scene.layout.BorderPane;

public class MainPlayingWindow extends Screen{
	private ControlWindow myControlWindow;
	private PlayScreen myPlayScreen;
	private HUDWindow myHUDWindow;
	
	public MainPlayingWindow(Screen parent, String gameName){
		super(parent);
		myPane = new BorderPane();
		
		myPlayScreen = (PlayScreen) GameLoader.newGame(gameName);
		myControlWindow = new ControlWindow(myPlayScreen);
		myHUDWindow = new HUDWindow(myPlayScreen);
		
		((BorderPane) myPane).setCenter(myPlayScreen.getPane());
		((BorderPane) myPane).setLeft(myControlWindow.getPane());
		((BorderPane) myPane).setRight(myHUDWindow.getPane());
	}
}
