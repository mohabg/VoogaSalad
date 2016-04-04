package highscoretable;

import javafx.scene.Node;

public class HighScoreItem<E> {
	private Node node;
	private E item;

	public HighScoreItem() {
		// TODO Auto-generated constructor stub

	}

	public E getItem() {
		return item;
	}

	public void setItem(E item) {
		this.item = item;
	}

	public Node getNode() {
		return node;

	}

	public void setNode(Node node) {
		this.node = node;
	}
}
