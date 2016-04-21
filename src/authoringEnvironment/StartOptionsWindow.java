package authoringEnvironment;

import java.util.Optional;
import java.util.ResourceBundle;

import gameplayer.ButtonFactory;
import gameplayer.GameEditingFileScreen;
import gameplayer.GamePlayingFileScreen;
import gameplayer.Screen;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.FrontEndData;

/**
 * @author Huijia Yu, Joe Jacob
 */

public class StartOptionsWindow extends Screen {

	private Stage myStage;
	private VBox startWindowBox;
	

	public StartOptionsWindow(Stage currStage) {
		super();
		myStage = currStage;
		myPane = new BorderPane();
		startWindowBox = new VBox();
		Settings.setStartWindowSettings(startWindowBox);

		makeAndSetStartBox();
	}

	private void makeAndSetStartBox() {
		// TODO CHANGE THIS TO USE REFLECTION
		ImageView myLogo = new ImageView(FrontEndData.DEFAULT_IMAGE);

		Button createNewButton = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("new"), (e -> {
			TextInputDialog dialog = new TextInputDialog("my-game");
			dialog.setContentText("Please enter your game's name:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(r->switchScene(new MainAuthoringWindow(this, r)));
		}));

		Button editButton = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("edit"), (e -> {

			switchScene(new GameEditingFileScreen());
		}));

		Button playButton = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("play"), (e -> {
			switchScene(new GamePlayingFileScreen());
		}));

		startWindowBox.getChildren().addAll(myLogo, createNewButton, editButton, playButton);
		((BorderPane) myPane).setCenter(startWindowBox);
	}

}
