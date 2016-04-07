package gameplayer;

import javafx.scene.layout.Pane;

public class PlayScreen implements IScreen {
	Pane myPane;

	public PlayScreen(GameView myGameView) {
		myPane.getChildren().addAll(myGameView);

	}

	@Override
	public void setStage() {
		// TODO Auto-generated method stub

	}

}
