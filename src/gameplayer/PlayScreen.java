package gameplayer;

import java.io.File;
import java.util.List;
import java.util.Map;

import HUD.HUDEnum;
import HUD.HeadsUpDisplay;
import authoringEnvironment.LevelModel;
import authoringEnvironment.Model;
import authoringEnvironment.ViewSprite;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PlayScreen implements IScreen {
	private Pane myPane;
	private Group myViewSprites;
	private Scene myScene;
	private HeadsUpDisplay myHUD;
	
	private File gameFile;
	private IScreen parentScreen;
	
	private final String PAUSE = "Pause";
	
	public PlayScreen(File newGameFile) {
		gameFile = newGameFile;
		myPane = new Pane();
		myViewSprites = new Group();
		myScene = new Scene(myPane);
		myHUD = new HeadsUpDisplay(myScene.getWidth(), myScene.getHeight());
		initHUD();
		// add above to HUD
		
		// TODO ADD HUD TO THIS SCREEN
	}

	private void initHUD() {
		Button pauseButton = makePauseButton();
		myHUD.addToHUDElement(HUDEnum.Up, pauseButton);
		myPane.getChildren().add(myHUD.getHUD());
	}
	
	private Button makePauseButton() {
		return ButtonFactory.makeButton(PAUSE, a -> {
			PauseScreen ps = new PauseScreen();
			ps.setParentScreen(this);
			switchScene(ps);
		});
	}
	
	public void setGameLevels(List<LevelModel> gameLevels) {
		for (LevelModel lm : gameLevels) {
			Map<ViewSprite, Model> spriteList = lm.getMyMap();
			for(ViewSprite vs : spriteList.keySet()) {
				myViewSprites.getChildren().add(vs);
			}
		}
		myPane.getChildren().add(myViewSprites);
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
