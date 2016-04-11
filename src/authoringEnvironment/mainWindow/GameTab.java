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
	private SettingsWindow myWindow;

	public GameTab(Map<ViewSprite, Model> mySpriteMap, String title, SettingsWindow window) {
		super(title);
		this.mySpriteMap = mySpriteMap;
		myWindow = window;

		ScrollPane myNewGameArea = new ScrollPane();
		Settings.setGameAreaSettings(myNewGameArea);

		AnchorPane myNewGamePane = new AnchorPane();
		Settings.setGamePaneSettings(myNewGamePane);

		setContent(myNewGamePane);
		mySpriteMap.keySet().forEach(c-> addWithClicking(c));

	}

	EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			ViewSprite mySprite = ((ViewSprite) (t.getSource()));
			orgTranslateX = mySprite.getTranslateX();
			orgTranslateY = mySprite.getTranslateY();

			myWindow.setContent(setSettingsContent(mySpriteMap.get(mySprite)));

		}
	};

	public VBox setSettingsContent(Model spriteModel) {
		VBox myBox = new VBox(8);
		List<HBox> x = myWindow.getMyVisualFactory().getHBoxes(spriteModel.getMyPropertiesList());
		System.out.println(x.size());
		myBox.getChildren().addAll(x);
		return myBox;
	}

	EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
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

	public void addToWindow(ViewSprite mySprite, Model myModel) {
		ViewSprite copy = new ViewSprite(mySprite.getMyImage());
		Model mCopy = new Model(myModel.getMyRef());

		System.out.println(copy.getMyImage() + " " + copy.getTranslateX() + " " + copy.getY() + " " + copy.getFitWidth()
				+ " " + copy.getFitHeight());
		// copy.setImage(mySprite.getImage());
		// currSprite = copy;

		mySpriteMap.put(copy, mCopy);
		addWithClicking(copy);
		
	}
	private void addWithClicking(ViewSprite copy){
		copy.setCursor(Cursor.HAND);
		copy.setOnMousePressed(circleOnMousePressedEventHandler);
		copy.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		// System.out.println(myTabPane.getSelectionModel().getSelectedItem().getContent());
		((Pane) getContent()).getChildren().addAll(copy);
	}

}
