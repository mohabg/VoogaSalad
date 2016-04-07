package mainWindow;
/**
 * @author: davidyan
 */
import authoringEnvironment.ViewSprite;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import settingsWindow.SettingsWindow;

import java.awt.*;

public class GameMakerWindow {

	private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	private ScrollPane myGameArea;
	private TabPane myTabPane;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	private AnchorPane myGamePane;
	private ViewSprite currSprite;
    private SettingsWindow myWindow;

	public GameMakerWindow(){
		myTabPane = new TabPane();
		Tab tab = new Tab();
		tab.setText("Tab 1");

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
		Tab myTab = new Tab("Tab " + (myTabPane.getTabs().size() + 1));
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
					orgTranslateX = ((ImageView)(t.getSource())).getTranslateX();
					orgTranslateY = ((ImageView)(t.getSource())).getTranslateY();
                    currSprite.setSettingsContent(myWindow);
					System.out.println(orgTranslateX);
					System.out.println(orgTranslateY);
				}
			};

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

	public void addToWindow(ViewSprite mySprite){
		ViewSprite copy = new ViewSprite();
		copy.setImage(mySprite.getImage());
		currSprite = copy;
		copy.getImageView().setCursor(Cursor.HAND);
		copy.getImageView().setOnMousePressed(circleOnMousePressedEventHandler);
		copy.getImageView().setOnMouseDragged(circleOnMouseDraggedEventHandler);
		System.out.println(myTabPane.getSelectionModel().getSelectedItem().getContent());
		AnchorPane myPane = (AnchorPane) myTabPane.getSelectionModel().getSelectedItem().getContent();
		myPane.getChildren().addAll(copy.getImageView());
	}
}