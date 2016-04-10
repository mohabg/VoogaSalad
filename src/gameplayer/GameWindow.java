package gameplayer;

import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import authoringEnvironment.LevelModel;
import exampledata.XStreamHandlers.FXConverters;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameWindow implements IScreen{
	private static final int myScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int myScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	private Pane myPane;
	private File myGameFile;

	private Map<String, Screen> myScreenMap;

	private Screen myPlayScreen;
	private Screen myPauseScreen;
	private GameFileScreen myGameFileScreen;
	private Screen mySettingsScreen;
	
	private IScreen parentScreen;

	public GameWindow() {
		myPane = new Pane();

//		myScreenMap = new HashMap<String, Screen>();
//		myScreenMap.put("PlayScreen", new PlayScreen());
//		myScreenMap.put("PauseScreen", new PauseScreen());
//		myScreenMap.put("GameFileScreen", new GameFileScreen());
//		myScreenMap.put("SettingsScreen", new SettingsScreen());
//
//		myFactory.makeButton("c", p -> {setScreen("GameFileScreen");});
		setScreen("GameFileScreen");
	}
	

	public void setScreen(String key) {
		myPane.getChildren().clear();
		myPane.getChildren().add(myScreenMap.get(key).getPane());
	}

//	public void restart() {
//		newGame(myGameFile);
//	}

	
	@Override
	public void switchScene(IScreen screen) {
		((Stage) myPane.getScene().getWindow()).setScene(screen.getScene());
	}
	
	@Override
	public Scene getScene() {
		return myPane.getScene();
//		Scene myRetScene = new Scene(myPane, myScreenWidth, myScreenHeight);
//		myRetScene.getStylesheets().add("resources/styles.css");
//		return myRetScene;
	}


	@Override
	public void setParentScreen(IScreen screen) {
		parentScreen = screen;	
	}

}
