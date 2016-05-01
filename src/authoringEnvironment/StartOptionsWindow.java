package authoringEnvironment;

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

import java.util.Optional;

/**
 * @author Huijia Yu, Joe Jacob, davidyan
 * Contains the starting options that connects the user to the corresponding choice made from the initial selection
 * Allows user to choose whether to create a new game, edit an existing game, or play an already made game.
 * 
 */

public class StartOptionsWindow extends Screen {

	private Stage myStage;
	private VBox startWindowBox;
	

	public StartOptionsWindow(Stage currStage) {
		super();
		myStage = currStage;
		myPane = new BorderPane();
		myPane.getStylesheets().add(FrontEndData.STARTING_STYLESHEET);
		startWindowBox = new VBox();
		startWindowBox.setId("myvBox");
		Settings.setStartWindowSettings(startWindowBox);
		makeAndSetStartBox();
	}

	private void makeAndSetStartBox() {
		ImageView myLogo = new ImageView(FrontEndData.DEFAULT_IMAGE);
		Button createNewButton = ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("new"), (e -> {
			TextInputDialog dialog = new TextInputDialog(FrontEndData.ButtonLabels.getString("SetGame"));
			dialog.setContentText(FrontEndData.ButtonLabels.getString("InputGame"));
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(r->{
				switchScene(new MainAuthoringWindow(this, r));
			});
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
