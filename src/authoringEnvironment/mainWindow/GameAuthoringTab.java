
package authoringEnvironment.mainWindow;

import authoringEnvironment.AESpriteFactory;
import authoringEnvironment.LevelModel;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.Sprite;
import interfaces.ITab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import level.Level;
import level.LevelProperties;
import resources.FrontEndData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author David Yan, Huijia Yu, Joe Jacob
 */
public class GameAuthoringTab extends AClickableWindow implements ITab {

	private Tab myTab;
	private Map<ViewSprite, Sprite> mySpriteMap;
	private Map<Sprite, TabPane> mySpriteTabPanes;
	private ViewSprite currentSprite;
	private SettingsWindow myWindow;
	private ContextMenu contextMenu;
	//private Map<ViewSprite, >
	private LevelModel myLevelModel;
//    private LevelProperties myLevelProperties;
    private AnchorPane myNewGamePane = new AnchorPane();


    private EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            ViewSprite dragSource = (ViewSprite) t.getSource();
            // update x, update y with newTranslate
            dragSource.setX(newTranslateX);
            dragSource.setY(newTranslateY);
            //dragSource.setRotate(dragSource.getMySpriteProperties().getMyAngle());
//            dragSource.getMySpriteProperties().setMyX(dragSource.getTranslateX());
//            dragSource.getMySpriteProperties().setMyY(dragSource.getTranslateY());
        }
	};

	private EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
            ViewSprite mySprite = ((ViewSprite) (t.getSource()));
            orgTranslateX = mySprite.getX();
            orgTranslateY = mySprite.getY();

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            
            if(t.isSecondaryButtonDown()){
				contextMenu.show(myTab.getContent(), t.getScreenX(), t.getScreenY());
			}
            
            if (mySprite != currentSprite) {
            	currentSprite = mySprite;
            	updateSettingsPane(mySprite);
            }
		}
	};
	
	private void createContextMenu(){
		MenuItem delete = new MenuItem("delete");
		contextMenu.getItems().add(delete);
		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				((Pane) getTabContent()).getChildren().remove(currentSprite);
				event.consume();
			}
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
        mySpriteTabPanes = new HashMap<Sprite, TabPane>();
		initArea();
	}
	
	@Override
	public void initArea() {
		ScrollPane myNewGameArea = new ScrollPane();

		Settings.setGamePaneSettings((AnchorPane) myNewGamePane);

		myNewGameArea.setContent(myNewGamePane);
		myNewGamePane.setOnMouseClicked(e -> {
			updateSettingsPane(this.myLevelModel);
		});

		setTabContent(myNewGameArea);
		mySpriteMap.keySet().forEach(c -> addWithClicking(c));
	}

	public List<Sprite> getList() {
		return mySpriteMap.values().stream().collect(Collectors.toList());
	}

	@Override
	public Tab getTab() {
		return myTab;
	}

	@Override
	public Node getTabContent() {
		return myTab.getContent();
	}

	@Override
	public void setTabContent(Node content) {

//        content.setStyle("  -fx-border-width: 1 2 3 4; -fx-border-color: black black black black ;");
        myTab.setContent(content);
    }

	@Override
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
	@Override
	public void setViewSprite(ViewSprite view) {
		AESpriteFactory sf = new AESpriteFactory();
		ViewSprite copy = sf.clone(view);
		mySpriteMap.put(copy, sf.makeSprite(copy));
		addWithClicking(copy);
	}

	@Override
	public void updateSettingsPane(ViewSprite clickedSprite) {
		myWindow.setContent(setSettingsContent(mySpriteMap.get(clickedSprite)));

	}

}