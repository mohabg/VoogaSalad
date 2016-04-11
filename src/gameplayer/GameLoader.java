package gameplayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import authoringEnvironment.LevelModel;
import authoringEnvironment.Model;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.mainWindow.GameTab;
import exampledata.XStreamHandlers.FXConverters;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class GameLoader {
	private XStream xstream;
	private final String DEFAULT_DIRECTORY = System.getProperty("user.dir") + "/SavedGameData/DefaultGames/my-file.xml";
	public GameLoader() {
		xstream = new XStream(new StaxDriver());
		FXConverters.configure(xstream);
	}

	public IScreen newGame(File file) {
		List<LevelModel> gameLevels = parseAndLoadGame(file);
		PlayScreen ps = new PlayScreen(file);
		ps.setGameLevels(gameLevels);

		return ps;
	}

	public void saveGame(List<LevelModel> gameLevels) {
		saveGame(DEFAULT_DIRECTORY, gameLevels);
	}
	
	public void saveGame(String saveFileDir, List<LevelModel> gameLevels) {
		String xml = xstream.toXML(gameLevels);

		FileWriter fw;
		try {
			fw = new FileWriter(saveFileDir);
			fw.write(xml);
			fw.close();
		} catch (IOException e1) {
			// TODO PRINT ERROR
		}
	}

	public List<LevelModel> levelTabsToModels(TabPane levels) {
		List<LevelModel> levelModelList = new ArrayList<LevelModel>();
		for(Tab levelTab: levels.getTabs()){
			Map<ViewSprite, Model> spriteModels = ((GameTab) levelTab).getMap();
			LevelModel newLM = new LevelModel(spriteModels);
            levelModelList.add(newLM);
        }
		return levelModelList;
	}
	
	public IScreen restartGame(File file) {
		return newGame(file);
	}

	public List<LevelModel> parseAndLoadGame(File file) {
		return (List<LevelModel>) xstream.fromXML(file);
	}
}
