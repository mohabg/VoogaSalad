package authoringEnvironment;

/**
 * @author davidyan
 */
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import mainWindow.GameMakerWindow;
import settingsWindow.SettingsWindow;

import java.awt.Toolkit;

import authoringToolbar.AuthoringMenubarCreator;
import itemWindow.ItemWindow;

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
		myItemWindow = new ItemWindow();
		myMenubar = new AuthoringMenubarCreator();
		myMainWindow = new GameMakerWindow();
		mySettingsWindow = new SettingsWindow();
		myPane.setLeft(myItemWindow.getTabPane());
		myPane.setTop(myMenubar.getMenuBar());
		myPane.setCenter(myMainWindow.getMainWindow());
		myPane.setRight(mySettingsWindow.getBox());
		
	}
	
	public Scene getScene(){
		Scene myRetScene = new Scene(myPane, myScreenWidth, myScreenHeight);
		myRetScene.getStylesheets().add("resources/styles.css");
		return myRetScene;
	}

}
