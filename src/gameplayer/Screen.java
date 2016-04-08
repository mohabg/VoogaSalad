package gameplayer;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class Screen {
	private Pane myPane;
	ButtonFactory myFactory;

	public Screen(ButtonFactory myFactory){
		myPane = new Pane();
		this.myFactory = myFactory;
	}

	public void add(Node node){
		myPane.getChildren().add(node);

	}
	public Pane getPane(){
		return myPane;
	}

}
