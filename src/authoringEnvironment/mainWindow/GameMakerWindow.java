package authoringEnvironment.mainWindow;

/**
 * @author: David Yan, Joe Jacob, Huijia Yu
 */
import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.itemWindow.ItemWindowData;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.Sprite;
import interfaces.ITab;
import interfaces.ITabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMakerWindow implements ITabPane{
	private TabPane myTabPane;
	private Map<Tab, GameAuthoringTab> myGameTabs;
	private SettingsWindow myWindow;

	public GameMakerWindow() {
		myTabPane = new TabPane();
		myGameTabs = new HashMap<Tab, GameAuthoringTab>();
	}

	public void init(SettingsWindow window) {
		myWindow = window;
		addNewTab();
	}

	public void createNewTab(Map<ViewSprite, Sprite> mySpriteMap) {
		String tabName = ItemWindowData.TAB + (myTabPane.getTabs().size() + 1);
		GameAuthoringTab myTab = new GameAuthoringTab(mySpriteMap, tabName, myWindow);
		myGameTabs.put(myTab.getTab(), myTab);
		
		getTabPane().getTabs().add(myTab.getTab());
		getTabPane().getSelectionModel().select(myTab.getTab());
	}

	public void populateEditingFromSave(List<LevelModel> gameLevels) {
		myTabPane.getTabs().clear();
		for (LevelModel lm : gameLevels) {
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

	@Override
	public List<ITab> getITabs() {
		List<ITab> myITabsList = new ArrayList<ITab>();
		myGameTabs.values().stream()
		.forEach(e -> {
			myITabsList.add((ITab) e);
		});
		return myITabsList;
	}

//	@Override
//	public TabPane getTabPane() {
//		return myTabPane;
//	}
}