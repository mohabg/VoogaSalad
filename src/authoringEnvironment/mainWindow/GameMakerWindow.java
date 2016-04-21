package authoringEnvironment.mainWindow;

/**
 * @author: David Yan, Joe Jacob, Huijia Yu
 */
import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.Sprite;
import interfaces.ITab;
import interfaces.ITabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import resources.FrontEndData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMakerWindow implements ITabPane {
	private TabPane myTabPane;
	private Map<Tab, GameAuthoringTab> myGameTabs;
	private SettingsWindow myWindow;

	public GameMakerWindow() {

	}

	public void init(SettingsWindow window) {
		myTabPane = new TabPane();
		myTabPane.getStylesheets().add(FrontEndData.STYLESHEET);
		myGameTabs = new HashMap<Tab, GameAuthoringTab>();
		myWindow = window;
		addNewTab();

		myTabPane.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
			Tab selectedTab = myTabPane.getSelectionModel().getSelectedItem();
			GameAuthoringTab gat = myGameTabs.get(selectedTab);
			gat.setCurrentSpriteNull();
		});

	}

	/**
	 * @param mySpriteMap
	 *            uses the map to populate a new tab, or level, in the Game
	 *            Authoring Window
	 */

	public void createNewTab(Map<ViewSprite, Sprite> mySpriteMap) {
		String tabName = FrontEndData.TAB + (myTabPane.getTabs().size() + 1);
		GameAuthoringTab myTab = new GameAuthoringTab(mySpriteMap, tabName, myWindow);
		myGameTabs.put(myTab.getTab(), myTab);
		
		getTabPane().getTabs().add(myTab.getTab());
		getTabPane().getSelectionModel().select(myTab.getTab());
	}

	/**
	 * @param gameLevels
	 *            takes a list of game level objects to populate the correct
	 *            number of tabs with the correct objects
	 */

	public void setGameTabs(List<LevelModel> gameLevels) {
		myTabPane.getTabs().clear();
        // myTabPane.getStylesheets().add("authoringEnvironment/itemWindow/TabStyles.css");
		myGameTabs = new HashMap<Tab, GameAuthoringTab>();
		for (LevelModel lm : gameLevels) {
			System.out.println("new tab");
			createNewTab(lm.getMyMap());
		}
	}

	public ITab getCurrentTab() {
		return myGameTabs.get(myTabPane.getSelectionModel().getSelectedItem());
	}

	public TabPane getTabPane() {
		return myTabPane;
	}

	@Override
	public void addNewTab() {
		createNewTab(new HashMap<ViewSprite, Sprite>());
	}

	/**
	 * @return list of tabs
	 */
	@Override
	public List<ITab> getITabs() {
		List<ITab> myITabsList = new ArrayList<ITab>();
		myTabPane.getTabs().forEach(e -> {
			myITabsList.add(myGameTabs.get(e));
		});
		return myITabsList;
	}

	// @Override
	// public TabPane getTabPane() {
	// return myTabPane;
	// }
}