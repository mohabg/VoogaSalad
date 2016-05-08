package authoringEnvironment.mainWindow;

import authoringEnvironment.AESpriteFactory;
import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;
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
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import resources.FrontEndData;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author davidyan, Huijia Yu
 * Abstract class that contains all methods needed to handle drag and drop and click events of Screens 
 * Includes the Authoring Environment Screen and the Game Player Screen
 */
public abstract class AClickableWindow implements ClipboardOwner {
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	private ViewSprite currentSprite;
	private Map<ISprite, TabPane> mySpriteTabPanes;
	private SettingsWindow myWindow;
	private ObjectEditorController myOEC;
	private Pane myNewGamePane;
	private LevelModel myLevelModel;
	private AESpriteFactory sf;
	private DoubleProperty absoluteX;
	private DoubleProperty absoluteY;
	
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
	
	private EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
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

	private EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
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
	
	public void updateSettingsPane(LevelModel clickedSprite) {
		myWindow.setContent(setSettingsContent(clickedSprite));
	}

	
	public VBox setSettingsContent(ISprite iSprite) {
		VBox myBox = new VBox(FrontEndData.VBOX_SPACING);
		TabPane propertiesPane = new TabPane();
		
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

	public void bindSpritePos(ViewSprite copy, Sprite sprite) {
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
		
		addWithClicking(copy);
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
	
	public void setMyNewGamePane(Pane newPane) {
		myNewGamePane = newPane;
	}
	
	public AESpriteFactory getSpriteFactory() {
		return sf;
	}
	
	public SettingsWindow getMySettingsWindow(){
		return myWindow;
	}
	
	public void setMySettingsWindow(SettingsWindow newWindow) {
		myWindow = newWindow;
	}
	
	public LevelModel getMyLevelModel() {
		return myLevelModel;
	}
	
	public void setMyLevelModel(LevelModel newModel) {
		myLevelModel = newModel;
	}
	
	public ViewSprite getCurrentSprite() {
		return currentSprite;
	}
	
	public void setCurrentSprite(ViewSprite currSprite) {
		currentSprite = currSprite;
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
	
	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		return;
	}

	public abstract void initArea();

	public abstract void setViewSprite(ViewSprite vs);
	
	public abstract ViewSprite initViewSprite(ViewSprite viewsprite);
	
	public abstract void updateSpriteMap(ViewSprite copy, Sprite sprite);

	public abstract void setViewpoint();
	
	public abstract Node getTabContent();
	
	public abstract void updateSettingsPane(ViewSprite clickedSprite);
}
