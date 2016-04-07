package gameplayer;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class GameFileScreen extends Screen {
	File myDirectory;
	File myGameFile;

	public GameFileScreen(ButtonFactory myFactory) {
		super(myFactory);
		init();

	}

	private void init() {
		setDirectory();
		FlowPane flow = new FlowPane();
		flow.getChildren().addAll(
				Arrays.stream(getGames())
				.map(f -> myFactory.makeDisplay(f))
				.collect(Collectors.toList()));
		add(flow);
	}

	private void setDirectory() {

		DirectoryChooser directoryChooser = new DirectoryChooser();
		myDirectory = directoryChooser.showDialog(new Stage());

	}

	private File[] getGames() {
		return myDirectory.listFiles(f -> f.getName().endsWith(".xml"));
	}


	public File getGameFile() {
		return myGameFile;

	}

}
