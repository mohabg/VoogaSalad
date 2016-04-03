package authoringEnvironment;
/**
 * @author davidyan
 */
import authoringToolbar.AuthoringMenubarCreator;
import itemWindow.ItemWindow;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import mainWindow.GameMakerWindow;
import settingsWindow.SettingsWindow;

import java.awt.*;

public class MainAuthoringWindow {
	private BorderPane myPane;
	private static final int myScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int myScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	private ItemWindow myItemWindow;
	private AuthoringMenubarCreator myMenubar;
	private GameMakerWindow myMainWindow;
	private SettingsWindow mySettingsWindow;

	public MainAuthoringWindow(){
		myPane = new BorderPane();
		myMainWindow = new GameMakerWindow();
		myItemWindow = new ItemWindow(myMainWindow);
		myMenubar = new AuthoringMenubarCreator();
		mySettingsWindow = new SettingsWindow();

		myPane.setCenter(myMainWindow.getMainWindow());
		myPane.setLeft(myItemWindow.getTabPane());
		myPane.setTop(myMenubar.getMenuBar());
		myPane.setRight(mySettingsWindow.getBox());
	}

	public Scene getScene(){
		Scene myRetScene = new Scene(myPane, myScreenWidth, myScreenHeight);
		myRetScene.getStylesheets().add("resources/styles.css");
		return myRetScene;
	}

	public GameMakerWindow getGameWindow(){
		return myMainWindow;
	}

}