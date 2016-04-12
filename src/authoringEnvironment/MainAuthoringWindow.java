package authoringEnvironment;
/**
 * @author davidyan
 */
import authoringEnvironment.authoringToolbar.AuthoringMenubarCreator;
import authoringEnvironment.itemWindow.ItemWindow;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import authoringEnvironment.mainWindow.GameMakerWindow;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameplayer.IScreen;

import java.awt.*;

public class MainAuthoringWindow implements IScreen{
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
		myItemWindow = new ItemWindow(myGameMakerWindow);
		myMenubar = new AuthoringMenubarCreator();
		myMenubar.initMenuBar(myGameMakerWindow);
		mySettingsWindow = new SettingsWindow();
//        myItemWindow.init();
        myGameMakerWindow.init(mySettingsWindow);


        myPane.setCenter(myGameMakerWindow.getTabPane());
		myPane.setLeft(myItemWindow.getTabPane());
		myPane.setTop(myMenubar.getMenuBar());
		myPane.setRight(mySettingsWindow.getBox());
//        mySettingsWindow.setContent();
	}

	public Scene getScene(){
		Scene myRetScene = new Scene(myPane, myScreenWidth, myScreenHeight);
		myRetScene.getStylesheets().add("resources/styles.css");
		return myRetScene;
	}
	public GameMakerWindow getGameMakerWindow(){
		return myGameMakerWindow;
	}

	@Override
	public void switchScene(IScreen screen) {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void setParentScreen(IScreen screen) {
		// TODO Auto-generated method stub
		
	}


}