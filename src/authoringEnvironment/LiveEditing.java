package authoringEnvironment;

import authoringEnvironment.mainWindow.AClickableWindow;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameplayer.PlayScreen;
import interfaces.IGameWindow;
import javafx.scene.control.ScrollPane;

public class LiveEditing extends AClickableWindow implements IGameWindow {
	PlayScreen ps;

	public LiveEditing(PlayScreen myPlayScreen, SettingsWindow window) {
		super(window);
		ps = myPlayScreen;
		myLevelModel = new LevelModel(ps.getCurrentLevel());
		myNewGamePane = ps.getScreen().getPane();
		myNewGamePane.getChildren().removeAll(ps.getViewSprites().values());
		initArea();
	}
	@Override
	public void initArea() {
		ScrollPane myNewGameArea = new ScrollPane();
		myNewGameArea.setContent(myNewGamePane);
		myNewGamePane.setOnMouseClicked(e -> {
			updateSettingsPane(myLevelModel);
		});

		ps.getViewSprites().values().forEach(c -> addWithClicking(c));
	}

	@Override
	public void setViewSprite(ViewSprite vs) {
		AESpriteFactory sf = new AESpriteFactory();
		ViewSprite copy = sf.clone(vs);
		setClicking(copy);
		ps.addSprite(copy);

	}


	@Override
	public void updateSettingsPane(ViewSprite clickedSprite) {
		Integer ID = null;
		for(Integer i : ps.getViewSprites().keySet()) {
			if (clickedSprite.equals(ps.getViewSprites().get(i))) {
				ID = i;
				break;
			}
		}

		myWindow.setContent(setSettingsContent(ps.getSprites().get(ID)));
		
	}
	public PlayScreen getPlayScreen(){
		return ps;
	}
	

}
