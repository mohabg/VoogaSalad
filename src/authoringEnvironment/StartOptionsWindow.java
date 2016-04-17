package authoringEnvironment;

import java.util.ResourceBundle;

import gameplayer.ButtonFactory;
import gameplayer.GameEditingFileScreen;
import gameplayer.GamePlayingFileScreen;
import gameplayer.Screen;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
			switchScene(new MainAuthoringWindow(this));
			myStage.centerOnScreen();
		}));

		Button editButton = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("edit"), (e -> {

			switchScene((Screen) new GameEditingFileScreen());
		}));

		Button playButton = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("play"), (e -> {
			switchScene((Screen) new GamePlayingFileScreen());
		}));

		startWindowBox.getChildren().addAll(myLogo, createNewButton, editButton, playButton);
		((BorderPane) myPane).setCenter(startWindowBox);
	}

}
