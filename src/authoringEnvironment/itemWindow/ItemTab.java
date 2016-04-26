package authoringEnvironment.itemWindow;

import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import interfaces.ITab;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import resources.FrontEndData;

import java.util.List;
/** 
 * This is the abstract tab class which will be used for the item window. 
 *
 * @author Huijia
 *
 */
public  class ItemTab implements ITab{
	
	private Tab myTab;
    private TilePane myTilePane;
    
    private static double IMAGE_HEIGHT;
    private static double OFFSET = 50;

    public ItemTab() {
        myTab = new Tab();
        myTilePane = new TilePane();
        Settings.setTilePaneSettings(myTilePane);
        IMAGE_HEIGHT = myTilePane.getTileHeight() - OFFSET;
    }
    /** 
     * this adds the sprites to the tab
     * @param list
     */
    public void populateTab(List<ImageView> list){
        list.forEach(viewSprite -> {
            viewSprite.setFitHeight(IMAGE_HEIGHT);
            viewSprite.setPreserveRatio(true);
            myTilePane.getChildren().add(viewSprite);
        });
        myTilePane.getStylesheets().add(FrontEndData.TILEPANE);
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
	@Override
	public void setViewSprite(ViewSprite view) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setBackground(String bg) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getBackground() {
		// TODO Auto-generated method stub
		return null;
	}
}
