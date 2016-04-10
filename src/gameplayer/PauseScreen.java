package gameplayer;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class PauseScreen extends Screen {

	public PauseScreen() {
		super();
		BorderPane container = new BorderPane();
		container.setCenter(myFactory.makePauseScreenButtons());
		add(container);
	}
	
	

}
