package gameplayer;

import authoringEnvironment.MainAuthoringWindow;
import authoringEnvironment.authoringToolbar.AuthoringMenubarCreator;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MainPlayingWindow extends Screen{
	private ControlWindow myControlWindow;
	private PlayScreen myPlayScreen;
	private HUDWindow myHUDWindow;
	private Button toggleButton;
	
	public MainPlayingWindow(Screen parent, String gameName){
		super(parent);
		myPane = new BorderPane();
		
		myPlayScreen = (PlayScreen) GameLoader.newGame(gameName);
		myControlWindow = new ControlWindow(myPlayScreen);
		myHUDWindow = new HUDWindow(myPlayScreen);
		toggleButton = new Button("Toggle");
		toggleButton.setOnAction(e -> {
			MainAuthoringWindow myMainAuthoringWindow = new MainAuthoringWindow(this, gameName, myPlayScreen);
    		switchScene(myMainAuthoringWindow);
		});
		
		((BorderPane) myPane).setCenter(myPlayScreen.getPane());
		((BorderPane) myPane).setLeft(myControlWindow.getPane());
		((BorderPane) myPane).setRight(myHUDWindow.getPane());
		((BorderPane) myPane).setTop(toggleButton);
	}
}
