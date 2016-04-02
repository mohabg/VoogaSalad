package mainWindow;

import java.awt.Toolkit;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;

public class GameMakerWindow {
	private static final int myScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int myScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	private GraphicsContext myGraphics;
	private Canvas myCanvas = new Canvas(0.4*myScreenWidth, myScreenHeight);
	private ScrollPane myGameArea;
	private TabPane myTabPane;
	
	public GameMakerWindow(){
		//myWindow = new VBox();
		myTabPane = new TabPane();
		Tab tab = new Tab();
		tab.setText("Tab 1");
		myGraphics = myCanvas.getGraphicsContext2D();
		myGameArea = new ScrollPane();
        tab.setContent(myCanvas);
        myGameArea.setFitToWidth(true);
        myGameArea.setPrefHeight(myScreenHeight+600);
        myGameArea.setPrefWidth(0.4*myScreenWidth);
        myGraphics.setFill(Color.BLACK);
        myGraphics.fillRect(0,0,myCanvas.getWidth(), myCanvas.getHeight());
        myTabPane.getTabs().add(tab);

	}
	
	public TabPane getMainWindow(){
		return myTabPane;
	}
}
