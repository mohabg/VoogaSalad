package mainWindow;
/**
 * @author: davidyan
 */
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.awt.*;

public class GameMakerWindow {

	private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	private ScrollPane myGameArea, myGameArea2;
	private TabPane myTabPane;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	private AnchorPane myGamePane, myGamePane2;

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

		myGameArea = new ScrollPane();
		myGamePane.setPrefWidth(0.3*SCREEN_WIDTH);
		myGamePane.setPrefHeight(SCREEN_HEIGHT);
		tab.setContent(myGamePane);

		Tab tab2 = new Tab();
		tab2.setText("Tab 2");

		myGameArea2 = new ScrollPane();
		myGameArea2.setFitToWidth(true);
		myGameArea2.setPrefHeight(SCREEN_HEIGHT);
		myGameArea2.setPrefWidth(0.4*SCREEN_WIDTH);

		myGamePane2 = new AnchorPane();
		myGamePane2.getStyleClass().add("pane");

		myGameArea2 = new ScrollPane();
		myGamePane2.setPrefWidth(0.3*SCREEN_WIDTH);
		myGamePane2.setPrefHeight(SCREEN_HEIGHT);
		tab2.setContent(myGamePane2);

		myTabPane.getTabs().add(tab);
		myTabPane.getTabs().add(tab2);

	}

	EventHandler<MouseEvent> circleOnMousePressedEventHandler =
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					orgSceneX = t.getSceneX();
					orgSceneY = t.getSceneY();
					orgTranslateX = ((ImageView)(t.getSource())).getTranslateX();
					orgTranslateY = ((ImageView)(t.getSource())).getTranslateY();
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

	public void addToWindow(ImageView img){
		ImageView temp = new ImageView(img.getImage());
		temp.setCursor(Cursor.HAND);
		temp.setOnMousePressed(circleOnMousePressedEventHandler);
		temp.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		System.out.println(myTabPane.getSelectionModel().getSelectedItem().getContent());
		AnchorPane myPane = (AnchorPane) myTabPane.getSelectionModel().getSelectedItem().getContent();
		myPane.getChildren().addAll(temp);
	}
}