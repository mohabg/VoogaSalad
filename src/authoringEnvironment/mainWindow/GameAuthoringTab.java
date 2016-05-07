// This entire file is part of my masterpiece, but is an implementation example only.
// David Yan
// This class extends the abstract AClickableScreen masterpiece class
// The Code Masterpiece allowed us to define very clear and custom events for the classes that extended this class in our
// project. One particular class that used this abstract window, or screen, class was the GameAuthoringTab class
// (shown below) that has a screen embedded within a Tab.
//
// As you can see below, the types of custom objects we were adding to the screen were ViewSprites within our project.
// The initViewSprite() method was the main method that created our ViewSprites, and initArea() method was the main
// method that added all of our sprites to the abstract screen class, so that any Nodes added through the
// addWithClicking() method called within that method were automatically added to the screen and given basic drag and
// drop functionality.
// From there, custom methods were written for what should happen to the Node and the screen during a click event,
// a drag event, and even a right-click event.
//
// All I had to really do for defining custom click/drag events was just override the clickEvent, rightClickEvent, and
// dragEvent methods to define custom events for my project when a node is clicked, right clicked, or dragged.
// For example, during a click event I decided use our group’s custom VisualFactory calls to populate the settings window
// with the clicked Viewsprite’s information. For right clicks, I decided to create a context menu that allowed the user
// to create a popup menu with an option to copy the image reference of the image. While my GameAuthoringTab example for
// extending my AClickableScreen class had its own unique usages, other groups using AClickableScreen will be able to
// create their own methods to handle click/drag events that could be completely different from mine and add different
// objects to the screen due to the flexibility of the AClickableScreen design.

package authoringEnvironment.mainWindow;

import authoringEnvironment.AESpriteFactory;
import authoringEnvironment.LevelModel;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.ISprite;
import gameElements.Sprite;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import resources.FrontEndData;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameAuthoringTab extends AClickableScreen {
    private VisualManager visualManager;
    private Tab myTab;
    private Map<ViewSprite, Sprite> mySpriteMap;
    private LevelModel myLevelModel;
    private Map<ISprite, TabPane> currSpriteMap;
    private SettingsWindow mySettingsWindow;

    public GameAuthoringTab(Map<ViewSprite, Sprite> spriteMap, Integer levelID, SettingsWindow window) {
		super();
        currSpriteMap = new HashMap<>();
		myTab = new Tab(FrontEndData.TAB + levelID);
		mySpriteMap = spriteMap;
		mySettingsWindow = window;
        myLevelModel = new LevelModel();
        visualManager = new VisualManager();
		initArea();
	}

    public void initArea() {
		Settings.setGamePaneSettings(getMyNewGamePane());
		getMyNewGamePane().setOnMouseClicked(e -> {
			updateSettingsPane(myLevelModel);
		});
		myTab.setContent(getMyNewGamePane());
		mySpriteMap.keySet().forEach(c -> addWithClicking(c));
	}

    public ViewSprite initViewSprite(ViewSprite viewsprite) {
        AESpriteFactory sf = new AESpriteFactory();
        ViewSprite copy = sf.clone(viewsprite);
        Sprite sprite = sf.makeSprite(copy);
        mySpriteMap.put(copy, sprite);
        addWithClicking(copy);
        return copy;
    }

    @Override
    public void rightClickEvent(Node currNode, double x, double y) {
        ViewSprite myCurrSprite = (ViewSprite) currNode;
        ContextMenu menu = visualManager.createContextMenu(myCurrSprite);
        menu.setX(x);
        menu.setY(y);
        menu.setAutoHide(true);
        menu.show(myCurrSprite, x, y);
    }

    @Override
    public void clickEvent(Node mySource, double x, double y) {
        ((ViewSprite) mySource).setX(x);
        ((ViewSprite) mySource).setY(y);
    }

    @Override
    public void dragEvent(Node mySource) {
        ViewSprite mySprite = (ViewSprite) mySource;
        setOrgTranslateX(mySprite.getX());
        setOrgTranslateY(mySprite.getY());
        setCurrentNode(mySprite);
        updateSettingsPane(mySprite);
    }
	public List<Sprite> getList() {return mySpriteMap.values().stream().collect(Collectors.toList());}

	public Tab getTab() {return myTab;}
	
	public LevelModel getLevelModel() {return myLevelModel;}

	public void setTabTitle(String tabTitle) {myTab.setText(tabTitle);}

	public void setCurrentSpriteNull() {setCurrentNode(null);}

	public void setViewSprite(ViewSprite viewsprite) {initViewSprite(viewsprite);}

	public void setPlayerViewSprite(ViewSprite viewsprite) {initViewSprite(viewsprite);}
	
	public void updateSettingsPane(Node clickedSprite) {mySettingsWindow.setContent(visualManager.setSettingsContent(mySpriteMap.get(clickedSprite), currSpriteMap));}
	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {}
	
	public void updateSettingsPane(LevelModel clickedSprite) {mySettingsWindow.setContent(visualManager.setSettingsContent(clickedSprite));}
}
