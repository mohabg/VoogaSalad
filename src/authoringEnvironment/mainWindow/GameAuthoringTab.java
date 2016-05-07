// This entire file is part of my masterpiece, but is an implementation example only.
// David Yan
// This class extends the AClickableScreen class that represented my masterpiece
// More details about this class can be found in the Analysis writeup

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

/**
 * @author David Yan, Huijia Yu, Joe Jacob
 * Main window in the Authoring Environment that allows users to set up the game to play.
 * Initializes the Sprites to be used in the game.
 */
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