package gameplayer;

import java.io.File;

import authoringEnvironment.MainAuthoringWindow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * The game file screen for editing games. Extends gamefilescreen.
 * @author Huijia
 *
 */
public class GameEditingFileScreen extends GameFileScreen {
	private final String DEFAULT_PICTURE = "pictures/cipher.png";

	public GameEditingFileScreen() {
		super();
	}

	@Override
	public void setOnMouseClick(File file){
		MainAuthoringWindow myMainAuthoringWindow = new MainAuthoringWindow(this);
		// TODO CHANGE THIS TO INJECTIONS
		myMainAuthoringWindow.getGameMakerWindow()
				.setGameTabs(getMyGameLoader().parseAndLoadGame(file));
		switchScene(myMainAuthoringWindow);
	}
	
	
	// MOVE OR SOMERHING

}
