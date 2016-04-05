package itemWindow;

import java.util.ArrayList;

import authoringEnvironment.ViewSprite;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.TilePane;

public class PlayerSpritesTab implements ITab{
	
	Tab tab;
	TilePane tilepane;
	
	public PlayerSpritesTab(){
		tab = new Tab();
		tilepane = new TilePane();
	}
	
	public void populateTab(ArrayList<ViewSprite> viewSprites){
		setTilePaneSettings();
		for(ViewSprite x : viewSprites){
//			img.setFitHeight(myTabPane.getPrefWidth()*0.25);
//			img.setFitWidth(myTabPane.getPrefWidth()*0.25);
			tilepane.getChildren().add(x.getImageView());
		}
		tab.setContent(tilepane);
	}
	
	public void setTabTitle(String tabTitle){
		tab.setText(tabTitle);
	}
	
	private void setTilePaneSettings(){
		tilepane.setPrefTileHeight(200);
		tilepane.setPrefTileWidth(200);
		double inset = 5;
		tilepane.setPadding(new Insets(inset, inset, inset, inset));
		tilepane.setHgap(inset);
	}

	public Tab getTab(){
		return tab;
	}
}