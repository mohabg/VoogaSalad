package authoringEnvironment.mainWindow;

import authoringEnvironment.AESpriteFactory;
import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;

import authoringEnvironment.itemWindow.ImageResizing.DragResizer;
import authoringEnvironment.itemWindow.ImageResizing.IOnDragResizeEventListener;
import authoringEnvironment.settingsWindow.SettingsWindow;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Constants.StylesheetType;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Main.ObjectEditorController;

import gameElements.ISprite;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
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
	protected AESpriteFactory sf;
	protected DoubleProperty absoluteX;
	protected DoubleProperty absoluteY;
	
	public AClickableWindow(SettingsWindow window) {
		sf = new AESpriteFactory();
		myWindow = window;
		mySpriteTabPanes = new HashMap<>();
		myLevelModel = new LevelModel();
		initGamePane();
		initOEC();
		initViewpoint();
	}

	private void initGamePane() {
		myNewGamePane = new AnchorPane();
		setBackground("");
	}
	
	private void initOEC() {
		myOEC = new ObjectEditorController(Arrays.asList("authoringEnvironment", "behaviors", "collisions", "game", "gameElements",
				"gameplayer", "goals", "highscoretable", "HUD", "interfaces", "keyboard", "level",
				"spriteProperties", "events"));
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
			t.consume();
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
			if (t.getButton() != MouseButton.SECONDARY && mySprite != currentSprite) {
				currentSprite = mySprite;
				updateSettingsPane(mySprite);
			}
			t.consume();
		}
	};
	
	public abstract void makeRightClickEvent(ViewSprite mySprite, MouseEvent t);
	
	public abstract void updateSettingsPane(ViewSprite clickedSprite);

	public void initViewpoint() {
		absoluteX = new SimpleDoubleProperty(0);
		absoluteY = new SimpleDoubleProperty(0);
		absoluteX.addListener((o, ov, nv) -> {
			double change = nv.doubleValue() - ov.doubleValue();
			BackgroundImage myIm = getMyNewGamePane().getBackground().getImages().get(0);
			double horPos = myIm.getPosition().getHorizontalPosition();
			horPos = horPos - change/200;
			double verPos = myIm.getPosition().getVerticalPosition();
			Side horSide = myIm.getPosition().getHorizontalSide();
			Side verSide = myIm.getPosition().getVerticalSide();
			boolean horPerc = myIm.getPosition().isHorizontalAsPercentage();
			boolean verPerc = myIm.getPosition().isVerticalAsPercentage();
			BackgroundPosition newPos = new BackgroundPosition(horSide, horPos, horPerc, verSide, verPos, verPerc);
			BackgroundImage newIm = new BackgroundImage(myIm.getImage(), myIm.getRepeatX(), myIm.getRepeatX(), newPos, myIm.getSize());
			getMyNewGamePane().setBackground(new Background(newIm));
		});
		
		absoluteY.addListener((o, ov, nv) -> {
			double change = nv.doubleValue() - ov.doubleValue();
			BackgroundImage myIm = getMyNewGamePane().getBackground().getImages().get(0);
			double horPos = myIm.getPosition().getHorizontalPosition();
			double verPos = myIm.getPosition().getVerticalPosition();
			verPos = verPos - change/200;
			Side horSide = myIm.getPosition().getHorizontalSide();
			Side verSide = myIm.getPosition().getVerticalSide();
			boolean horPerc = myIm.getPosition().isHorizontalAsPercentage();
			boolean verPerc = myIm.getPosition().isVerticalAsPercentage();
			BackgroundPosition newPos = new BackgroundPosition(horSide, horPos, horPerc, verSide, verPos, verPerc);
			BackgroundImage newIm = new BackgroundImage(myIm.getImage(), myIm.getRepeatX(), myIm.getRepeatX(), newPos, myIm.getSize());
			getMyNewGamePane().setBackground(new Background(newIm));
		});
		
	}
	
	public abstract void setViewpoint();
	
	public abstract void updateViewpoint(KeyCode code);
	
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
	
	public abstract ViewSprite initViewSprite(ViewSprite viewsprite);
	
	public abstract void updateSpriteMap(ViewSprite copy, Sprite sprite);
	
	
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
