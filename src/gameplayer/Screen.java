package gameplayer;

import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class Screen {
	private Pane myPane;

	public Screen(){
		myPane = new Pane();
	}

	public void add(Node node){
		myPane.getChildren().add(node);

	}
	public Pane getPane(){
		return myPane;
	}
	public void addAll(Collection<Node> c) {
		c.forEach(n -> add(n));
	}

}
