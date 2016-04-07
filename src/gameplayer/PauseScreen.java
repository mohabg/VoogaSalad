package gameplayer;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class PauseScreen implements IScreen {
	Pane myPane;

	public PauseScreen(Node myButtons) {
		myPane.getChildren().add(myButtons);
	}

	@Override
	public void setStage() {
		// TODO Auto-generated method stub

	}

}
