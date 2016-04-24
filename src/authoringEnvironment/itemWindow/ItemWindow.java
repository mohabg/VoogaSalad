package authoringEnvironment.itemWindow;

import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import interfaces.ITabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import resources.FrontEndData;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author David Yan, Sam Toffler, Huijia Yu, Joe Jacob
 */
public class ItemWindow {
	private TabPane myTabPane;
	private ITabPane myGameTabPane;
	// private Map<ViewSprite, Sprite> mySpritesAndModels;

	public ItemWindow(ITabPane window) {
		myGameTabPane = window;
		myTabPane = new TabPane();
		myTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		myTabPane.getStylesheets().add("authoringEnvironment/itemWindow/itemWindow.css");
		// mySpritesAndModels = new HashMap<ViewSprite, Sprite>();
		initTabPane();
	}

	private void initTabPane() {
		Settings.setTabPaneSettings(myTabPane);
		myTabPane.getTabs().addAll(FrontEndData.TabTypes.stream().map(t -> makeTab(t)).collect(Collectors.toList()));
	}

	private Tab makeTab(String type) {
		try {
			Class c = Class.forName(FrontEndData.ItemPaths.getString(type));
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
		return FrontEndData.SpriteImages.keySet().stream().filter(s -> s.startsWith(type)).map(k -> makeViewSprite(k))
				.collect(Collectors.toList());
	}

	private ViewSprite makeViewSprite(String key) {
        	String p = FrontEndData.SpriteImages.getString(key);
			ViewSprite viewsprite = new ViewSprite(p);
			viewsprite.setOnMouseClicked(e -> {
				myGameTabPane.getCurrentTab().setTabContent(viewsprite);
			});
			return viewsprite;
	}

	public TabPane getTabPane() {
		return myTabPane;
	}
}