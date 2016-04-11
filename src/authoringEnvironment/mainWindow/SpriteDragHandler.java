package authoringEnvironment.mainWindow;

import authoringEnvironment.ViewSprite;
import interfaces.ITabPane;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SpriteDragHandler {
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	private ITabPane myTabPane;
	
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

			myTabPane.setContent(setSettingsContent(mySpriteMap.get(mySprite)));
		}
	};
	
	public SpriteDragHandler(ITabPane tabPane) {
		myTabPane = tabPane;
	}
}
