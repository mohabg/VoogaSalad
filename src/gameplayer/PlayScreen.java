package gameplayer;

import java.util.List;
import java.util.Map;

import authoringEnvironment.LevelModel;
import authoringEnvironment.Model;
import authoringEnvironment.ViewSprite;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class PlayScreen implements IScreen {
//	HUD

	public PlayScreen() {
		super();
		BorderPane container = new BorderPane();
		container.setTop(myFactory.makePause());
		
		add(container);

		
//		add HUD
	}

	public void setGameLevels(List<LevelModel> gameLevels) {
		for (LevelModel lm : gameLevels) {
			Map<ViewSprite, Model> spriteList = lm.getMyMap();
			for(ViewSprite vs : spriteList.keySet()) {
				add(vs);
			}
		}
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void switchScene(IScreen screen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParentScreen(IScreen screen) {
		// TODO Auto-generated method stub
		
	}
}
