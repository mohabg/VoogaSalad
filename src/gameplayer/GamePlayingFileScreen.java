package gameplayer;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Game file screen for playing games. Extends gamefilescreen.
 * 
 * @author Huijia
 *
 */
public class GamePlayingFileScreen extends GameFileScreen {
	private final String DEFAULT_PICTURE = "pictures/cipher.png";

	public GamePlayingFileScreen() {
		super();
	}

	@Override
	public void setOnMouseClick(File file) {

		Screen playScreen = GameLoader.newGame(file);
		switchScene(playScreen);
	}
}
