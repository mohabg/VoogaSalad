package gameplayer;

import java.util.Collection;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class PlayScreen extends Screen {
	// HUD

	public PlayScreen(ButtonFactory myFactory) {
		super(myFactory);
		BorderPane container = new BorderPane();
		container.setTop(myFactory.makePause());

		add(container);

		// add HUD
	}

}
