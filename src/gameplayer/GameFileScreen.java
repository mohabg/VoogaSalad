package gameplayer;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class GameFileScreen extends Screen {
	File myDirectory;
	File myGameFile;

	public GameFileScreen() {
		super();
		setDirectory();
		FlowPane flow = new FlowPane();
		flow.getChildren().addAll(
				Arrays.stream(getGames())
				.map(f -> makeDisplay(f))
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

	private ImageView makeDisplay(File file) {
		ImageView imageview = new ImageView();
		imageview.setImage(new Image("pictures/cipher.png"));
		imageview.setOnMouseClicked((event) -> {
			myGameFile = file;
		});
		return imageview;

	}

	public File getGameFile() {
		return myGameFile;

	}

}
