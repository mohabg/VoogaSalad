package HUD;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class HUDElement {
	private Pane myBox;
	private enum BoxType {Vertical, Horizontal}

    private BoxType myBoxType;
	
	private Priority ITEM_PRIORITY = Priority.ALWAYS;
	private Pos CHILDREN_ALIGNMENT = Pos.CENTER;
	
	public HUDElement(HUDEnum type) {
		setBoxType(type);
		makeBox();
	}
	
	private void setBoxType(HUDEnum type) {
		switch (type) {
			case Left:
			case Right:
				myBoxType = BoxType.Vertical;
				break;
			case Up:
			case Down:
				myBoxType = BoxType.Horizontal;
				break;
		}
	}
	
	private void makeBox() {
		if (myBoxType == BoxType.Vertical) {
			myBox = new VBox();
			((VBox) myBox).setAlignment(CHILDREN_ALIGNMENT);
		} else {
			myBox = new HBox();
			((HBox) myBox).setAlignment(CHILDREN_ALIGNMENT);
		}
	}
	
	
	public void addHUDItem(Node item) {
		myBox.getChildren().add(setGrow(item));
	}
	
	
	public void removeHUDItem(Node item) {
		myBox.getChildren().remove(item);
	}
	
	private Node setGrow(Node item) {
		VBox.setVgrow(item, ITEM_PRIORITY);
		HBox.setHgrow(item, ITEM_PRIORITY);

		return item;
	}
	
	public Pane getBox() {
		return myBox;
	}
}
