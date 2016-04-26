package gameplayer;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class HUDWindow{
	PlayScreen myPlayScreen;
	private FlowPane myFlowPane;
	
	public HUDWindow(PlayScreen myPlayScreen) {
		this.myPlayScreen =  myPlayScreen;
		myFlowPane = new FlowPane();
		myFlowPane.getStylesheets().add("gameplayer/HUDWindow.css");
		myFlowPane.setId("myFlowPane");
		createFlowPane();
	}
	
	private void createFlowPane(){
		myFlowPane.getChildren().add(new Label("HUD goes here"));
	}
	
	public Pane getPane(){
		return myFlowPane;
	}
}