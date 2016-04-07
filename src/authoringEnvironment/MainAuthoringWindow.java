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
	private GameMakerWindow myGameMakerWindow;
	private SettingsWindow mySettingsWindow;

	public MainAuthoringWindow(){
		myPane = new BorderPane();
		myGameMakerWindow = new GameMakerWindow();
		myItemWindow = new ItemWindow();
		myMenubar = new AuthoringMenubarCreator();
		myMenubar.init(myGameMakerWindow);
		mySettingsWindow = new SettingsWindow();
        myItemWindow.init(myGameMakerWindow, mySettingsWindow);
        myGameMakerWindow.init(mySettingsWindow);


        myPane.setCenter(myGameMakerWindow.getMainWindow());
		myPane.setLeft(myItemWindow.getTabPane());
		myPane.setTop(myMenubar.getMenuBar());
		myPane.setRight(mySettingsWindow.getBox());
        //mySettingsWindow.setContent();
	}

	public Scene getScene(){
		Scene myRetScene = new Scene(myPane, myScreenWidth, myScreenHeight);
		myRetScene.getStylesheets().add("resources/styles.css");
		return myRetScene;
	}

}