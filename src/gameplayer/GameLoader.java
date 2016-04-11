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
	private static XStream xstream = new XStream(new StaxDriver());
	
	private static final String DEFAULT_DIRECTORY = System.getProperty("user.dir") + "/SavedGameData/DefaultGames/my-file.xml";
	
	public GameLoader() {
		FXConverters.configure(xstream);
	}

	public IScreen newGame(File file) {
		List<LevelModel> gameLevels = parseAndLoadGame(file);
		PlayScreen ps = new PlayScreen(file);
		ps.setGameLevels(gameLevels);

		return ps;
	}

	public static void saveGame(List<LevelModel> gameLevels) {
		FXConverters.configure(xstream);
		saveGame(DEFAULT_DIRECTORY, gameLevels);
	}
	
	// TODO MIGHT WANT TO ASK FOR FILENAME HERE
	public static void saveGame(String saveFileDir, List<LevelModel> gameLevels) {
		FXConverters.configure(xstream);
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

	// TODO FIND A WAY TO CHECK IF THE TABPANE ACTUALLY CORRESPONDS TO LEVELS
	public static List<LevelModel> levelTabsToModels(TabPane levels) {
		FXConverters.configure(xstream);
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
