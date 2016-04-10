package authoringEnvironment.mainWindow;

/**
 * @author: davidyan
 */
import authoringEnvironment.LevelModel;
import authoringEnvironment.Model;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.itemWindow.ItemWindowData;
import authoringEnvironment.settingsWindow.SettingsWindow;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import spriteProperties.Behavior;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMakerWindow {
    private Settings settings;
    private TabPane myTabPane;
    private SettingsWindow myWindow;

    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private Map<ViewSprite, Model> mySpriteMap;

    public GameMakerWindow() {
        settings = new Settings();
        myTabPane = new TabPane();
        mySpriteMap = new HashMap<ViewSprite, Model>();
        addNewTab();

    }

    public void init(SettingsWindow window) {
        myWindow = window;
    }

    public void addNewTab() {
        Tab myTab = new Tab(ItemWindowData.TAB + (myTabPane.getTabs().size() + 1));

        ScrollPane myNewGameArea = new ScrollPane();
        settings.setGameAreaSettings(myNewGameArea);

        AnchorPane myNewGamePane = new AnchorPane();
        settings.setGamePaneSettings(myNewGamePane);

        myTab.setContent(myNewGamePane);
        myTabPane.getTabs().add(myTab);
        myTabPane.getSelectionModel().select(myTab);

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
        VBox myPictureBox = new VBox();
        myPictureBox.setPadding(new Insets(20,0,0,0));
        ImageView myImage = new ImageView(new Image(spriteModel.getMyRef()));
        myPictureBox.getChildren().add(myImage);
        myPictureBox.setAlignment(Pos.CENTER);
        myBox.getChildren().add(myPictureBox);
        List<Behavior> myList = spriteModel.getMyPropertiesList();
        List<HBox> myBoxes = myWindow.getMyVisualFactory().getHBoxes(myList);
        myBox.getChildren().addAll(myBoxes);
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

    public TabPane getMainWindow() {
        return myTabPane;
    }


    public void addToWindow(ViewSprite mySprite, Model myModel){
        ViewSprite copy = new ViewSprite(mySprite.getMyImage());
        Model mCopy = new Model(myModel.getMyRef());
        copy.setCursor(Cursor.HAND);

        mySpriteMap.put(copy, mCopy);

        copy.setOnMousePressed(circleOnMousePressedEventHandler);
        copy.setOnMouseDragged(circleOnMouseDraggedEventHandler);
        AnchorPane myPane = (AnchorPane) myTabPane.getSelectionModel().getSelectedItem().getContent();
        myPane.getChildren().addAll(copy);
    }

    public void populateEditingFromSave(List<LevelModel> gameLevels) {

    }

    public Map<ViewSprite, Model> getMap(){
        return mySpriteMap;
    }

    public TabPane getMyTabPane() {
        return myTabPane;
    }
}