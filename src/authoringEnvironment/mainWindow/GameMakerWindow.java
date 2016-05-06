package authoringEnvironment.mainWindow;

import authoringEnvironment.AESpriteFactory;
import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.Sprite;
import interfaces.IGameWindow;
import interfaces.ITabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import resources.FrontEndData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: David Yan, Joe Jacob, Huijia Yu
 */
/**
 * 
 * @author davidyan, Joe Jacob, Huijia Yu
 * Initializes the overall Tab Pane object to hold the Game Authoring Tabs that represents each level of the game.
 *
 */
public class GameMakerWindow implements ITabPane, IGameWindow {
	private TabPane myTabPane;
	private Map<Tab, GameAuthoringTab> myGameTabs;
	private SettingsWindow myWindow;

	public GameMakerWindow(TabPane tabPane, SettingsWindow settingsWindow) {
		myWindow = settingsWindow;
		myTabPane = tabPane;
		myTabPane.getStylesheets().add(FrontEndData.MAINWINDOW_STYLESHEET);
		myTabPane.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
			Tab selectedTab = myTabPane.getSelectionModel().getSelectedItem();
            GameAuthoringTab gat = myGameTabs.get(selectedTab);
			if (gat != null) {
				gat.setCurrentSpriteNull();
			}
		});
		myGameTabs = new HashMap<Tab, GameAuthoringTab>();
	}

	public void init() {	
		addNewTab();
		myTabPane.layoutXProperty().addListener((o, ov, nv) -> {
			System.out.println("layout " + myTabPane.getLayoutX() + " " + myTabPane.getLayoutY());
			System.out.println("translate " + myTabPane.getTranslateX() + " " + myTabPane.getTranslateY());
		});
	}

	/**
	 * @param mySpriteMap
	 *            uses the map to populate a new tab, or level, in the Game
	 *            Authoring Window
	 */

	public void createNewTab(Map<ViewSprite, Sprite> mySpriteMap) {
		GameAuthoringTab myTab = new GameAuthoringTab(mySpriteMap, myTabPane.getTabs().size() + 1, myWindow);
		myGameTabs.put(myTab.getTab(), myTab);
		getTabPane().getTabs().add(myTab.getTab());
		// must be done after added to tabpane
		getTabPane().getSelectionModel().select(myTab.getTab());
	}

	/**
	 * @param gameLevels
	 *            takes a list of game level objects to populate the correct
	 *            number of tabs with the correct objects
	 */

	public void setGameTabs(List<LevelModel> gameLevels) {
		myTabPane.getTabs().clear();
		// myTabPane.getStylesheets().add("authoringEnvironment/itemWindow/styles.css");
		myGameTabs = new HashMap<>();
		for (LevelModel lm : gameLevels) {
			AESpriteFactory sf = new AESpriteFactory();
			createNewTab(sf.makeMap(lm.getMySpriteList()));
		}
	}

	public GameAuthoringTab getCurrentTab() {
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
	public List<GameAuthoringTab> getTabs() {
		List<GameAuthoringTab> myTabsList = new ArrayList<>();
		myTabPane.getTabs().forEach(e -> {
			myTabsList.add(myGameTabs.get(e));
		});
		return myTabsList;
	}

	public void setViewSprite(ViewSprite vs) {
		getCurrentTab().setViewSprite(vs);
	}

	public void setBackground(String bg) {
		getCurrentTab().setBackground(bg);

	}

	public void setPlayerViewSprite(ViewSprite viewsprite) {
		getCurrentTab().setPlayerViewSprite(viewsprite);
	}

	

	// @Override
	// public TabPane getTabPane() {
	// return myTabPane;
	// }
}