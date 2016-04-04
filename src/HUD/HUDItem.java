package HUD;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class HUDItem{
	Node myItem;
	
	public HUDItem(Node myItem) {
		this.myItem = myItem;
	}
	/*public HUDItem(Number myNum) {
		myItem = new Label(myNum.toString());
	}
	
	public HUDItem(String imagePath) {
		myItem = new ImageView(imagePath);
	}*/
	
	public Node getItem() {
		return myItem;
	}
}
