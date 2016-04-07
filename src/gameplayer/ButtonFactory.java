package gameplayer;

import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public abstract class ButtonFactory {
	GameWindow myGameWindow;
	GameLoader myGameLoader;

	public ButtonFactory(GameWindow myGameWindow, GameLoader myGameLoader) {
		this.myGameWindow = myGameWindow;
		this.myGameLoader = myGameLoader;
	}

	private Button makeOneButton(String name, EventHandler<? super MouseEvent> action) {
		Button b = new Button();
		b.setText(name);
		b.setOnMouseClicked(action);
		return b;
	}

	public VBox makePauseScreenButtons() {
		VBox box = new VBox();

		Button cont = makeOneButton("", a -> {
			myGameWindow.setPlayWindow();
		});
		Button restart = makeOneButton("", a -> {
			myGameWindow.setNewPlayWindow();
		});
		Button switchgame = makeOneButton("", a -> {
			myGameLoader.load();
		});
		Button save = makeOneButton("", a -> {
			myGameLoader.save();
		});
		Button settings = makeOneButton("", a -> {
			myGameWindow.setSettingsWindow();
		});

		box.getChildren().addAll(cont, restart, switchgame, save, settings);
		return box;
	}

}
