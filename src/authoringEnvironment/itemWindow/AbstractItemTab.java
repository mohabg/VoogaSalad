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
    
    private static double IMAGE_HEIGHT;

    public AbstractItemTab() {
        myTab = new Tab();
        myTilePane = new TilePane();
    }
    
    public void populateTab(List<ViewSprite> viewSprites){
        Settings.setTilePaneSettings(myTilePane);
        IMAGE_HEIGHT = myTilePane.getTileHeight() - 50;
        for(ViewSprite sprite : viewSprites){
        	sprite.setFitHeight(IMAGE_HEIGHT);
        	sprite.setPreserveRatio(true);
            myTilePane.getChildren().add(sprite);
        }
        setTabContent(myTilePane);
    }

    public void setTabTitle(String tabTitle){
        myTab.setText(tabTitle);
    }

    public Tab getTab(){
        return myTab;
    }
    
    @Override
    public void setTabContent(Node content) {
    	myTab.setContent(content);
    }
}
