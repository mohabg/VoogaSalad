package authoringEnvironment.mainWindow;

import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;

import authoringEnvironment.itemWindow.ImageResizing.DragResizer;
import authoringEnvironment.itemWindow.ImageResizing.IOnDragResizeEventListener;
import authoringEnvironment.settingsWindow.SettingsWindow;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.StylesheetType;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Main.ObjectEditorController;

import gameElements.ISprite;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import resources.FrontEndData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author davidyan, Huijia Yu
 * Abstract class that contains all methods needed to handle drag and drop and click events of Screens 
 * Includes the Authoring Environment Screen and the Game Player Screen
 */
public abstract class AClickableWindow {
	protected double orgSceneX, orgSceneY;
	protected double orgTranslateX, orgTranslateY;
	protected ViewSprite currentSprite;
	protected Map<ISprite, TabPane> mySpriteTabPanes;
	protected SettingsWindow myWindow;
	protected ObjectEditorController myOEC;
	protected Pane myNewGamePane;
	protected LevelModel myLevelModel;

	public AClickableWindow(SettingsWindow window) {
		myWindow = window;
		mySpriteTabPanes = new HashMap<>();
		myLevelModel = new LevelModel();
		initGamePane();
		myOEC = new ObjectEditorController(Arrays.asList("authoringEnvironment", "behaviors", "collisions", "game", "gameElements",
				"gameplayer", "goals", "highscoretable", "HUD", "interfaces", "keyboard", "level",
				"spriteProperties", "events"));
		initOEC();
	}

	private void initGamePane() {
		myNewGamePane = new AnchorPane();
		setBackground("");
	}

	
	private void initOEC() {
		for (StylesheetType type : StylesheetType.values()) {
			myOEC.addObjectStylesheet(type, FrontEndData.STYLESHEET);
		}
	}
	
	protected EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;
			ViewSprite dragSource = (ViewSprite) t.getSource();
			dragSource.setX(newTranslateX);
			dragSource.setY(newTranslateY);
		}
	};

	protected EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			ViewSprite mySprite = ((ViewSprite) (t.getSource()));
			orgTranslateX = mySprite.getX();
			orgTranslateY = mySprite.getY();
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			if (!t.isSecondaryButtonDown() && mySprite != currentSprite) {
				currentSprite = mySprite;
				updateSettingsPane(mySprite);
			}
		}
	};
	
	public abstract void makeRightClickEvent(ViewSprite mySprite, MouseEvent t);
	

	public abstract void updateSettingsPane(ViewSprite clickedSprite);

	public void updateSettingsPane(LevelModel clickedSprite) {
		myWindow.setContent(setSettingsContent(clickedSprite));
	}

	/**
	 * @param iSprite
	 *            model used to generate visual elements that are added to a new
	 *            VBox and displayed in the Settings Window
	 */

	public VBox setSettingsContent(ISprite iSprite) {
		VBox myBox = new VBox(FrontEndData.VBOX_SPACING);
		TabPane propertiesPane = new TabPane();
        //mySpriteTabPanes.get(spriteModel) != null
		if (mySpriteTabPanes.containsKey(iSprite)) {
			propertiesPane = mySpriteTabPanes.get(iSprite);
		} else {
			propertiesPane = myOEC.makeObjectEditorTabPane(iSprite);
			mySpriteTabPanes.put(iSprite, propertiesPane);
		}
		myBox.getChildren().add(propertiesPane);
		return myBox;
	}

	public VBox setSettingsContent(LevelModel myLevel) {
		currentSprite = null;
		VBox myBox = new VBox(FrontEndData.VBOX_SPACING);
		TabPane propertiesList = myOEC.makeObjectEditorTabPane(myLevel);
		myBox.getChildren().addAll(propertiesList);
		return myBox;
	}

	public void addWithClicking(ViewSprite sprite) {
		setClicking(sprite);
		myNewGamePane.getChildren().add(sprite);
	}

	public void setClicking(ViewSprite sprite) {
		sprite.setCursor(Cursor.HAND);
		sprite.setOnMousePressed(circleOnMousePressedEventHandler);
		sprite.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		sprite.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
           makeRightClickEvent(sprite, e);
        });
	}

	public Pane getMyNewGamePane() {
		return myNewGamePane;
	}
	
	public void setBackground(String background) {
		if (background == "") {
			myNewGamePane.setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), null, null)));		
		} else {
			myNewGamePane.setBackground(new Background(new BackgroundImage(new Image(background), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		}
		
		myLevelModel.setBackground(background);
	}
	
	public StringProperty getBackground() {
		return myLevelModel.getBackground();
	}

	public abstract void initArea();

	public abstract void setViewSprite(ViewSprite vs);

}
