package authoringEnvironment.mainWindow;

import authoringEnvironment.AESpriteFactory;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.Sprite;
import interfaces.ITab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import level.LevelProperties;
import resources.FrontEndData;

import java.util.HashMap;
import java.util.Map;
/**
 * @author David Yan, Huijia Yu, Joe Jacob
 */
public class GameAuthoringTab implements ITab{
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;

	private Tab myTab;
	private Map<ViewSprite, Sprite> mySpriteMap;
	private Map<Sprite, TabPane> mySpriteTabPanes;
	private ViewSprite currentSprite;
	private SettingsWindow myWindow;
	private ContextMenu contextMenu;
	//private Map<ViewSprite, >
    private LevelProperties myLevelProperties;
    private AnchorPane myNewGamePane = new AnchorPane();


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
            
            if(t.isSecondaryButtonDown()){
				contextMenu.show(myTab.getContent(), t.getScreenX(), t.getScreenY());
			}
            
            if (mySprite != currentSprite) {
            	currentSprite = mySprite;
            	updateSettingsPane(mySprite);
            }
		}
	};
	
	private void createContextMenu(){
		MenuItem delete = new MenuItem("delete");
		contextMenu.getItems().add(delete);
		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				((Pane) getTabContent()).getChildren().remove(currentSprite);
				event.consume();
			}
		});
		contextMenu.setAutoHide(true);
	}

	public GameAuthoringTab(Map<ViewSprite, Sprite> spriteMap, String title, SettingsWindow window) {
		myTab = new Tab(title);
		mySpriteMap = spriteMap;
		myWindow = window;
<<<<<<< HEAD
		contextMenu = new ContextMenu();
		createContextMenu();
=======
        myLevelProperties = new LevelProperties();
        mySpriteTabPanes = new HashMap<Sprite, TabPane>();
>>>>>>> 9ad368ba9ee8ac269278592494ab95904322f4fc
		initArea();
	}

	private void initArea() {
		ScrollPane myNewGameArea = new ScrollPane();
<<<<<<< HEAD
		Settings.setGameAreaSettings(myNewGameArea);
		myNewGameArea.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myNewGameArea.setVvalue(myNewGameArea.getPrefHeight());
		
		AnchorPane myNewGamePane = new AnchorPane();
		Settings.setGamePaneSettings(myNewGamePane);
		
		Button addButton = new Button();
		addButton.setText("+");
		addButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				myNewGamePane.setPrefHeight(myNewGamePane.getPrefHeight() + 100);
			}
		});
		
		Button minusButton = new Button();
		minusButton.setText("-");
		minusButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				myNewGamePane.setPrefHeight(myNewGamePane.getPrefHeight() - 100);
			}
		});
		
		HBox buttonHBox = new HBox();
		buttonHBox.getChildren().addAll(minusButton, addButton);
		
		myNewGamePane.getChildren().add(buttonHBox);
		myNewGameArea.setContent(myNewGamePane);
		
=======
//		Settings.setGameAreaSettings(myNewGameArea);

		//AnchorPane myNewGamePane = new AnchorPane();
		Settings.setGamePaneSettings(myNewGamePane);

        myNewGameArea.setContent(myNewGamePane);
        myNewGamePane.setOnMouseClicked(e->{
            updateSettingsPane(this.myLevelProperties);
        });


>>>>>>> 9ad368ba9ee8ac269278592494ab95904322f4fc
		setTabContent(myNewGameArea);
		mySpriteMap.keySet().forEach(c-> addWithClicking(c));
		
		myTab.getContent().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
			}
		});
	}

	private void updateSettingsPane(ViewSprite clickedSprite) {
		myWindow.setContent(setSettingsContent(mySpriteMap.get(clickedSprite)));
	}

    private void updateSettingsPane(LevelProperties clickedSprite) {
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

    public VBox setSettingsContent(LevelProperties myLevelProperties) {
    	currentSprite = null;
        VBox myBox = new VBox(FrontEndData.VBOX_SPACING);
        TabPane propertiesList = myWindow.getMyVisualFactory().getMyTabs(myLevelProperties);
        myBox.getChildren().addAll(propertiesList);
        return myBox;
    }

	private void addWithClicking(ViewSprite sprite){
		sprite.setCursor(Cursor.HAND);
<<<<<<< HEAD
=======

>>>>>>> 9ad368ba9ee8ac269278592494ab95904322f4fc
		sprite.setFitHeight(sprite.getImage().getHeight()*0.5);
		sprite.setFitWidth(sprite.getImage().getWidth()*0.5);
		sprite.setOnMousePressed(circleOnMousePressedEventHandler);
		sprite.setOnMouseDragged(circleOnMouseDraggedEventHandler);
<<<<<<< HEAD
//        sprite.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent e) {
//                if (e.getButton() == MouseButton.SECONDARY) {
//                    ((Pane) getTabContent()).getChildren().remove(sprite);
//                }
//                e.consume();
//            }
//        });
		
		((Pane) ((ScrollPane) getTabContent()).getContent()).getChildren().addAll(sprite);
=======
        sprite.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.SECONDARY) {
                    ((Pane) getTabContent()).getChildren().remove(sprite);
                }
                e.consume();
            }
        });

        (getMyNewGamePane()).getChildren().addAll(sprite);
        
>>>>>>> 9ad368ba9ee8ac269278592494ab95904322f4fc
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

    public void setCurrentSpriteNull() {
    	currentSprite = null;
    }

    /**
     * @param view is a ViewSprite that's going to be copied and get its properties set between the
     * Sprite properties.
     */
	@Override
	public void setTabContent(ViewSprite view) {
		AESpriteFactory sf = new AESpriteFactory();
		ViewSprite copy = sf.clone(view);
		mySpriteMap.put(copy, sf.makeSprite(copy));
		addWithClicking(copy);
	}


    public AnchorPane getMyNewGamePane() {
        return myNewGamePane;
    }


    }