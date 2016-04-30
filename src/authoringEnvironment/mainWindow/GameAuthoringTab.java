
package authoringEnvironment.mainWindow;

import authoringEnvironment.AESpriteFactory;
import authoringEnvironment.LevelModel;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.SettingsWindow;
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
	//private Map<ViewSprite, >
	private LevelModel myLevelModel;
//    private LevelProperties myLevelProperties;
//    private AnchorPane myNewGamePane = new AnchorPane();

	private void createContextMenu(){
		MenuItem delete = new MenuItem("delete");
		contextMenu.getItems().add(delete);
		delete.setOnAction(event -> {
            ((Pane) getTabContent()).getChildren().remove(currentSprite);
            event.consume();
        });
		contextMenu.setAutoHide(true);
	}

	public GameAuthoringTab(Map<ViewSprite, Sprite> spriteMap, Integer levelID, SettingsWindow window) {
		super(window);
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
		System.out.println("sdsds");
		
		myTab.getTabPane().addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			System.out.println(key.getCode());
			updateViewpoint(key.getCode());		
			key.consume();
		});
	}
	
	private void updateViewpoint(KeyCode code) {
		switch (code) {
			case LEFT:	absoluteX.subtract(1);
				break;
			case RIGHT: absoluteX.add(1);
				break;
			case UP:	absoluteY.subtract(1);
				break;
			case DOWN:	absoluteY.add(1);
				break;
			default:
		}
		
		System.out.println(absoluteX.getValue() + " " + absoluteY.getValue());
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

//        content.setStyle("  -fx-border-width: 1 2 3 4; -fx-border-color: black black black black ;");
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
	public void setViewSprite(ViewSprite view) {
		AESpriteFactory sf = new AESpriteFactory();
		ViewSprite copy = sf.clone(view);
		mySpriteMap.put(copy, sf.makeSprite(copy));
		addWithClicking(copy);
	}

	public void updateSettingsPane(ViewSprite clickedSprite) {
		myWindow.setContent(setSettingsContent(mySpriteMap.get(clickedSprite)));
		
	}

	public void setPlayerViewSprite(ViewSprite viewsprite) {
		AESpriteFactory sf = new AESpriteFactory();
		ViewSprite copy = sf.clone(viewsprite);
		Sprite player = sf.makeSprite(copy);
		player.setUserControlled(true);
		mySpriteMap.put(copy, player);
		addWithClicking(copy);
		
	}
	public LevelModel getLevelModel() {
		// TODO Auto-generated method stub
		return myLevelModel;
	}

}