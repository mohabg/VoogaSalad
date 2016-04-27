package authoringEnvironment;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import authoringEnvironment.mainWindow.AClickableWindow;
import authoringEnvironment.settingsWindow.SettingsWindow;
import gameElements.Sprite;
import gameplayer.GameLoader;
import gameplayer.PlayScreen;
import interfaces.IGameWindow;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import level.LevelProperties;
import resources.FrontEndData;

public class Project1 extends AClickableWindow implements IGameWindow {
	PlayScreen ps;

	public Project1(PlayScreen myPlayScreen, SettingsWindow window) {
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
