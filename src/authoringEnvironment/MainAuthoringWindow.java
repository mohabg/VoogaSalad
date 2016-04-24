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

public class MainAuthoringWindow extends Screen {
	private ItemWindow myItemWindow;
	private AuthoringMenubarCreator myMenubar;
	private GameMakerWindow myGameMakerWindow;
	private SettingsWindow mySettingsWindow;

	public MainAuthoringWindow(Screen parent, String gameName) {
		super(parent);
		myPane = new BorderPane();
		myPane.setStyle("-fx-background-color: #121b3e;");

		myGameMakerWindow = new GameMakerWindow();
		myItemWindow = new ItemWindow(myGameMakerWindow);
		myMenubar = new AuthoringMenubarCreator(gameName);
		myMenubar.initMenuBar(this, myGameMakerWindow);
		mySettingsWindow = new SettingsWindow();
		// myItemWindow.init();
		myGameMakerWindow.init(mySettingsWindow);
		
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