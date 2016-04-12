package gameplayer;

import java.io.File;

import authoringEnvironment.MainAuthoringWindow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GameEditingFileScreen extends GameFileScreen {
	private final String DEFAULT_PICTURE = "pictures/cipher.png";

	public GameEditingFileScreen() {
		super();
	}

	@Override
	public ImageView makeDisplay(File file) {
		ImageView imageview = new ImageView();
		// TODO have this pull the saved game's picture
		imageview.setImage(new Image(DEFAULT_PICTURE));
		imageview.setOnMouseClicked((event) -> {
			MainAuthoringWindow myMainAuthoringWindow = new MainAuthoringWindow();
			// TODO CHANGE THIS TO INJECTIONS
			myMainAuthoringWindow.getGameMakerWindow()
					.populateEditingFromSave(getMyGameLoader().parseAndLoadGame(file));
			switchScene(myMainAuthoringWindow);
		});
		return imageview;

	}
	// MOVE OR SOMERHING

}
