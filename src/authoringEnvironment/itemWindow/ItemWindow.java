package authoringEnvironment.itemWindow;

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
		mySpritesAndModels = new HashMap<ViewSprite, Sprite>();
		initTabPane();
	}

	private void initTabPane() {
		 Settings.setTabPaneSettings(myTabPane);
		 myTabPane.getTabs().addAll(ItemWindowData.TabTypes.stream()
				 .map(t -> makeTab(t))
				 .collect(Collectors.toList()));
	}


    /**
     * makeTab uses the method of reflection to generate new instances of tabs
     * The tabs are filled with sprites
     * @return new tab to be added to the character tab settings
     */
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
            Sprite newS = new Sprite(p);

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