package authoringEnvironment.itemWindow;

import java.util.*;

import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import interfaces.ITab;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.TilePane;

public abstract class AbstractItemTab implements ITab{
	private Tab myTab;
    private TilePane myTilePane;

    public AbstractItemTab() {
        myTab = new Tab();
        myTilePane = new TilePane();
    }
    
    public void populateTab(List<ViewSprite> viewSprites){
        Settings.setTilePaneSettings(myTilePane);
        for(ViewSprite sprite : viewSprites){
            myTilePane.getChildren().add(sprite);
        }
        myTab.setContent(myTilePane);
    }

    public void setTabTitle(String tabTitle){
        myTab.setText(tabTitle);
    }

    public Tab getTab(){
        return myTab;
    }
    
    @Override
    public void setTabContent(Node content) {
    	
    }
}
