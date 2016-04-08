package gameplayer;

import java.io.File;

import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
			myGameWindow.setScreen("PlayScreen");
		});
		Button restart = makeOneButton("restart", a -> {
			myGameWindow.restart();
		});
		Button switchgame = makeOneButton("switchgame", a -> {
			myGameWindow.setScreen("GameFileScreen");
		});
		Button save = makeOneButton("save", a -> {
//			myGameLoader.save();
		});
		Button settings = makeOneButton("settings", a -> {
			myGameWindow.setScreen("SettingsScreen");
		});

		box.getChildren().addAll(cont, restart, switchgame, save, settings);
		return box;
	}
	
	public Button makePause(){
		return makeOneButton("pause", a -> {
			myGameWindow.setScreen("PauseScreen");
		});
	}
	
	public ImageView makeDisplay(File file) {
		ImageView imageview = new ImageView();
		imageview.setImage(new Image("pictures/cipher.png"));
		imageview.setOnMouseClicked((event) -> {
			myGameWindow.newGame(file);
			
			System.out.println(file);
		});
		return imageview;

	}
	

}
