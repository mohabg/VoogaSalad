
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
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import resources.FrontEndData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author David Yan, Huijia Yu, Joe Jacob
 * Main window in the Authoring Environment that allows users to set up the game to play.
 * Initializes the Sprites to be used in the game.
 */
public class GameAuthoringTab extends AClickableWindow {
	// viewpoint coords
	private DoubleProperty absoluteX;
	private DoubleProperty absoluteY;
	
	private Tab myTab;
	private Map<ViewSprite, Sprite> mySpriteMap;
	private ViewSprite currentSprite;
	private SettingsWindow myWindow;
	private ContextMenu contextMenu;
	private LevelModel myLevelModel;
	
	private AESpriteFactory sf;

	public GameAuthoringTab(Map<ViewSprite, Sprite> spriteMap, Integer levelID, SettingsWindow window) {
		super(window);
		sf = new AESpriteFactory();
		String tabName = FrontEndData.TAB + levelID;
		myTab = new Tab(tabName);
		mySpriteMap = spriteMap;

		myWindow = window;
		contextMenu = new ContextMenu();
		createContextMenu();
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
	
	public void initViewpoint() {
		absoluteX = new SimpleDoubleProperty(0);
		absoluteY = new SimpleDoubleProperty(0);
		
		myTab.getTabPane().addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			if (myTab.isSelected()) {
				updateViewpoint(key.getCode());
			}
			key.consume();
		});
	}
	
	private void updateViewpoint(KeyCode code) {
		switch (code) {
			case LEFT:	absoluteX.set(absoluteX.getValue() - 3);
				break;
			case RIGHT: absoluteX.set(absoluteX.getValue() + 3);
				break;
			case UP:	absoluteY.set(absoluteY.getValue() - 3);
				break;
			case DOWN:	absoluteY.set(absoluteY.getValue() + 3);
				break;
			default:
		}
		
		System.out.println(code.getName() + " " + absoluteX.getValue() + " " + absoluteY.getValue());
	}
	
	private void createContextMenu(){
		MenuItem delete = new MenuItem("delete");
		contextMenu.getItems().add(delete);
		delete.setOnAction(event -> {
            ((Pane) getTabContent()).getChildren().remove(currentSprite);
            event.consume();
        });
		contextMenu.setAutoHide(true);
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
	 * @param view
	 *            is a ViewSprite that's going to be copied and get its
	 *            properties set between the Sprite properties.
	 */
	public void setViewSprite(ViewSprite viewsprite) {
		cloneAndInitViewSprite(viewsprite);
	}

	public void setPlayerViewSprite(ViewSprite viewsprite) {
		ViewSprite copy = cloneAndInitViewSprite(viewsprite);
		mySpriteMap.get(copy).setUserControlled(true);
	}
	
	private ViewSprite cloneAndInitViewSprite(ViewSprite viewsprite) {
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
		
		
		mySpriteMap.put(copy, sprite);
		addWithClicking(copy);
		
		return copy;
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

}