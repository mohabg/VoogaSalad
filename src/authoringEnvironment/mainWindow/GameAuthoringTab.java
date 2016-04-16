package authoringEnvironment.mainWindow;

import authoringEnvironment.RefObject;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.Sprite;
import interfaces.ITab;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Map;
/**
 * @author David Yan, Huijia Yu, Joe Jacob
 */
public class GameAuthoringTab implements ITab{
	private final double VBOX_SPACING = 8;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;

	private Tab myTab;
	private Map<ViewSprite, Sprite> mySpriteMap;
	private SettingsWindow myWindow;
	//private Map<ViewSprite, >

	private EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
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
            //dragSource.setRotate(dragSource.getMySpriteProperties().getMyAngle());
//            dragSource.getMySpriteProperties().setMyX(dragSource.getTranslateX());
//            dragSource.getMySpriteProperties().setMyY(dragSource.getTranslateY());

        }
	};

	private EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
            ImageView mySprite = ((ViewSprite) (t.getSource()));
            orgTranslateX = mySprite.getX();
            orgTranslateY = mySprite.getY();
            
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

            updateSettingsPane((ViewSprite) mySprite);
            
            if(t.isSecondaryButtonDown()){
				ContextMenu contextMenu = new ContextMenu();
				MenuItem delete = new MenuItem("Delete");
				contextMenu.getItems().add(delete);
				System.out.println("delete");
				//TO DO: make the delete option actually appear
			}
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

    /**
     * @param spriteModel model used to generate visual elements that
     * are added to a new VBox and displayed in the Settings Window
     */

	public VBox setSettingsContent(Sprite spriteModel) {
		VBox myBox = new VBox(VBOX_SPACING);
		TabPane propertiesList = myWindow.getMyVisualFactory().getMyTabs(spriteModel);
		myBox.getChildren().addAll(propertiesList);
		return myBox;
	}

	private void addWithClicking(ViewSprite sprite){
		sprite.setCursor(Cursor.HAND);
		
		sprite.setFitHeight(sprite.getImage().getHeight()*0.5);
		sprite.setFitWidth(sprite.getImage().getWidth()*0.5);
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
        content.setStyle("  -fx-border-width: 1 2 3 4; -fx-border-color: black black black black ;");
        myTab.setContent(content);

	}

	@Override
	public void setTabTitle(String tabTitle) {
		myTab.setText(tabTitle);
	}

    /**
     * @param view is a ViewSprite that's going to be copied and get its properties set between the
     * Sprite properties.
     * @param sprite Sprite properties are bound to ViewSprite coordinate variables such that when one
     * change is made, the other knows of the change
     */
	@Override
	public void setTabContent(ViewSprite view, Sprite sprite) {
		ViewSprite copy = new ViewSprite(view.getMyImage());
		Sprite mCopy = new Sprite(sprite.getSpriteProperties(), sprite.getHealth(), sprite.getCollisions(), sprite.getBehaviors(), new RefObject(sprite.getMyRef()));
		
        copy.xProperty().bindBidirectional(mCopy.getX());
        copy.yProperty().bindBidirectional(mCopy.getY());
        copy.fitHeightProperty().bindBidirectional(mCopy.getHeight());
        copy.fitWidthProperty().bindBidirectional(mCopy.getWidth());
        copy.rotateProperty().bindBidirectional(mCopy.getAngle());
        copy.stringRefProperty().bindBidirectional(mCopy.getMyStringRef());
        copy.imageProperty().bindBidirectional(mCopy.getMyImageProp());
        
		mySpriteMap.put(copy, mCopy);
		addWithClicking(copy);
	}

}
