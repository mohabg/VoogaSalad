package authoringEnvironment;

import authoringEnvironment.authoringToolbar.AuthoringMenubarCreator;
import authoringEnvironment.authoringToolbar.LEMenuBarCreator;
import authoringEnvironment.itemWindow.ItemWindow;
import authoringEnvironment.mainWindow.GameMakerWindow;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameplayer.PlayScreen;
import gameplayer.Screen;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import resources.FrontEndData;

/**
 * @author David Yan, Joe Jacob, Huijia Yu
 * Class that sets up the Borderpane that holds all the components for the Authoring Environment
 */

public class MainAuthoringWindow extends Screen {
    private ItemWindow myItemWindow;
    //	private AbstractMenuBar myMenubar;
    private GameMakerWindow myGameMakerWindow;
    private SettingsWindow mySettingsWindow;
    public LiveEditing le;
    private Screen myParent;
    private String myGameName;
    
    public MainAuthoringWindow(Screen parent, String gameName) {
        super(parent);
        myParent = parent;
        myGameName = gameName;
        initBorderPane();
        ((BorderPane) myPane).setCenter(myGameMakerWindow.getTabPane());
        myGameMakerWindow.init();
        myItemWindow = new ItemWindow(myGameMakerWindow);
        AuthoringMenubarCreator myMenubar = new AuthoringMenubarCreator(gameName);
        myMenubar.initMenuBar(this, myGameMakerWindow);
        setupScreen(myMenubar.getMenuBar());
    }

    private void initBorderPane() {
        myPane = new BorderPane();
        mySettingsWindow = new SettingsWindow();
        myPane.getStylesheets().add(FrontEndData.STARTING_STYLESHEET);
        myGameMakerWindow = new GameMakerWindow(new TabPane(), mySettingsWindow);
    }

    private void setupScreen(MenuBar menuBar) {
        ((BorderPane) myPane).setLeft(myItemWindow.getTabPane());
        ((BorderPane) myPane).setTop(menuBar);
        ((BorderPane) myPane).setRight(mySettingsWindow.getBox());
    }

    public MainAuthoringWindow(Screen parent, String gameName, PlayScreen myPlayScreen) {
        super(parent);
        initBorderPane();
        le = new LiveEditing(myPlayScreen, mySettingsWindow);
        myItemWindow = new ItemWindow(le);
        LEMenuBarCreator myMenubar = new LEMenuBarCreator(gameName);
        myMenubar.initMenuBar(this, le);
        ((BorderPane) myPane).setCenter(le.getMyNewGamePane());
        setupScreen(myMenubar.getMenuBar());
    }
    
    public GameMakerWindow getGameMakerWindow() {
        return myGameMakerWindow;
    }
    
    public void reset(){
    	switchScene(new MainAuthoringWindow(this, myGameName));
    }
}