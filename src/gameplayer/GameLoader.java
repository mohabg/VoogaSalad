package gameplayer;

import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.mainWindow.GameAuthoringTab;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import exampledata.XStreamHandlers.FXConverters;
import gameElements.Sprite;
import interfaces.ITab;
import interfaces.ITabPane;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class for saving and loading from files. Uses xstream.
 * 
 * @author Huijia
 *
 */
public class GameLoader {
	private static XStream xstream = new XStream(new StaxDriver());

	private static final String DEFAULT_DIRECTORY = System.getProperty("user.dir")
			+ "/SavedGameData/DefaultGames/my-file.xml";
	private static final String SAVED_DIRECTORY = System.getProperty("user.dir")
			+ "/SavedGameData/SavedGames/my-file.xml";

	public GameLoader() {
		FXConverters.configure(xstream);
	}

	/**
	 * makes new game playscreen and starts it with the elements from the file,
	 * 
	 * @param file
	 *            the file containing the game
	 * @return the screen with the game
	 */
	public IScreen newGame(File file) {
		List<LevelModel> gameLevels = parseAndLoadGame(file);
		PlayScreen ps = new PlayScreen(file);
		ps.setGameLevels(gameLevels);

		return ps;
	}

	/**
	 * saves a list of levelmodels to a default directory
	 * 
	 * @param gameLevels
	 */
	public static void saveGame(List<LevelModel> gameLevels) {
		FXConverters.configure(xstream);
		saveGame(SAVED_DIRECTORY, gameLevels);
	}

	/**
	 * saves levelmodels to a user defined directory
	 * 
	 * @param saveFileDir
	 * @param gameLevels
	 */
	// TODO MIGHT WANT TO ASK FOR FILENAME HERE
	public static void saveGame(String saveFileDir, List<LevelModel> gameLevels) {
		FXConverters.configure(xstream);
		BufferedOutputStream stdout = new BufferedOutputStream(System.out);
		xstream.marshal(gameLevels, new PrettyPrintWriter(new OutputStreamWriter(stdout)));
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

	/**
	 * creates list of LevelModel from tabs in authoring environment
	 * 
	 * @param levels
	 *            implement ITabPane
	 * @return list of LevelModel
	 */
	// TODO FIND A WAY TO CHECK IF THE TABPANE ACTUALLY CORRESPONDS TO LEVELS
	public static List<LevelModel> levelTabsToModels(ITabPane levels) {
		FXConverters.configure(xstream);
		List<LevelModel> levelModelList = new ArrayList<LevelModel>();
		for (ITab levelTab : levels.getITabs()) {
			Map<ViewSprite, Sprite> spriteModels = ((GameAuthoringTab) levelTab).getMap();
			LevelModel newLM = new LevelModel(spriteModels);
			levelModelList.add(newLM);
		}
		return levelModelList;
	}

	/**
	 * same as newgame(file)
	 * @param file
	 * @return
	 */

	public IScreen restartGame(File file) {
		return newGame(file);
	}
	/**
	 * parses with xstream
	 * @param file xml file
	 * @return list of LevelModel
	 */
	public List<LevelModel> parseAndLoadGame(File file) {
		return (List<LevelModel>) xstream.fromXML(file);
	}
}
