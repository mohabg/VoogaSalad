package authoringEnvironment.itemWindow;

import java.awt.Toolkit;

import javafx.geometry.Insets;
import javafx.scene.layout.TilePane;

public class Settings {
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

	
	public Settings(){
		
	}
	
    public void setTilePaneSettings(TilePane tilepane){
        tilepane.setPrefTileHeight(200);
        tilepane.setPrefTileWidth(200);
        double inset = 5;
        tilepane.setPadding(new Insets(inset, inset, inset, inset));
        tilepane.setHgap(inset);
    }
    
    public void setTabPaneSettings(TabPane myTabPane){
    	 myTabPane.setPrefHeight(SCREEN_HEIGHT);
         myTabPane.setPrefWidth(0.25*SCREEN_WIDTH);
    }

}
