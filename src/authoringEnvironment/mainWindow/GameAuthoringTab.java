
package authoringEnvironment.mainWindow;

import authoringEnvironment.AESpriteFactory;
import authoringEnvironment.LevelModel;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.StylesheetType;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Main.ObjectEditorController;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.ISprite;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import resources.FrontEndData;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Arrays;
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
	private Tab myTab;
	private Map<ViewSprite, Sprite> mySpriteMap;
    private AESpriteFactory sf;
    private LevelModel myLevelModel;
    private Map<ISprite, TabPane> currSpriteMap;
    private SettingsWindow mySettingsWindow;
    private ObjectEditorController myOEC;

    public GameAuthoringTab(Map<ViewSprite, Sprite> spriteMap, Integer levelID, SettingsWindow window) {
		super();
        sf = new AESpriteFactory();
        currSpriteMap = new HashMap<>();
        String tabName = FrontEndData.TAB + levelID;
		myTab = new Tab(tabName);
		mySpriteMap = spriteMap;
		mySettingsWindow = window;
        myLevelModel = (new LevelModel());
		initArea();
        initOEC();
	}

    private void initOEC() {
        myOEC = new ObjectEditorController(Arrays.asList(FrontEndData.PACKAGE_NAMES));
        for (StylesheetType type : StylesheetType.values()) {
            myOEC.addObjectStylesheet(type, FrontEndData.STYLESHEET);
        }
    }


    public void initArea() {
		Settings.setGamePaneSettings((AnchorPane) getMyNewGamePane());
		getMyNewGamePane().setOnMouseClicked(e -> {
			updateSettingsPane(myLevelModel);
		});
		setTabContent(getMyNewGamePane());
		mySpriteMap.keySet().forEach(c -> addWithClicking(c));
	}

	private ContextMenu createContextMenu(ViewSprite vs){
		ContextMenu contextMenu = new ContextMenu();
		MenuItem delete = new MenuItem("Delete Sprite");
		MenuItem copy = new MenuItem("Copy Image Ref");
		contextMenu.getItems().addAll(delete, copy);
		delete.setOnAction(event -> {
            ((Pane) myTab.getContent()).getChildren().remove(vs);
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

    public VBox setSettingsContent(LevelModel myLevel) {
        setCurrentNode(null);
        VBox myBox = new VBox(FrontEndData.VBOX_SPACING);
        TabPane propertiesList = myOEC.makeObjectEditorTabPane(myLevel);
        myBox.getChildren().addAll(propertiesList);
        return myBox;
    }

    public VBox setSettingsContent(ISprite iSprite) {
        VBox myBox = new VBox(FrontEndData.VBOX_SPACING);
        TabPane propertiesPane = new TabPane();
        if (currSpriteMap.containsKey(iSprite)) {
            propertiesPane = currSpriteMap.get(iSprite);
        } else {
            propertiesPane = myOEC.makeObjectEditorTabPane(iSprite);
            currSpriteMap.put(iSprite, propertiesPane);
        }
        myBox.getChildren().add(propertiesPane);
        return myBox;
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
    public LevelModel getLevelModel() {
        return myLevelModel;
    }

	
	public void setTabTitle(String tabTitle) {
		myTab.setText(tabTitle);
	}

	public void setCurrentSpriteNull() {
		 setCurrentNode(null);
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


    @Override
    public void clickEvent(Node mySource, double x, double y) {
        ((ImageView) mySource).setX(x);
        ((ImageView) mySource).setY(y);
    }

    @Override
    public void dragEvent(Node mySource) {
        ImageView mySprite = (ImageView) mySource;
        setOrgTranslateX(mySprite.getX());
        setOrgTranslateY(mySprite.getY());
        setCurrentNode((ViewSprite) mySprite);
        updateSettingsPane((ViewSprite) mySprite);
    }

    public void updateSettingsPane(Node clickedSprite) {
		mySettingsWindow.setContent(setSettingsContent(mySpriteMap.get((ViewSprite) clickedSprite)));
	}

    @Override
    public void rightClickEvent(Node currNode, double x, double y) {
        ViewSprite myCurrSprite = (ViewSprite) currNode;
        ContextMenu menu = createContextMenu(myCurrSprite);
        menu.setX(x);
        menu.setY(y);
        menu.setAutoHide(true);
        menu.show(myCurrSprite, x, y);
    }

//	@Override
//	public void makeRightClickEvent(ViewSprite mySprite, MouseEvent t) {
//		if (t.getButton() == MouseButton.SECONDARY) {
//			double xpos = t.getSceneX();
//			double ypos = t.getSceneY();
//			ContextMenu menu = createContextMenu(mySprite);
//			menu.setX(xpos);
//			menu.setY(ypos);
//			menu.setAutoHide(true);
//			menu.show(mySprite, xpos, ypos);
//        }
//        t.consume();
//	}


	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {}


	public void updateSpriteMap(ViewSprite copy, Sprite sprite) {
		mySpriteMap.put(copy, sprite);
	}

    public void updateSettingsPane(LevelModel clickedSprite) {
        mySettingsWindow.setContent(setSettingsContent(clickedSprite));
    }


	public ViewSprite initViewSprite(ViewSprite viewsprite) {
		ViewSprite copy = sf.clone(viewsprite);
		Sprite sprite = sf.makeSprite(copy);
	
		// bind viewpoint
		ISpriteProperties spriteProps = sprite.getSpriteProperties();
		updateSpriteMap(copy, sprite);
		addWithClicking(copy);
		
		return copy;
	}

}