package gameplayer;

import authoringEnvironment.Settings;
import authoringEnvironment.StartOptionsWindow;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Abstract class for screens.
 * 
 * @author Huijia
 *
 */
public abstract class Screen {
	protected Pane myPane;
	protected Screen parentScreen;

	public Screen() {
		myPane = new Pane();
		Settings.setGamePlayingSettings(myPane);

	}

	public Screen(Screen parent) {
		this();
		parentScreen = parent;
	}
	//
	// public void add(Node node){
	// myPane.getChildren().add(node);
	//
	// }
	// public Pane getPane(){
	// return myPane;
	// }
	// public void addAll(Collection<Node> c) {
	// c.forEach(n -> add(n));
	// }

	public Scene getScene() {
		try {
			return new Scene(myPane, myPane.getPrefWidth(), myPane.getPrefHeight());
		}
		catch (Exception e) {
			return myPane.getScene();

		}
	}

	public void switchScene(Screen screen) {
		Stage myStage = ((Stage) myPane.getScene().getWindow());
		myStage.setScene(screen.getScene());
		myStage.centerOnScreen();

	}

	public void returnToParentScreen() {
		switchScene(parentScreen);
	}

	public void returnToStart() {
		switchScene(new StartOptionsWindow((Stage) myPane.getScene().getWindow()));
	}

	public Pane getPane() {
		return myPane;
	}

}
