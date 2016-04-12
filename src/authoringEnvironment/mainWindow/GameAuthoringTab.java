package authoringEnvironment.mainWindow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import authoringEnvironment.Model;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.SettingsWindow;
import interfaces.ITab;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameAuthoringTab implements ITab{
	private final double VBOX_SPACING = 8;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	
	private Tab myTab;
	private Map<ViewSprite, Model> mySpriteMap;
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
	
	public GameAuthoringTab(Map<ViewSprite, Model> spriteMap, String title, SettingsWindow window) {
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
	
	public VBox setSettingsContent(Model spriteModel) {
		VBox myBox = new VBox(VBOX_SPACING);
		List<HBox> propertiesList = myWindow.getMyVisualFactory().getHBoxes(spriteModel.getMyPropertiesList());
		myBox.getChildren().addAll(propertiesList);
		return myBox;
	}
	
	private void addWithClicking(ViewSprite sprite){
		sprite.setCursor(Cursor.HAND);
		sprite.setOnMousePressed(circleOnMousePressedEventHandler);
		sprite.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		((Pane) getTabContent()).getChildren().addAll(sprite);
	}
	
	public Map<ViewSprite, Model> getMap(){
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
	public void setTabContent(ViewSprite view, Model model) {
		ViewSprite copy = new ViewSprite(view.getMyImage());
		Model mCopy = new Model(model.getMyRef());

		mySpriteMap.put(copy, mCopy);
		addWithClicking(copy);
	}

}
