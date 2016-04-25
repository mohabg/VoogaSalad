package gameplayer;

import authoringEnvironment.AESpriteFactory;
import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.mainWindow.GameAuthoringTab;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import XStreamHandlers.FXConverters;
import gameElements.Sprite;
import goals.Goal.Goals;
import goals.GoalProperties;
import interfaces.ITab;
import interfaces.ITabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import level.Level;
import level.LevelProperties;

import javafx.scene.control.TextInputDialog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
	private static final String SAVED_FOLDER_DIRECTORY = System.getProperty("user.dir")
			+ "/SavedGameData/SavedGames/%s.xml";

	private static final String SAVED_DIRECTORY2 = System.getProperty("user.dir") + "/SavedGameData/SavedGames/";

	public static void init() {
		FXConverters.configure(xstream);

	}

	/**
	 * makes new game playscreen and starts it with the elements from the file,
	 *
	 * @param file
	 *            the file containing the game
	 * @return the screen with the game
	 */
	public static Screen newGame(File file) {
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

	public static void saveGame(String name, ITabPane tabLevels) {
		saveGame(name, levelTabsToModels(tabLevels));

	}

	public static void savePlayedGame(String name, Collection<Level> levels) {
		List<LevelModel> gameLevels = levels.stream().map(l -> new LevelModel(l)).collect(Collectors.toList());
		saveGame(name, gameLevels);
	}

	/**
	 * saves levelmodels to a user defined directory
	 *
	 * @param saveFileDir
	 * @param gameLevels
	 */
	// TODO MIGHT WANT TO ASK FOR FILENAME HERE
	private static void saveGame(String name, List<LevelModel> gameLevels) {
		String saveFileDir = String.format(SAVED_FOLDER_DIRECTORY, name);
		System.out.println("saved to " + saveFileDir);
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
	public static List<LevelModel> levelTabsToModels(ITabPane levels) {

		List<LevelModel> levelModelList = new ArrayList<LevelModel>();
		for (ITab levelTab : levels.getITabs()) {
			LevelModel newLM = new LevelModel(((GameAuthoringTab) levelTab).getList());
			levelModelList.add(newLM);
		}
		return levelModelList;
	}

	/**
	 * parses with xstream
	 *
	 * @param file
	 *            xml file
	 * @return list of LevelModel
	 */
	public static List<LevelModel> parseAndLoadGame(File file) {
		return (List<LevelModel>) xstream.fromXML(file);
	}

	// TODO: TALK ABOUT STATIC IN GAMELOADER????
	public static Map<Level, Map<Integer, ViewSprite>> makeLevelViewSpriteMap(List<LevelModel> gameLevels) {
		Map<Level, Map<Integer, ViewSprite>> myViewSprites = new HashMap<Level, Map<Integer, ViewSprite>>();

		gameLevels.forEach(lm -> {
			Level newLevel = new Level();
			LevelProperties lp = newLevel.getLevelProperties();

			lp.setGoalProperties(lm.getMyGoals().stream().map(g -> new GoalProperties(g)).collect(Collectors.toList()));
			lp.setNumGoals(lm.getNumGoals());
//			lp.setKeyMapping(lm.getMyKeyMap());
			myViewSprites.put(newLevel, setLevelSprites(newLevel, lm.getMySpriteList()));
		});
		return myViewSprites;

	}

	private static Map<Integer, ViewSprite> setLevelSprites(Level newLevel, List<Sprite> list) {
		Map<Integer, ViewSprite> viewsprites = new HashMap<Integer, ViewSprite>();
		AESpriteFactory sf = new AESpriteFactory();
		list.forEach(s -> {
			if (s.isUserControlled()) {
				s.setAsUserControlled();
			}
			newLevel.addSprite(s);
			viewsprites.put(newLevel.getCurrentSpriteID(), sf.makeViewSprite(s));

		});
		return viewsprites;
	}

	public static Screen newGame(String name) {
		File file = new File(String.format(SAVED_FOLDER_DIRECTORY, name));
		return newGame(file);
	}
}
