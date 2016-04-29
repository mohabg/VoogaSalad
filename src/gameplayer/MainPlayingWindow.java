package gameplayer;

import java.util.HashMap;

import authoringEnvironment.MainAuthoringWindow;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import resources.FrontEndData;
import voogasalad.util.hud.source.HUDScreen;
import voogasalad.util.hud.source.Property;

public class MainPlayingWindow extends Screen {
	
	private ControlWindow myControlWindow;
	private PlayScreen myPlayScreen;
	private HUDWindow myHUDWindow;
	private Button toggleButton;
	private String gameName;
	private HUDScreen myHUDScreen;
	private HashMap<String, Property> status;

	public MainPlayingWindow(Screen parent, String gameName, PlayScreen playScreen) {
		super(parent);
		myPlayScreen = playScreen;
		this.gameName = gameName;
		
		status = new HashMap<String, Property>();
		init();
	}

	public MainPlayingWindow(Screen parent, String gameName) {
		this(parent, gameName, new PlayScreen(gameName));
	}

	private void init() {
		myPane = new BorderPane();
		myControlWindow = new ControlWindow(myPlayScreen);
		myHUDWindow = new HUDWindow(myPlayScreen);
		toggleButton = new Button(FrontEndData.ButtonLabels.getString("Toggle"));
		toggleButton.setOnAction(e -> {
			MainAuthoringWindow myMainAuthoringWindow = new MainAuthoringWindow(this, gameName, myPlayScreen);
			switchScene(myMainAuthoringWindow);
		});
		status.put("Health", myPlayScreen.getCurrentLevel().getLevelProperties().getUserControlledSprite().getMyHealth().getHealthProperty());
		myHUDScreen = new HUDScreen(100, 50, status);
        createBorderPane();
        
	}

    private void createBorderPane() {
        ((BorderPane) myPane).setCenter(myPlayScreen.getScreen().getPane());
        ((BorderPane) myPane).setLeft(myControlWindow.getPane());
        ((BorderPane) myPane).setRight(myHUDScreen.getScene());
        ((BorderPane) myPane).setTop(toggleButton);
    }
}
