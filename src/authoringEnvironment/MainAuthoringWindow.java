package authoringEnvironment;

import authoringEnvironment.authoringToolbar.AbstractMenuBar;
/**
 * @author David Yan, Joe Jacob, Huijia Yu
 */
import authoringEnvironment.authoringToolbar.AuthoringMenubarCreator;
import authoringEnvironment.authoringToolbar.LEMenuBarCreator;
import authoringEnvironment.itemWindow.ItemWindow;
import authoringEnvironment.mainWindow.GameMakerWindow;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameplayer.PlayScreen;
import gameplayer.Screen;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.io.File;

public class MainAuthoringWindow extends Screen {
	private ItemWindow myItemWindow;
//	private AbstractMenuBar myMenubar;
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

		
		AuthoringMenubarCreator myMenubar = new AuthoringMenubarCreator(gameName);
		myMenubar.initMenuBar(this, myGameMakerWindow);
		myGameMakerWindow.init(mySettingsWindow);
		
//		((BorderPane) myPane).setCenter(le.getMyNewGamePane());
		((BorderPane) myPane).setCenter(myGameMakerWindow.getTabPane());
		((BorderPane) myPane).setLeft(myItemWindow.getTabPane());
		((BorderPane) myPane).setTop(myMenubar.getMenuBar());
		((BorderPane) myPane).setRight(mySettingsWindow.getBox());
		// mySettingsWindow.setContent();	
	}
	
	public MainAuthoringWindow(Screen parent, String gameName, PlayScreen myPlayScreen) {
		super(parent);
		myPane = new BorderPane();

		mySettingsWindow = new SettingsWindow();
		myPane.setStyle("-fx-background-color: #121b3e;");

		myGameMakerWindow = new GameMakerWindow();
		
//		String PATH = "/Users/Huijia/Documents/workspace/voogasalad_TheDuballers/SavedGameData/SavedGames/a.xml";
		le = new Project1(myPlayScreen, mySettingsWindow);
		
		myItemWindow = new ItemWindow(le);
	
		LEMenuBarCreator myMenubar = new LEMenuBarCreator(gameName);
		myMenubar.initMenuBar(this, le);
		
		((BorderPane) myPane).setCenter(le.getMyNewGamePane());
		((BorderPane) myPane).setLeft(myItemWindow.getTabPane());
		((BorderPane) myPane).setTop(myMenubar.getMenuBar());
		((BorderPane) myPane).setRight(mySettingsWindow.getBox());
	}

	public GameMakerWindow getGameMakerWindow() {
		return myGameMakerWindow;
	}

}