package authoringEnvironment.mainWindow;

/**
 * @author: davidyan
 */
import authoringEnvironment.Model;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.itemWindow.ItemWindowData;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameplayer.Screen;
import interfaces.ITab;
import interfaces.ITabPane;
import spriteProperties.NumProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import authoringEnvironment.LevelModel;

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

	public void createNewTab(Map<ViewSprite, Model> mySpriteMap) {
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
		createNewTab(new HashMap<ViewSprite, Model>());
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