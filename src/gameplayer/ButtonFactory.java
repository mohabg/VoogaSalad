package gameplayer;

import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ButtonFactory {
	GameWindow myGameWindow;
//	GameLoader myGameLoader;

	public ButtonFactory(GameWindow myGameWindow) {
//		, GameLoader myGameLoader
		this.myGameWindow = myGameWindow;
//		this.myGameLoader = myGameLoader;
	}

	private Button makeOneButton(String name, EventHandler<? super MouseEvent> action) {
		Button b = new Button();
		b.setText(name);
		b.setOnMouseClicked(action);
		return b;
	}

	public VBox makePauseScreenButtons() {
		VBox box = new VBox();

		Button cont = makeOneButton("cont", a -> {
			myGameWindow.setPlay();
		});
		Button restart = makeOneButton("restart", a -> {
			myGameWindow.setRestart();
		});
		Button switchgame = makeOneButton("switchgame", a -> {
//			myGameLoader.load();
		});
		Button save = makeOneButton("save", a -> {
//			myGameLoader.save();
		});
		Button settings = makeOneButton("settings", a -> {
			myGameWindow.setSettings();
		});

		box.getChildren().addAll(cont, restart, switchgame, save, settings);
		return box;
	}
	
	public Button makePause(){
		return makeOneButton("pause", a -> {
			myGameWindow.setPause();
		});
	}
	

}
