
package authoringEnvironment.mainWindow;

import authoringEnvironment.AESpriteFactory;
import authoringEnvironment.LevelModel;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import resources.FrontEndData;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author David Yan, Huijia Yu, Joe Jacob
 * Main window in the Authoring Environment that allows users to set up the game to play.
 * Initializes the Sprites to be used in the game.
 */
public class GameAuthoringTab extends AClickableWindow implements ClipboardOwner {
	// viewpoint coords
	
	private Tab myTab;
	private Map<ViewSprite, Sprite> mySpriteMap;	

	public GameAuthoringTab(Map<ViewSprite, Sprite> spriteMap, Integer levelID, SettingsWindow window) {
		super(window);
		String tabName = FrontEndData.TAB + levelID;
		myTab = new Tab(tabName);
		mySpriteMap = spriteMap;

		myWindow = window;
        myLevelModel = new LevelModel();
		initArea();
	}
	
	
	@Override
	public void initArea() {
		Settings.setGamePaneSettings((AnchorPane) myNewGamePane);

		
		myNewGamePane.setOnMouseClicked(e -> {
			updateSettingsPane(myLevelModel);
		});

		setTabContent(myNewGamePane);

		mySpriteMap.keySet().forEach(c -> addWithClicking(c));
	}
	
	
	private ContextMenu createContextMenu(ViewSprite vs){
		ContextMenu contextMenu = new ContextMenu();
		MenuItem delete = new MenuItem("Delete Sprite");
		MenuItem copy = new MenuItem("Copy Image Ref");
		contextMenu.getItems().addAll(delete, copy);
		
		delete.setOnAction(event -> {
            ((Pane) getTabContent()).getChildren().remove(vs);
            event.consume();
        });
		
		copy.setOnAction(event -> {
			 StringSelection stringSelection = new StringSelection(vs.getMyImage());
			 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();		 
			 clipboard.setContents(stringSelection, this);
		});
		
		contextMenu.setAutoHide(true);
		return contextMenu;
	}

	public List<Sprite> getList() {
		return mySpriteMap.values().stream().collect(Collectors.toList());
	}

	public Tab getTab() {
		return myTab;
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

	public void setCurrentSpriteNull() {
		currentSprite = null;
	}

	/**
	 * @param viewsprite
	 *            is a ViewSprite that's going to be copied and get its
	 *            properties set between the Sprite properties.
	 */
	public void setViewSprite(ViewSprite viewsprite) {
		initViewSprite(viewsprite);
		// initGameViewSprite(viewsprite)
	}

	public void setPlayerViewSprite(ViewSprite viewsprite) {
		initViewSprite(viewsprite);
	//	mySpriteMap.get(copy).setUserControlled(true);
	}
	
	
	public void updateSettingsPane(ViewSprite clickedSprite) {
		myWindow.setContent(setSettingsContent(mySpriteMap.get(clickedSprite)));
	}
	
	public DoubleProperty getAbsoluteX() {
		return absoluteX;
	}
	
	public DoubleProperty getAbsoluteY() {
		return absoluteY;
	}
	
	public LevelModel getLevelModel() {
		// TODO Auto-generated method stub
		return myLevelModel;
	}


	@Override
	public void makeRightClickEvent(ViewSprite mySprite, MouseEvent t) {
		if (t.getButton() == MouseButton.SECONDARY) {
			double xpos = t.getSceneX();
			double ypos = t.getSceneY();
			
			ContextMenu menu = createContextMenu(mySprite);
			menu.setX(xpos);
			menu.setY(ypos);
			
			menu.setAutoHide(true);
			menu.show(mySprite, xpos, ypos);
        }
        t.consume();
	}


	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {}


	@Override
	public void updateSpriteMap(ViewSprite copy, Sprite sprite) {
		mySpriteMap.put(copy, sprite);
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
	public void updateViewpoint(KeyCode code) {
		switch (code) {
			case LEFT:	absoluteX.set(absoluteX.getValue() - 5);
				break;
			case RIGHT: absoluteX.set(absoluteX.getValue() + 5);
				break;
			case UP:	absoluteY.set(absoluteY.getValue() - 5);
				break;
			case DOWN:	absoluteY.set(absoluteY.getValue() + 5);
				break;
			default:
		}
	}


	@Override
	public ViewSprite initViewSprite(ViewSprite viewsprite) {
		ViewSprite copy = sf.clone(viewsprite);
		Sprite sprite = sf.makeSprite(copy);		
	
		// bind viewpoint
		ISpriteProperties spriteProps = sprite.getSpriteProperties();
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
		
		updateSpriteMap(copy, sprite);
		addWithClicking(copy);
		
		return copy;
	}


}