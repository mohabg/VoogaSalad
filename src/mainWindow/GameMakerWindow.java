package mainWindow;

import java.awt.Toolkit;

import itemWindow.ItemWindow;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class GameMakerWindow {
	
	private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
//	private GraphicsContext myGraphics;
	private Canvas myCanvas = new Canvas(0.4*SCREEN_WIDTH, SCREEN_HEIGHT);
	private ScrollPane myGameArea;
	private TabPane myTabPane;
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private AnchorPane myGamePane;
    private Tab tab;

	
	public GameMakerWindow(){
		myTabPane = new TabPane();
		Tab tab = new Tab();
		tab.setText("Tab 1");
//		myGraphics = myCanvas.getGraphicsContext2D();
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
        myTabPane.getTabs().add(tab);
//        myGraphics.setFill(Color.BLACK);
//        myGraphics.fillRect(0,0,myCanvas.getWidth(), myCanvas.getHeight());

	}
	
	public void addImageToPane(){
        ImageView myImage = new ImageView("pictures/gaming.png");
        myImage.setCursor(Cursor.HAND);
        myImage.setOnMousePressed(circleOnMousePressedEventHandler);
        myImage.setOnMouseDragged(circleOnMouseDraggedEventHandler);
        myGamePane.getChildren().addAll(myImage);

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
	
		
	public void addImageToWindow(){
		ImageView temp = new ImageView("pictures/galaga_ship.png");
		temp.setCursor(Cursor.HAND);
		temp.setOnMousePressed(circleOnMousePressedEventHandler);
		temp.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		myGamePane.getChildren().addAll(temp);
		System.out.println("test");
	}
}
