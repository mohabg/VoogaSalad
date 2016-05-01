package authoringEnvironment;

import authoringEnvironment.mainWindow.AClickableWindow;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.Sprite;
import gameplayer.PlayScreen;
import interfaces.IGameWindow;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
/**
 * 
 * @author davidyan, Joe Jacob, Huijia Yu
 * LiveEditing window that retains functionality in the Authoring Environment to allow user ot edit while playing
 *
 */
public class LiveEditing extends AClickableWindow implements IGameWindow {
	PlayScreen ps;

	public LiveEditing(PlayScreen myPlayScreen, SettingsWindow window) {
		super(window);
		ps = myPlayScreen;
		myLevelModel = new LevelModel(ps.getCurrentLevel());
		
		// TODO THIS IS RELATED TO THE WEIRD RESIZING
		myNewGamePane = ps.getScreen().getPane();
		myNewGamePane.getChildren().removeAll(ps.getViewSprites().values());
		initArea();
	}
	
	@Override
	public void initArea() {
		//ScrollPane myNewGameArea = new ScrollPane();
		//myNewGameArea.setContent(myNewGamePane);
		//myNewGamePane.get
		Settings.setGamePaneSettings(myNewGamePane);
		
		myNewGamePane.setOnMouseClicked(e -> {
			updateSettingsPane(myLevelModel);
		});

		ps.getViewSprites().values().forEach(c -> addWithClicking(c));
	}

	@Override
	public void setViewSprite(ViewSprite vs) {
		AESpriteFactory sf = new AESpriteFactory();
		ViewSprite copy = sf.clone(vs);
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

		myWindow.setContent(setSettingsContent(ps.getSprites().get(ID)));		
	}
	public PlayScreen getPlayScreen(){
		return ps;
	}
	@Override
	public void setPlayerViewSprite(ViewSprite viewsprite) {
		setViewSprite(viewsprite);
	}
	@Override
	public void makeRightClickEvent(ViewSprite mySprite, MouseEvent t) {
		// TODO Auto-generated method stub
		t.consume();
		
	}
	

}
