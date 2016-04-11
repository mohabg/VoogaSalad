package authoringEnvironment.mainWindow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoringEnvironment.Model;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.SettingsWindow;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameTab extends Tab {
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	private Map<ViewSprite, Model> mySpriteMap;
	private SettingsWindow mySettingsWindow;
	private AnchorPane myNewGamePane;
	
	private final double VBOX_SPACING = 8;
	
	private EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;

			((ImageView) (t.getSource())).setTranslateX(newTranslateX);
			((ImageView) (t.getSource())).setTranslateY(newTranslateY);
		}
	};
	
	private EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			ViewSprite mySprite = ((ViewSprite) (t.getSource()));
			orgTranslateX = mySprite.getTranslateX();
			orgTranslateY = mySprite.getTranslateY();

			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			
			mySettingsWindow.setContent(setSettingsContent(mySpriteMap.get(mySprite)));
		}
	};
	
	public GameTab(String title, Map<ViewSprite, Model> spriteMap, SettingsWindow window) {
		super(title);
		mySpriteMap = spriteMap;
		mySettingsWindow = window;
		myNewGamePane = new AnchorPane();
		initGameTabPane();
		
//		ScrollPane myNewGameArea = new ScrollPane();
//		Settings.setGameAreaSettings(myNewGameArea);
	}

	private void initGameTabPane() {
		Settings.setGamePaneSettings(myNewGamePane);	
		setContent(myNewGamePane);
		mySpriteMap.keySet().forEach(c-> addWithClicking(c));
	}
	
	public VBox setSettingsContent(Model spriteModel) {
		VBox myBox = new VBox(VBOX_SPACING);
		// TODO might be able to remove the settings window dependency if i take away this call
		List<HBox> x = mySettingsWindow.getMyVisualFactory().getHBoxes(spriteModel.getMyPropertiesList());
		myBox.getChildren().addAll(x);
		return myBox;
	}

	public void addToWindow(ViewSprite mySprite, Model myModel) {
		ViewSprite copy = new ViewSprite(mySprite.getMyImagePath());
		Model mCopy = new Model(myModel.getMyImagePath());

		mySpriteMap.put(copy, mCopy);
		addWithClicking(copy);
	}
	
	private void addWithClicking(ViewSprite viewSpriteCopy){
		viewSpriteCopy.setCursor(Cursor.HAND);
		viewSpriteCopy.setOnMousePressed(circleOnMousePressedEventHandler);
		viewSpriteCopy.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		((Pane) getContent()).getChildren().add(viewSpriteCopy);
	}
	
	public Map<ViewSprite, Model> getMap(){
		return mySpriteMap;
	}

}
