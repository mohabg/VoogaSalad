package authoringEnvironment;
/**
 * @author davidyan
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartOptionsWindow {
	
	private Stage myStage;
	private BorderPane myPane;
	private VBox startWindowBox;
	private static final int mySpacing = 20;
	
	public StartOptionsWindow(Stage useStage){
		myStage = useStage;
		myPane = new BorderPane();
		startWindowBox = new VBox();
		startWindowBox.setPadding(new Insets(mySpacing, mySpacing, mySpacing, mySpacing));
		startWindowBox.setSpacing(mySpacing);
		startWindowBox.setAlignment(Pos.CENTER);
		makeStartBox();
		myPane.setCenter(startWindowBox);

		
	}
	
	public void makeStartBox(){
		ImageView myLogo = new ImageView("pictures/gaming.png");		
		Button createNewButton = createNewButton();
		Button editButton = createEditButton();
		Button playButton = createPlayButton();
		startWindowBox.getChildren().addAll(myLogo, createNewButton, editButton, playButton);

	}

	public Button createPlayButton() {
		Button playButton = new Button("Play A Game");
		playButton.setOnAction(e-> {
			System.out.println("PLAY");
		});
		return playButton;
	}

	public Button createEditButton() {
		Button editButton = new Button("Edit An Existing Game");
		editButton.setOnAction(e-> {
			System.out.println("EDIT");
		});
		return editButton;
	}

	public Button createNewButton() {
		Button newButton = new Button("Create A New Game");
		newButton.setOnAction(e-> {
			MainAuthoringWindow myMainAuthoringWindow = new MainAuthoringWindow();
			myStage.setScene(myMainAuthoringWindow.getScene());
			myStage.show();
			myStage.centerOnScreen();
		});
		return newButton;
	}
	
	public Scene getScene(){
		return new Scene(myPane, myPane.getPrefWidth(), myPane.getPrefHeight());
	}

}
