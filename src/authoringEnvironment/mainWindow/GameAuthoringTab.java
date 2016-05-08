// This entire file is part of my masterpiece.
// Joe Jacob

/**
 * This class's purpose is to project the game authoring for a single level 
 * within the game authoring environment. 
 * 
 * I think this class is well-designed because it effectively uses both the features of 
 * implementing interfaces and using the abstract class hierarchy, which avoids the need to check 
 * class types and reusing code, respectively. Additionally, this class is a product of the OO
 * principle of object-oriented design because it 'extended' the AClickableWindow class while keeping 
 * the source code of AClickableWindow intact.
 */

package authoringEnvironment.mainWindow;

import authoringEnvironment.LevelModel;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.Sprite;
import interfaces.IGameWindow;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyEvent;
import resources.FrontEndData;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author David Yan, Huijia Yu, Joe Jacob
 * Main window in the Authoring Environment that allows users to set up the game to play.
 * Initializes the Sprites to be used in the game.
 */
public class GameAuthoringTab extends AClickableWindow implements IGameWindow{
	private Tab myTab;
	private Map<ViewSprite, Sprite> mySpriteMap;	

	public GameAuthoringTab(Map<ViewSprite, Sprite> spriteMap, Integer levelID, SettingsWindow window) {
		super(window);
		String tabName = FrontEndData.TAB + levelID;
		myTab = new Tab(tabName);
		mySpriteMap = spriteMap;

		setMySettingsWindow(window);
        setMyLevelModel(new LevelModel());
		initArea();
	}
	
	
	@Override
	public void initArea() {
		Settings.setGamePaneSettings(getMyNewGamePane());

		getMyNewGamePane().setOnMouseClicked(e -> {
			updateSettingsPane(getMyLevelModel());
		});

		setTabContent(getMyNewGamePane());
		mySpriteMap.keySet().forEach(c -> addWithClicking(c));
	}

	@Override
	public void setViewSprite(ViewSprite viewsprite) {
		initViewSprite(viewsprite);
	}
	
	@Override
	public void updateSettingsPane(ViewSprite clickedSprite) {
		getMySettingsWindow().setContent(setSettingsContent(mySpriteMap.get(clickedSprite)));
	}
	
	@Override
	public void setViewpoint() {
		myTab.getTabPane().addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			if (myTab.isSelected()) {
				updateViewpoint(key.getCode());
			}
			key.consume();
		});
	}
	
	@Override
	public void updateSpriteMap(ViewSprite copy, Sprite sprite) {
		mySpriteMap.put(copy, sprite);
	}

	@Override
	public ViewSprite initViewSprite(ViewSprite viewsprite) {
		ViewSprite copy = getSpriteFactory().clone(viewsprite);
		Sprite sprite = getSpriteFactory().makeSprite(copy);		
	
		bindSpritePos(copy, sprite);
		updateSpriteMap(copy, sprite);	
		
		return copy;
	}

	public List<Sprite> getSpriteList() {
		return mySpriteMap.values().stream().collect(Collectors.toList());
	}

	public Node getTabContent() {
		return myTab.getContent();
	}

	public void setTabContent(Node content) {
		myTab.setContent(content);
    }

	
	public void setTabTitle(String tabTitle) {
		myTab.setText(tabTitle);
	}

	public Tab getTab() {
		return myTab;
	}
	
	public void setCurrentSpriteNull() {
		setCurrentSprite(null);	
	}

}