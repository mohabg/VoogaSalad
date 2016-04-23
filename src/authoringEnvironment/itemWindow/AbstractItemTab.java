package authoringEnvironment.itemWindow;

import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import interfaces.ITab;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.TilePane;
import resources.FrontEndData;

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
    
    private static double IMAGE_HEIGHT;
    private static double OFFSET = 50;

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
        IMAGE_HEIGHT = myTilePane.getTileHeight() - OFFSET;
        viewSprites.forEach(viewSprite -> {
            viewSprite.setFitHeight(IMAGE_HEIGHT);
            viewSprite.setPreserveRatio(true);
            myTilePane.getChildren().add(viewSprite);
        });
        myTilePane.setId(FrontEndData.TILEPANE);
        myTilePane.getStylesheets().add(FrontEndData.STYLESHEET);
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
	public Node getTabContent() {
		// TODO Auto-generated method stub
		return null;
	}
}
