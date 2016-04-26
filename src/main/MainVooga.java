package main;

import authoringEnvironment.StartOptionsWindow;
import gameplayer.GameLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainVooga extends Application {

	public static void main(String[] args) {
		launch();
	}

	public void start(Stage primaryStage) {
		StartOptionsWindow myFirstWindow = new StartOptionsWindow(primaryStage);
		GameLoader.init();
		primaryStage.setScene(myFirstWindow.getScene());
		primaryStage.show();

	}

}