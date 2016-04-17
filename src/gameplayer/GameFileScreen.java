package gameplayer;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import authoringEnvironment.Settings;

/**
 * Abstract class implementing IScreen, used to display available/saved games
 * 
 * @author Huijia
 *
 */
public abstract class GameFileScreen extends Screen {
	private File myGameFile;
	private TabPane tabPane;

	private final File DEFAULT_DIRECTORY = new File(System.getProperty("user.dir") + "/SavedGameData/DefaultGames");
	private final File SAVED_DIRECTORY = new File(System.getProperty("user.dir") + "/SavedGameData/SavedGames");

	private final String FILE_TYPE = ".xml";
	private final String DEFAULT_PICTURE = "pictures/cipher.png";
	private final String DEFAULT_GAMES = "Default Games";
	private final String SAVED_GAMES = "Saved Games";
	private static final String BACK = "back";


	private GameLoader myGameLoader;

	public GameFileScreen() {
		super();
		tabPane = new TabPane();
		setMyGameLoader(new GameLoader());
		// Settings.setGamePlayingSettings((Pane) tabPane);
		initTabs();
	}

	private void initTabs() {
		tabPane.getTabs().add(addTab(DEFAULT_GAMES, DEFAULT_DIRECTORY));
		tabPane.getTabs().add(addTab(SAVED_GAMES, SAVED_DIRECTORY));
		BorderPane pane = new BorderPane();
		pane.setTop(ButtonFactory.makeButton(BACK, a-> {returnToStart();}));
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

	/**
	 * Creates an Imageview for a file, setting its onclick action to load it in
	 * the player or in the environment depending on the subclass.
	 * 
	 * @param file
	 * @return
	 */

	public VBox makeDisplay(File file) {
		ImageView imageview = new ImageView();
		// TODO have this pull the saved game's picture
		imageview.setImage(new Image(DEFAULT_PICTURE));
		imageview.setOnMouseClicked((event) -> {
			setOnMouseClick(file);
		});
		Label label = new Label(file.getName().replace(".xml", ""));
		return new VBox(imageview, label);

	}

	public abstract void setOnMouseClick(File file);

	public File getGameFile() {
		return myGameFile;
	}

	public GameLoader getMyGameLoader() {
		return myGameLoader;
	}

	public void setMyGameLoader(GameLoader myGameLoader) {
		this.myGameLoader = myGameLoader;
	}

}
