package gameplayer;

import java.io.File;

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

        MainPlayingWindow myMainPlayingWindow = new MainPlayingWindow(this, file.getName().replace(".xml", ""));
        switchScene(myMainPlayingWindow);
	}
}
