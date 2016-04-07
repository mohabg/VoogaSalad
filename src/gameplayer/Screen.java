package gameplayer;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class Screen {
	Pane myPane;

	public Screen(){
		myPane = new Pane();
	}

	public void add(Node node){
		myPane.getChildren().add(node);

	}
	public Pane getPane(){
		return myPane;
	}

}
