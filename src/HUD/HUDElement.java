package HUD;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HUDElement {
	Pane myElement;
	
	public HUDElement(HUDEnum type) {
		makeBox(type);
	}
	
	private void makeBox(HUDEnum type) {
		switch (type) {
			case Left:
			case Right:
				myElement = new VBox();
				break;
			case Up:
			case Down:
				myElement = new HBox();
				break;
			default:
				// TODO: THROW ERROR
		}
	}
	
	public void addHUDItem(HUDItem item) {
		myElement.getChildren().add(item.getItem());
	}
	
	public void removeHUDItem(HUDItem item) {
		myElement.getChildren().remove(item.getItem());
	}
}
