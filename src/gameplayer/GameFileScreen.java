package gameplayer;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import authoringEnvironment.Settings;

public abstract class GameFileScreen implements IScreen {
	private File myGameFile;

	private Pane myPane;
	private TabPane tabPane;
	private IScreen parentScreen;
	private Scene myScene;

	private final File DEFAULT_DIRECTORY = new File(System.getProperty("user.dir") + "/SavedGameData/DefaultGames");
	private final File SAVED_DIRECTORY = new File(System.getProperty("user.dir") + "/SavedGameData/SavedGames");

	private final String FILE_TYPE = ".xml";
	private final String DEFAULT_PICTURE = "pictures/cipher.png";
	private final String DEFAULT_GAMES = "Default Games";
	private final String SAVED_GAMES = "Saved Games";

	private GameLoader myGameLoader;

	public GameFileScreen() {
		myPane = new Pane();
		myScene = new Scene(myPane);
		tabPane = new TabPane();
		setMyGameLoader(new GameLoader());
//		Settings.setGamePlayingSettings((Pane) tabPane);
		initTabs();
	}

	private void initTabs() {
		tabPane.getTabs().add(addTab(DEFAULT_GAMES, DEFAULT_DIRECTORY));
		tabPane.getTabs().add(addTab(SAVED_GAMES, SAVED_DIRECTORY));
		System.out.println("here");
		myPane.getChildren().add(tabPane);
	}

	private Tab addTab(String title, File directory) {
		Tab tab = new Tab();
		tab.setText(title);
		tab.setContent(makeFlowPane(directory));
		return tab;
	}

	private FlowPane makeFlowPane(File flowDirectory) {
		FlowPane flowPane = new FlowPane();
		flowPane.getChildren()
				.addAll(Arrays.stream(getGames(flowDirectory)).map(f -> makeDisplay(f)).collect(Collectors.toList()));
		return flowPane;
	}

	private File[] getGames(File directory) {
		return directory.listFiles(f -> f.getName().endsWith(FILE_TYPE));
	}

	public abstract ImageView makeDisplay(File file); 
	//TODO: MAKE THE ACTUAL EVENTS abstract, not this whole method 


	public File getGameFile() {
		return myGameFile;
	}

	@Override
	public Scene getScene() {
		return myPane.getScene();
	}

	@Override
	public void switchScene(IScreen screen) {
		Stage myStage = (Stage) myPane.getScene().getWindow();
        myStage.setScene(screen.getScene());
        myStage.centerOnScreen();
	}

	@Override
	public void setParentScreen(IScreen screen) {
		parentScreen = screen;
	}

	public GameLoader getMyGameLoader() {
		return myGameLoader;
	}

	public void setMyGameLoader(GameLoader myGameLoader) {
		this.myGameLoader = myGameLoader;
	}

}
