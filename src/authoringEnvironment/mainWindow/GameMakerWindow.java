package authoringEnvironment.mainWindow;
/**
 * @author: davidyan
 */
import authoringEnvironment.Model;
import authoringEnvironment.ViewSprite;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoringEnvironment.settingsWindow.SettingsWindow;
import spriteProperties.NumProperty;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import authoringEnvironment.LevelModel;

public class GameMakerWindow {

	private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	private ScrollPane myGameArea;
	private TabPane myTabPane;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	private AnchorPane myGamePane;
    private SettingsWindow myWindow;
    private Map<ViewSprite, Model> mySpriteMap;

	public GameMakerWindow(){
		myTabPane = new TabPane();
        mySpriteMap = new HashMap<ViewSprite, Model>();
		Tab tab = new Tab();
		tab.setText("Level 1");

		myGameArea = new ScrollPane();
		myGameArea.setFitToWidth(true);
		myGameArea.setPrefHeight(SCREEN_HEIGHT);
		myGameArea.setPrefWidth(0.4*SCREEN_WIDTH);

		myGamePane = new AnchorPane();
		myGamePane.getStyleClass().add("pane");

		myGamePane.setPrefWidth(0.3*SCREEN_WIDTH);
		myGamePane.setPrefHeight(SCREEN_HEIGHT);

		tab.setContent(myGamePane);


		myTabPane.getTabs().add(tab);

	}
    public void init(SettingsWindow window){
        myWindow = window;
    }

	public void addNewTab(){
		Tab myTab = new Tab("Level " + (myTabPane.getTabs().size() + 1));
		ScrollPane myNewGameArea = new ScrollPane();
		myNewGameArea.setFitToWidth(true);
		myNewGameArea.setPrefHeight(SCREEN_HEIGHT);
		myNewGameArea.setPrefWidth(0.4*SCREEN_WIDTH);

		AnchorPane myNewGamePane = new AnchorPane();
		myNewGamePane.getStyleClass().add("pane");

		myNewGamePane.setPrefWidth(0.3*SCREEN_WIDTH);
		myNewGamePane.setPrefHeight(SCREEN_HEIGHT);

		myTab.setContent(myNewGamePane);
		myTabPane.getTabs().add(myTab);
		myTabPane.getSelectionModel().select(myTab);

	}

	EventHandler<MouseEvent> circleOnMousePressedEventHandler =
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					orgSceneX = t.getSceneX();
					orgSceneY = t.getSceneY();
                    ViewSprite mySprite = ((ViewSprite) (t.getSource()));
					orgTranslateX = mySprite.getTranslateX();
					orgTranslateY = mySprite.getTranslateY();
                    System.out.println(mySprite.getImage());

                    myWindow.setContent(setSettingsContent(mySpriteMap.get(mySprite)));


                    //mySprite.setSettingsContent(myWindow);
                    //myWindow.setContent(settingsPropertyFactory.makeNewThing(ViewSprite's model'));
				}
			};

    public VBox setSettingsContent(Model spriteModel){
        VBox myBox = new VBox(8);
        for(NumProperty aProp: spriteModel.getMyPropertiesList()){
            HBox myTempBox = new HBox();
            javafx.scene.control.Label myLabel = new javafx.scene.control.Label(aProp.toString());
            Slider mySlider = new Slider(0,100,aProp.getMyValue());
//            mySlider.setMin(0);
//            mySlider.setMax(100);
            mySlider.setShowTickMarks(true);
            mySlider.setShowTickLabels(true);

            mySlider.valueProperty().bindBidirectional(aProp.getDouble());
//            mySlider.valueProperty().addListener(new ChangeListener<Number>() {
//                public void changed(ObservableValue<? extends Number> ov,
//                                    Number old_val, Number new_val) {
//                    aProp.setMyValue((double) new_val);
//                }
//            });
            myTempBox.getChildren().addAll(myLabel, mySlider);
            myBox.getChildren().add(myTempBox);
        }

        return myBox;
    }

	EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					double offsetX = t.getSceneX() - orgSceneX;
					double offsetY = t.getSceneY() - orgSceneY;
					double newTranslateX = orgTranslateX + offsetX;
					double newTranslateY = orgTranslateY + offsetY;

					((ImageView)(t.getSource())).setTranslateX(newTranslateX);
					((ImageView)(t.getSource())).setTranslateY(newTranslateY);
				}
			};


	public TabPane getMainWindow(){
		return myTabPane;
	}

	public void addToWindow(ViewSprite mySprite, Model myModel){
		ViewSprite copy = new ViewSprite(mySprite.getMyImage());
        Model mCopy = new Model();
        
        System.out.println(copy.getMyImage() +  " " + copy.getTranslateX() + " " + copy.getY() + " " + copy.getFitWidth() + " " + copy.getFitHeight());
		//copy.setImage(mySprite.getImage());
//		currSprite = copy;
		copy.setCursor(Cursor.HAND);

        mySpriteMap.put(copy, mCopy);

		copy.setOnMousePressed(circleOnMousePressedEventHandler);
		copy.setOnMouseDragged(circleOnMouseDraggedEventHandler);
//		System.out.println(myTabPane.getSelectionModel().getSelectedItem().getContent());
		AnchorPane myPane = (AnchorPane) myTabPane.getSelectionModel().getSelectedItem().getContent();
		myPane.getChildren().addAll(copy);
	}

	public void populateEditingFromSave(List<LevelModel> gameLevels) {
		
	}
	
    public Map<ViewSprite, Model> getMap(){
        return mySpriteMap;
    }

    public TabPane getMyTabPane(){
        return myTabPane;
    }
}