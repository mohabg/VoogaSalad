package authoringEnvironment;

import authoringEnvironment.mainWindow.AClickableWindow;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.StylesheetType;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Main.ObjectEditorController;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.ISprite;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameplayer.PlayScreen;
import interfaces.IGameWindow;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import resources.FrontEndData;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author davidyan, Joe Jacob, Huijia Yu
 * LiveEditing window that retains functionality in the Authoring Environment to allow user to edit while playing
 *
 */
public class LiveEditing extends AClickableWindow implements IGameWindow {
	PlayScreen ps;
    private LevelModel myLevelModel;
    private Map<ISprite, TabPane> mySpriteMap;
    private SettingsWindow myWindow;
    private ObjectEditorController myOEC;


    public LiveEditing(PlayScreen myPlayScreen, SettingsWindow window) {
		super();
		ps = myPlayScreen;
		myLevelModel = (new LevelModel(ps.getCurrentLevel()));
        mySpriteMap = new HashMap<>();
        myWindow = window;
		setMyNewGamePane(ps.getScreen().getPane());
		getMyNewGamePane().getChildren().removeAll(ps.getViewSprites().values());
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
		Settings.setGamePaneSettings(getMyNewGamePane());
        getMyNewGamePane().setOnMouseClicked(e -> {
			updateSettingsPane(myLevelModel);
		});
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


    @Override
	public void updateSettingsPane(Node clickedSprite) {
		Integer ID = null;
		for(Integer i : ps.getViewSprites().keySet()) {
			if (clickedSprite.equals(ps.getViewSprites().get(i))) {
				ID = i;
				break;
			}
		}

		myWindow.setContent(setSettingsContent(ps.getSprites().get(ID)));
	}

    public VBox setSettingsContent(ISprite iSprite) {
        VBox myBox = new VBox(FrontEndData.VBOX_SPACING);
        TabPane propertiesPane = new TabPane();
        if (mySpriteMap.containsKey(iSprite)) {
            propertiesPane = mySpriteMap.get(iSprite);
        } else {
            propertiesPane = myOEC.makeObjectEditorTabPane(iSprite);
            mySpriteMap.put(iSprite, propertiesPane);
        }
        myBox.getChildren().add(propertiesPane);
        return myBox;
    }

    public VBox setSettingsContent(LevelModel myLevel) {
		setCurrentNode(null);
		VBox myBox = new VBox(FrontEndData.VBOX_SPACING);
		TabPane propertiesList = myOEC.makeObjectEditorTabPane(myLevel);
		myBox.getChildren().addAll(propertiesList);
		return myBox;
	}

	public PlayScreen getPlayScreen(){
		return ps;
	}
	
	@Override
	public void setPlayerViewSprite(ViewSprite viewsprite) {
		setViewSprite(viewsprite);
	}

    @Override
    public void rightClickEvent(Node currNode, double x, double y) {

    }

    public void updateSpriteMap(ViewSprite copy, Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	public ViewSprite initViewSprite(ViewSprite viewsprite) {
		// bind viewpoint
		ISpriteProperties spriteProps = viewsprite.getMySpriteProperties();
		
		addWithClicking(viewsprite);
		
		return viewsprite;
	}


    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {

    }

    public void updateSettingsPane(LevelModel clickedSprite) {
        myWindow.setContent(setSettingsContent(clickedSprite));
    }
}
