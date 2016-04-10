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
	
	public PlayScreen(File gameFile) {
		this.gameFile = gameFile;
		BorderPane container = new BorderPane();
		container.setTop(myFactory.makePause());
		
		add(container);

		
		// TODO ADD HUD TO THIS SCREEN
	}

	public void setGameLevels(List<LevelModel> gameLevels) {
		for (LevelModel lm : gameLevels) {
			Map<ViewSprite, Model> spriteList = lm.getMyMap();
			for(ViewSprite vs : spriteList.keySet()) {
				add(vs);
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
