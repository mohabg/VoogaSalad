package authoringEnvironment.itemWindow;

import authoringEnvironment.FrontEndData;
import authoringEnvironment.RefObject;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import gameElements.Sprite;
import interfaces.ITabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author David Yan, Sam Toffler, Huijia Yu, Joe Jacob
 */
public class ItemWindow {
	private TabPane myTabPane;
	private ITabPane myGameTabPane;
	private Map<ViewSprite, Sprite> mySpritesAndModels;

	public ItemWindow(ITabPane window) {
		myGameTabPane = window;
		myTabPane = new TabPane();
        myTabPane.getStylesheets().add(FrontEndData.STYLESHEET);
		mySpritesAndModels = new HashMap<ViewSprite, Sprite>();
		initTabPane();
	}

	private void initTabPane() {
		 Settings.setTabPaneSettings(myTabPane);
		 myTabPane.getTabs().addAll(FrontEndData.TabTypes.stream()
				 .map(t -> makeTab(t))
				 .collect(Collectors.toList()));
	}

	private Tab makeTab(String type) {
		try {
			Class c = Class.forName(FrontEndData.ItemPaths.getString(type));
			AbstractItemTab tab = (AbstractItemTab) c.newInstance();
			tab.populateTab(fillSprites(type));
			System.out.println("made it");
			tab.setTabTitle(type);
			return tab.getTab();
		} catch (Exception e) {
			// TODO throw exception
		}
		return null;
	}

	private List<ViewSprite> fillSprites(String type) {
		return FrontEndData.SpriteImages.keySet().stream()
				.filter(s -> s.startsWith(type))
				.map(k -> makeViewSprite(k))
				.collect(Collectors.toList());
	}

	private ViewSprite makeViewSprite(String key) {
		try {
			Class c = Class.forName(FrontEndData.VIEWSPRITE);
			ViewSprite viewsprite = (ViewSprite) c.newInstance();

            String p = FrontEndData.SpriteImages.getString(key);
            viewsprite.setImage(p);
            
            Sprite newS = new Sprite(new RefObject(p));
            //newS.setHeight(new SimpleDoubleProperty(viewsprite.getHeight()));
            //newS.setWidth(new SimpleDoubleProperty(viewsprite.getWidth()));
            //sprite.setMySpriteProperties(newS.getSpriteProperties());

            mySpritesAndModels.put(viewsprite, newS);
            
			viewsprite.setOnMouseClicked(e -> {
				myGameTabPane.getCurrentTab().setTabContent(viewsprite, newS);
			});	
		
			return viewsprite;
		} catch (Exception e) {

        }
		return null;
	}

	public TabPane getTabPane() {
		return myTabPane;
	}
}