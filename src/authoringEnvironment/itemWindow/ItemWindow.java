package authoringEnvironment.itemWindow;

import authoringEnvironment.RefObject;
import authoringEnvironment.Settings;
import authoringEnvironment.SpriteProperties;
import authoringEnvironment.ViewSprite;
import gameElements.Behavior;
import gameElements.Collision;
import gameElements.Health;
import gameElements.Sprite;
import interfaces.ITabPane;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;
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
		mySpritesAndModels = new HashMap<ViewSprite, Sprite>();
		initTabPane();
	}

	private void initTabPane() {
		 Settings.setTabPaneSettings(myTabPane);
		 myTabPane.getTabs().addAll(ItemWindowData.TabTypes.stream()
				 .map(t -> makeTab(t))
				 .collect(Collectors.toList()));
	}

	private Tab makeTab(String type) {
        System.out.println(type);
		try {
			Class c = Class.forName(ItemWindowData.ItemPaths.getString(type));
			AbstractItemTab tab = (AbstractItemTab) c.newInstance();
			tab.populateTab(fillSprites(type));
			tab.setTabTitle(type);
			return tab.getTab();
		} catch (Exception e) {
			// TODO throw exception
		}
		return null;
	}

	private List<ViewSprite> fillSprites(String type) {
		return ItemWindowData.SpriteImages.keySet().stream()
				.filter(s -> s.startsWith(type))
				.map(k -> makeViewSprite(k))
				.collect(Collectors.toList());
	}

	private ViewSprite makeViewSprite(String key) {
		try {
			Class c = Class.forName(ItemWindowData.VIEWSPRITE);
			ViewSprite sprite = (ViewSprite) c.newInstance();

			sprite.setImage(ItemWindowData.SpriteImages.getString(key));

            String p = ItemWindowData.SpriteImages.getString(key);
            
            //TODO: also change this
            Sprite newS = new Sprite(new SpriteProperties(), new Health(), new ArrayList<Collision>(), new HashMap<String, Behavior>(), new RefObject(p));
           
            
            //TODO: move this stuff to sprite class
            System.out.println(sprite.getHeight());
            newS.setHeight(new SimpleDoubleProperty(sprite.getHeight()));
            newS.setWidth(new SimpleDoubleProperty(sprite.getWidth()));

			mySpritesAndModels.put(sprite, newS);

			sprite.setOnMouseClicked(e -> {
				myGameTabPane.getCurrentTab().setTabContent(sprite, mySpritesAndModels.get(sprite));
			});

			return sprite;
		} catch (Exception e) {

        }
		return null;
	}

	public TabPane getTabPane() {
		return myTabPane;
	}
}