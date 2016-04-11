package authoringEnvironment.itemWindow;

import authoringEnvironment.Model;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import authoringEnvironment.mainWindow.GameMakerWindow;

import java.util.*;
import java.util.stream.Collectors;

public class ItemWindow {
	private TabPane myTabPane;
	private GameMakerWindow window;

	private Map<ViewSprite, Model> mySpritesAndModels;

	public ItemWindow(GameMakerWindow window) {
		this.window = window;
		myTabPane = new TabPane();
		mySpritesAndModels = new HashMap<ViewSprite, Model>();
		Settings.setTabPaneSettings(myTabPane);

		init();
	}

	public void init() {
		 myTabPane.getTabs().addAll(ItemWindowData.TabTypes.stream().map(t ->
		 makeTab(t)).collect(Collectors.toList()));

	}

	private Tab makeTab(String type) {
		try {
			Class c = Class.forName(ItemWindowData.ItemPaths.getString(type));
			AItemTab tab = (AItemTab) c.newInstance();
			tab.populateTab(fillSprites(type));
			tab.setTabTitle(type);
			return tab.getTab();

		} catch (Exception e) {

		}
		return null;

	}

	private Collection<ViewSprite> fillSprites(String type) {
		return ItemWindowData.SpriteImages.keySet().stream().filter(s -> s.startsWith(type)).map(k -> makeViewSprite(k))
				.collect(Collectors.toList());
	}

	private ViewSprite makeViewSprite(String key) {
		try {
			Class c = Class.forName(ItemWindowData.VIEWSPRITE);
			ViewSprite sprite = (ViewSprite) c.newInstance();

			sprite.setImage(ItemWindowData.SpriteImages.getString(key));
			mySpritesAndModels.put(sprite, new Model(ItemWindowData.SpriteImages.getString(key)));

			sprite.setOnMouseClicked(e -> {
				window.getCurrentTab().addToWindow(sprite, mySpritesAndModels.get(sprite));
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