package authoringEnvironment;

import authoringEnvironment.mainWindow.AClickableWindow;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameplayer.PlayScreen;
import interfaces.IGameWindow;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
		myLevelModel = new LevelModel(ps.getCurrentLevel());
		
		myNewGamePane = ps.getScreen().getPane();
		myNewGamePane.getChildren().removeAll(ps.getViewSprites().values());
		initArea();
		setViewpoint();
	}
	
	@Override
	public void initArea() {
		Settings.setGamePaneSettings(myNewGamePane);
		
		myNewGamePane.setOnMouseClicked(e -> {
			updateSettingsPane(myLevelModel);
		});

		//ps.getViewSprites().values().forEach(c -> addWithClicking(c));
		ps.getViewSprites().values().forEach(c -> initViewSprite(c));
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
		t.consume();	
	}

	@Override
	public void setViewpoint() {
		myNewGamePane.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			updateViewpoint(key.getCode());
			key.consume();
		});		
	}

	@Override
	public void updateViewpoint(KeyCode code) {
		switch (code) {
			case O:	absoluteX.set(absoluteX.getValue() - 5);
				break;
			case P: absoluteX.set(absoluteX.getValue() + 5);
				break;
			case L:	absoluteY.set(absoluteY.getValue() - 5);
				break;
			case K:	absoluteY.set(absoluteY.getValue() + 5);
				break;
			default:
		}
		
		System.out.println(code.getName() + " " + absoluteX.getValue() + " " + absoluteY.getValue());
	}

	@Override
	public void updateSpriteMap(ViewSprite copy, Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ViewSprite initViewSprite(ViewSprite viewsprite) {
		// bind viewpoint
		ISpriteProperties spriteProps = viewsprite.getMySpriteProperties();
		
		DoubleProperty spriteX = spriteProps.getXProperty();	
		absoluteX.addListener((o, ov, nv) -> {
			double change = nv.doubleValue() - ov.doubleValue();
			spriteX.setValue(spriteX.getValue() - change);
		});
		
		DoubleProperty spriteY = spriteProps.getYProperty();
		absoluteY.addListener((o, ov, nv) -> {
			double change = nv.doubleValue() - ov.doubleValue();
			spriteY.setValue(spriteY.getValue() - change);
		});
		
		
		addWithClicking(viewsprite);
		
		return viewsprite;
	}
	

}
