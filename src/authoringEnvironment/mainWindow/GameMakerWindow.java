package authoringEnvironment.mainWindow;

/**
 * @author: davidyan
 */
import authoringEnvironment.Model;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.itemWindow.ItemWindowData;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameplayer.Screen;
import spriteProperties.NumProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import authoringEnvironment.LevelModel;

public class GameMakerWindow {
    private TabPane myTabPane;
    private SettingsWindow myWindow;
    
    public GameMakerWindow() {
        myTabPane = new TabPane();
        
    }
    
    public void init(SettingsWindow window) {
        myWindow = window;
        addNewTab();
        
    }
    
    public void createNewTab(Map<ViewSprite, Model> mySpriteMap) {
        GameTab myTab = new GameTab(mySpriteMap, ItemWindowData.TAB + (myTabPane.getTabs().size() + 1), myWindow);
        
        myTabPane.getTabs().add(myTab);
        myTabPane.getSelectionModel().select(myTab);
    }
    
    public void addNewTab() {
        createNewTab(new HashMap<ViewSprite, Model>());
    }
    
    public GameTab getCurrentTab() {
        return (GameTab) myTabPane.getSelectionModel().getSelectedItem();
    }
    
    public void populateEditingFromSave(List<LevelModel> gameLevels) {
        myTabPane.getTabs().clear();
        for (LevelModel lm : gameLevels) {
            
            createNewTab(lm.getMyMap());
        }
        
    }
    
    public TabPane getMyTabPane() {
        return myTabPane;
    }
}