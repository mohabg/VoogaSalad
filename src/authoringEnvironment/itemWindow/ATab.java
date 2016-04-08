package authoringEnvironment.itemWindow;

import java.util.*;

import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.TilePane;

public abstract class ATab {
	Tab tab;
    TilePane tilepane;

    public ATab() {
        tab = new Tab();
        tilepane = new TilePane();
    }
    
    public void populateTab(Collection<ViewSprite> viewSprites){
        Settings s = new Settings();
        s.setTilePaneSettings(tilepane);
        for(ViewSprite x : viewSprites){
//			img.setFitHeight(myTabPane.getPrefWidth()*0.25);
//			img.setFitWidth(myTabPane.getPrefWidth()*0.25);
            tilepane.getChildren().add(x);
        }
        tab.setContent(tilepane);
    }

    public void setTabTitle(String tabTitle){
        tab.setText(tabTitle);
    }


    public Tab getTab(){
        return tab;
    }
}
