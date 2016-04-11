package gameplayer;

import java.io.File;
import java.util.concurrent.Callable;

import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ButtonFactory {

	public ButtonFactory() {}
	
		
	public static Button makeButton(String name, EventHandler<? super MouseEvent> action) {
		Button b = new Button();
		b.setText(name);
		b.setOnMouseClicked(action);
		return b;
	}
	
//	public Button makePause(){
//		return makeButton("pause", a -> {
//			myGameWindow.setScreen("PauseScreen");
//		});
//	}
//	
//	public ImageView makeDisplay(File file) {
//		ImageView imageview = new ImageView();
//		imageview.setImage(new Image("pictures/cipher.png"));
//		imageview.setOnMouseClicked((event) -> {
//			myGameWindow.newGame(file);
//			
//			System.out.println(file);
//		});
//		return imageview;
//
//	}
	

}
