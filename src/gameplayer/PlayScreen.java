package gameplayer;

import java.io.File;
import java.util.List;
import java.util.Map;

import authoringEnvironment.LevelModel;
import authoringEnvironment.Model;
import authoringEnvironment.ViewSprite;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PlayScreen implements IScreen {
	private Pane myPane;
	private File gameFile;
	private IScreen parentScreen;
	private BorderPane bpContainer;
	
	private final String PAUSE = "Pause";
	
	public PlayScreen(File newGameFile) {
		gameFile = newGameFile;
		initBorderPane();

		
		// TODO ADD HUD TO THIS SCREEN
	}

	private void initBorderPane() {
		bpContainer = new BorderPane();
		bpContainer.setTop(ButtonFactory.makeButton(PAUSE, a -> {
			switchScene(new PauseScreen());
		}));
		myPane.getChildren().add(bpContainer);
	}
	
	public void setGameLevels(List<LevelModel> gameLevels) {
		for (LevelModel lm : gameLevels) {
			Map<ViewSprite, Model> spriteList = lm.getMyMap();
			for(ViewSprite vs : spriteList.keySet()) {
				myPane.getChildren().add(vs);
			}
		}
	}

	public File getGameFile() {
		return gameFile;
	}
	
	@Override
	public Scene getScene() {
		return myPane.getScene();
	}

	@Override
	public void switchScene(IScreen screen) {
		((Stage) myPane.getScene().getWindow()).setScene(screen.getScene());	
	}

	@Override
	public void setParentScreen(IScreen screen) {
		parentScreen = screen;	
	}
}
