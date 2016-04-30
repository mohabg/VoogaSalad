package authoringEnvironment.itemWindow;

import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import interfaces.IGameWindow;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.ImageView;
import resources.FrontEndData;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author David Yan, Sam Toffler, Huijia Yu, Joe Jacob
 * Generates the Item Window in the Authoring Environment that displays all possible sprites to use in the game
 */
public class ItemWindow {
	private TabPane myTabPane;
	private IGameWindow myGameTabPane;
	// private Map<ViewSprite, Sprite> mySpritesAndModels;

	public ItemWindow(IGameWindow window) {
		myGameTabPane = window;
		myTabPane = new TabPane();
		myTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		myTabPane.getStylesheets().add(FrontEndData.ITEMWINDOW_STYLESHEET);
		// mySpritesAndModels = new HashMap<ViewSprite, Sprite>();
		initTabPane();
	}

	private void initTabPane() {
		Settings.setTabPaneSettings(myTabPane);
		myTabPane.getTabs().addAll(FrontEndData.TabTypes.stream().map(t -> makeTab(t)).collect(Collectors.toList()));
	}

	private Tab makeTab(String type) {
		try {
			ItemTab tab = new ItemTab();
			// TOOD: Change type checking here
			if (type.equals("Background")) {
				tab.populateTab(fillBackground(type));
			} else {
				tab.populateTab(fillSprites(type));
			}
			tab.setTabTitle(type);
			return tab.getTab();
		} catch (Exception e) {
			// TODO throw exception
		}
		return null;
	}

	private List<ImageView> fillBackground(String type) {
		return FrontEndData.SpriteImages.keySet().stream().filter(s -> s.startsWith(type)).map(k -> makeBackground(k))
				.collect(Collectors.toList());
	}

	private ImageView makeBackground(String k) {
		String p = FrontEndData.SpriteImages.getString(k);
		ImageView bg = new ImageView(p);
		bg.setOnMouseClicked(e -> {
			myGameTabPane.setBackground(p);
		});
		return bg;
	}

	private List<ImageView> fillSprites(String type) {
		return FrontEndData.SpriteImages.keySet().stream().filter(s -> s.startsWith(type))
				.map(k -> makeViewSprite(k, type)).collect(Collectors.toList());
	}

	private ViewSprite makeViewSprite(String key, String type) {
		String p = FrontEndData.SpriteImages.getString(key);
		ViewSprite viewsprite = new ViewSprite(p);
		if (type.equals("Player")) {
			viewsprite.setOnMouseClicked(e -> {
				myGameTabPane.setPlayerViewSprite(viewsprite);
			});
		} else {
			viewsprite.setOnMouseClicked(e -> {
				myGameTabPane.setViewSprite(viewsprite);
			});
		}
		return viewsprite;
	}

	public TabPane getTabPane() {
		return myTabPane;
	}
}