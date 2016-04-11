package authoringEnvironment.authoringToolbar;

import authoringEnvironment.LevelModel;
import authoringEnvironment.Model;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.mainWindow.GameMakerWindow;
import authoringEnvironment.mainWindow.GameTab;
import exampledata.XStreamHandlers.FXConverters;
import gameplayer.GameLoader;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;



/**
 * Created by davidyan on 4/7/16.
 */
public class SaveGameMenu {

    private Menu myFileMenu;
    private GameLoader myGameLoader;
    
    private final String SAVE_GAME = "Save Game";
    private final String SAVE_CURRENT_GAME = "Save Current Game";
    
    public SaveGameMenu(){
        myFileMenu = new Menu(SAVE_GAME);
        myGameLoader = new GameLoader();
    }

    public void addLevelTab(GameMakerWindow myWindow){
    	MenuItem myNewFile = new MenuItem(SAVE_CURRENT_GAME);
        myNewFile.setOnAction(e->saveMyGame(myWindow));
        myFileMenu.getItems().add(myNewFile);
    }
    
    private void saveMyGame(GameMakerWindow myWindow) {
    	TabPane allLevels = myWindow.getMyTabPane();
    	List<LevelModel> levelModels = myGameLoader.levelTabsToModels(allLevels);
    	myGameLoader.saveGame(levelModels);
    }

    public Menu getMenu(){
        return myFileMenu;
    }


}
