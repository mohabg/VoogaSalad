package gameplayer;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GamePlayingFileScreen extends GameFileScreen {
	private final String DEFAULT_PICTURE = "pictures/cipher.png";

	public GamePlayingFileScreen(){
		super();
	}
	

	@Override
	public ImageView makeDisplay(File file) {
		ImageView imageview = new ImageView();
		// TODO have this pull the saved game's picture
		imageview.setImage(new Image(DEFAULT_PICTURE));
		imageview.setOnMouseClicked((event) -> {
			IScreen playScreen = getMyGameLoader().newGame(file);
			switchScene(playScreen);
		});
		return imageview;
	}
}
