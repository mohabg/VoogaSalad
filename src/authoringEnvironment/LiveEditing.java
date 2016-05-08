// This entire file is part of my masterpiece.
// Joe Jacob

/**
 * 
 */
package authoringEnvironment;

import authoringEnvironment.mainWindow.AClickableWindow;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.Sprite;
import gameplayer.PlayScreen;
import interfaces.IGameWindow;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

/**
 * 
 * @author davidyan, Joe Jacob, Huijia Yu
 * LiveEditing window that retains functionality in the Authoring Environment to allow user to edit while playing
 *
 */
public class LiveEditing extends AClickableWindow implements IGameWindow {
	PlayScreen ps;

	public LiveEditing(PlayScreen myPlayScreen, SettingsWindow window) {
		super(window);
		ps = myPlayScreen;
		setMyLevelModel(new LevelModel(ps.getCurrentLevel()));
		
		setMyNewGamePane(ps.getScreen().getPane());
		getMyNewGamePane().getChildren().removeAll(ps.getViewSprites().values());
		initArea();

		setViewpoint();
	}
	
	@Override
	public void initArea() {
		Settings.setGamePaneSettings(getMyNewGamePane());
		
		getMyNewGamePane().setOnMouseClicked(e -> {
			updateSettingsPane(getMyLevelModel());
		});

		ps.getViewSprites().values().forEach(c -> initViewSprite(c));
	}

	@Override
	public void setViewSprite(ViewSprite vs) {
		ViewSprite copy = getSpriteFactory().clone(vs);
		setClicking(copy);
		ps.addSprite(copy);

	}


	@Override
	public void updateSettingsPane(ViewSprite clickedSprite) {
		Integer ID = null;
		for(Integer i : ps.getViewSprites().keySet()) {
			if (clickedSprite.equals(ps.getViewSprites().get(i))) {
				ID = i;
				break;
			}
		}

		getMySettingsWindow().setContent(setSettingsContent(ps.getSprites().get(ID)));		
	}

	@Override
	public void setViewpoint() {
		getMyNewGamePane().addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			updateViewpoint(key.getCode());
			key.consume();
		});		
	}
	
	@Override
	public void updateSpriteMap(ViewSprite copy, Sprite sprite) {
		return;
	}

	@Override
	public ViewSprite initViewSprite(ViewSprite viewsprite) {
		bindSpritePos(viewsprite, getSpriteFactory().makeSprite(viewsprite));
		
		addWithClicking(viewsprite);
		
		return viewsprite;
	}

	@Override
	public Node getTabContent() {
		return null;
	}
	
	public PlayScreen getPlayScreen(){
		return ps;
	}
	

}
