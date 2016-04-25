package authoringEnvironment;

/**
 * @author David Yan, Joe Jacob, Huijia Yu
 */
import authoringEnvironment.authoringToolbar.AuthoringMenubarCreator;
import authoringEnvironment.itemWindow.ItemWindow;
import authoringEnvironment.mainWindow.GameMakerWindow;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameplayer.Screen;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.io.File;

public class MainAuthoringWindow extends Screen {
	private ItemWindow myItemWindow;
	private AuthoringMenubarCreator myMenubar;
	private GameMakerWindow myGameMakerWindow;
	private SettingsWindow mySettingsWindow;
	Project1 le;

	public MainAuthoringWindow(Screen parent, String gameName) {
		super(parent);
		myPane = new BorderPane();

		mySettingsWindow = new SettingsWindow();
		myPane.setStyle("-fx-background-color: #121b3e;");

		myGameMakerWindow = new GameMakerWindow();
		
//		String PATH = "/Users/Huijia/Documents/workspace/voogasalad_TheDuballers/SavedGameData/SavedGames/a.xml";
//		le = new Project1(new File(PATH), mySettingsWindow);
		
		myItemWindow = new ItemWindow(myGameMakerWindow);
//		myItemWindow = new ItemWindow(le);

		
		myMenubar = new AuthoringMenubarCreator(gameName);
		myMenubar.initMenuBar(this, myGameMakerWindow);
		myGameMakerWindow.init(mySettingsWindow);
		
//		((BorderPane) myPane).setCenter(le.getMyNewGamePane());
		((BorderPane) myPane).setCenter(myGameMakerWindow.getTabPane());
		((BorderPane) myPane).setLeft(myItemWindow.getTabPane());
		((BorderPane) myPane).setTop(myMenubar.getMenuBar());
		((BorderPane) myPane).setRight(mySettingsWindow.getBox());
		// mySettingsWindow.setContent();
		
		
	}

	public GameMakerWindow getGameMakerWindow() {
		return myGameMakerWindow;
	}

}