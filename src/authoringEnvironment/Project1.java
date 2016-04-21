package authoringEnvironment;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.Sprite;
import gameplayer.GameLoader;
import gameplayer.PlayScreen;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import level.LevelProperties;
import resources.FrontEndData;

public class Project1 {
	PlayScreen ps;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;

//	private Tab myTab;
//	private Map<ViewSprite, Sprite> mySpriteMap;
	private Map<Sprite, TabPane> mySpriteTabPanes;
	private ViewSprite currentSprite;
	private SettingsWindow myWindow;
	//private Map<ViewSprite, >
//	private LevelModel myLevelModel;
//    private LevelProperties myLevelProperties;
    private Pane myNewGamePane;
	
    
    
	public Project1(File newGameFile,SettingsWindow window){
		ps = (PlayScreen) GameLoader.newGame(newGameFile); 
		myWindow = window;
        myNewGamePane = ps.getPane();
        myNewGamePane.getChildren().removeAll(ps.getViewSprites().values());
        mySpriteTabPanes = new HashMap<Sprite, TabPane>();
		initArea();
	}
	


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


	private void initArea() {
		ScrollPane myNewGameArea = new ScrollPane();
//		Settings.setGameAreaSettings(myNewGameArea);

		//AnchorPane myNewGamePane = new AnchorPane();

        myNewGameArea.setContent(myNewGamePane);
//        myNewGamePane.setOnMouseClicked(e->{
//            updateSettingsPane(this.myLevelModel);
//        });

		ps.getViewSprites().values().forEach(c-> addWithClicking(c));
	}

	private void updateSettingsPane(ViewSprite clickedSprite) {
		Integer ID = null;
		for(Integer i: ps.getViewSprites().keySet()){
			if(clickedSprite.equals(ps.getViewSprites().get(i))){
				ID= i;
				break;
			}
		}
		
		myWindow.setContent(setSettingsContent(ps.getSprites().get(ID)));
	}

    private void updateSettingsPane(LevelModel clickedSprite) {
        myWindow.setContent(setSettingsContent(clickedSprite));
    }

    /**
     * @param spriteModel model used to generate visual elements that
     * are added to a new VBox and displayed in the Settings Window
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

	private void addWithClicking(ViewSprite sprite){
		sprite.setCursor(Cursor.HAND);

		sprite.setFitHeight(sprite.getImage().getHeight()*0.5);
		sprite.setFitWidth(sprite.getImage().getWidth()*0.5);
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

        myNewGamePane.getChildren().addAll(sprite);
        
	}
	
	public Pane getPane(){
		return ps.getPane();
	}
	
	public void setKeys(){
		ps.setKeys();
	}

}
