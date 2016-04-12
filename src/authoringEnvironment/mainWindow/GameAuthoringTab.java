package authoringEnvironment.mainWindow;

import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.Sprite;
import interfaces.ITab;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Map;

public class GameAuthoringTab implements ITab{
	private final double VBOX_SPACING = 8;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	
	private Tab myTab;
	private Map<ViewSprite, Sprite> mySpriteMap;
	private SettingsWindow myWindow;
	
	
	private EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;

			ImageView dragSource = (ImageView) t.getSource();
			dragSource.setTranslateX(newTranslateX);
			dragSource.setTranslateY(newTranslateY);
		}
	};
	
	private EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			ImageView mySprite = ((ImageView) (t.getSource()));
			orgTranslateX = mySprite.getTranslateX();
			orgTranslateY = mySprite.getTranslateY();
			
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();

			updateSettingsPane((ViewSprite) mySprite);
		}
	};
	
	public GameAuthoringTab(Map<ViewSprite, Sprite> spriteMap, String title, SettingsWindow window) {
		myTab = new Tab(title);
		mySpriteMap = spriteMap;
		myWindow = window;
		
		initArea();
	}

	private void initArea() {
		ScrollPane myNewGameArea = new ScrollPane();
		Settings.setGameAreaSettings(myNewGameArea);

		AnchorPane myNewGamePane = new AnchorPane();
		Settings.setGamePaneSettings(myNewGamePane);

		setTabContent(myNewGamePane);
		mySpriteMap.keySet().forEach(c-> addWithClicking(c));
	}
	
	private void updateSettingsPane(ViewSprite clickedSprite) {
		myWindow.setContent(setSettingsContent(mySpriteMap.get(clickedSprite)));
	}
	
	public VBox setSettingsContent(Sprite spriteModel) {
		VBox myBox = new VBox(VBOX_SPACING);
		TabPane propertiesList = myWindow.getMyVisualFactory().getMyTabs(spriteModel);
		myBox.getChildren().addAll(propertiesList);
		return myBox;
	}
	
	private void addWithClicking(ViewSprite sprite){
		sprite.setCursor(Cursor.HAND);
		sprite.setOnMousePressed(circleOnMousePressedEventHandler);
		sprite.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		((Pane) getTabContent()).getChildren().addAll(sprite);
	}
	
	public Map<ViewSprite, Sprite> getMap(){
		return mySpriteMap;
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
		myTab.setContent(content);
		
	}

	@Override
	public void setTabTitle(String tabTitle) {
		myTab.setText(tabTitle);
	}

	@Override
	public void setTabContent(ViewSprite view, Sprite sprite) {
		ViewSprite copy = new ViewSprite(view.getMyImage());
		Sprite mCopy = new Sprite(sprite.getMyRef());

		mySpriteMap.put(copy, mCopy);
		addWithClicking(copy);
	}

}
