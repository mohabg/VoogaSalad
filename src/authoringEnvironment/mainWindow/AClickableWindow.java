package authoringEnvironment.mainWindow;

import java.util.HashMap;
import java.util.Map;

import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.Sprite;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import level.LevelProperties;
import resources.FrontEndData;

public abstract class AClickableWindow {
	protected double orgSceneX, orgSceneY;
	protected double orgTranslateX, orgTranslateY;
	protected ViewSprite currentSprite;
	protected Map<Sprite, TabPane> mySpriteTabPanes;
	protected SettingsWindow myWindow;
	protected Pane myNewGamePane;
	
	protected LevelModel myLevelModel;


	public AClickableWindow(SettingsWindow window) {
		myWindow = window;
		mySpriteTabPanes = new HashMap<Sprite, TabPane>();
		myLevelModel = new LevelModel();
		myNewGamePane = new AnchorPane();


	}

	protected EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
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
			// dragSource.setRotate(dragSource.getMySpriteProperties().getMyAngle());
			// dragSource.getMySpriteProperties().setMyX(dragSource.getTranslateX());
			// dragSource.getMySpriteProperties().setMyY(dragSource.getTranslateY());

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

			if (mySprite != currentSprite) {
				currentSprite = mySprite;
				updateSettingsPane(mySprite);
			}
		}
	};

	public abstract void updateSettingsPane(ViewSprite clickedSprite);

	public void updateSettingsPane(LevelModel clickedSprite) {
		myWindow.setContent(setSettingsContent(clickedSprite));
	}

	/**
	 * @param spriteModel
	 *            model used to generate visual elements that are added to a new
	 *            VBox and displayed in the Settings Window
	 */

	public VBox setSettingsContent(Sprite spriteModel) {
		VBox myBox = new VBox(FrontEndData.VBOX_SPACING);
		TabPane propertiesPane = new TabPane();
		if (mySpriteTabPanes.get(spriteModel) != null) {
			propertiesPane = mySpriteTabPanes.get(spriteModel);
		} else {
			propertiesPane = myWindow.getMyVisualFactory().getMyTabs(spriteModel);
			mySpriteTabPanes.put(spriteModel, propertiesPane);
		}
		myBox.getChildren().addAll(propertiesPane);
		return myBox;
	}

	public VBox setSettingsContent(LevelModel myLevel) {
		currentSprite = null;
		VBox myBox = new VBox(FrontEndData.VBOX_SPACING);
		TabPane propertiesList = myWindow.getMyVisualFactory().getMyTabs(myLevel);
		myBox.getChildren().addAll(propertiesList);
		return myBox;
	}

	public void addWithClicking(ViewSprite sprite) {
		setClicking(sprite);
		myNewGamePane.getChildren().addAll(sprite);

	}

	public void setClicking(ViewSprite sprite) {
		sprite.setCursor(Cursor.HAND);

		sprite.setFitHeight(sprite.getImage().getHeight() * 0.5);
		sprite.setFitWidth(sprite.getImage().getWidth() * 0.5);
		sprite.setOnMousePressed(circleOnMousePressedEventHandler);
		sprite.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		sprite.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.getButton() == MouseButton.SECONDARY) {
					myNewGamePane.getChildren().remove(sprite);
				}
				e.consume();
			}
		});
	}

	public Pane getMyNewGamePane() {
		return myNewGamePane;
	}
	
	public void setBackground(String background) {
		myNewGamePane.setStyle("-fx-background-image: url(" + background + ");" + "\n" +
				   "-fx-background-repeat: repeat;");		
	}
	
	public String getBackground() {
		// TODO Auto-generated method stub
		return myNewGamePane.getStyle().replace("-fx-background-image: url(", "").replace(");", "").replace("-fx-background-repeat: repeat;", "").trim();
	}

	public abstract void initArea();

	public abstract void setViewSprite(ViewSprite vs);

}
