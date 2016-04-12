package authoringEnvironment.itemWindow;

import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import gameElements.Sprite;
import interfaces.ITab;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.TilePane;

import java.util.List;
/** 
 * This is the abstract tab class which will be used for the item window. 
 *
 * @author Huijia
 *
 */
public abstract class AbstractItemTab implements ITab{
	private Tab myTab;
    private TilePane myTilePane;

    public AbstractItemTab() {
        myTab = new Tab();
        myTilePane = new TilePane();
    }
    /** 
     * this adds the sprites to the tab
     * @param viewSprites
     */
    public void populateTab(List<ViewSprite> viewSprites){
        Settings.setTilePaneSettings(myTilePane);
        for(ViewSprite sprite : viewSprites){
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

	@Override
	public void setTabContent(ViewSprite view, Sprite model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getTabContent() {
		// TODO Auto-generated method stub
		return null;
	}
}
