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
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import level.Level;
import level.LevelProperties;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	public Screen newGame(File file) {
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

	public static void saveGame(ITabPane tabLevels) {
		saveGame(levelTabsToModels(tabLevels));

	}

	public static void saveGame(List<LevelModel> gameLevels) {
		FXConverters.configure(xstream);

		TextInputDialog dialog = new TextInputDialog("my-game");
		dialog.setContentText("Please enter your game's name:");
		Optional<String> result = dialog.showAndWait();

		result.ifPresent(name -> saveGame(String.format(SAVED_FOLDER_DIRECTORY, name), gameLevels));

		// saveGame(SAVED_DIRECTORY, gameLevels);
	}

	/**
	 * saves levelmodels to a user defined directory
	 * 
	 * @param saveFileDir
	 * @param gameLevels
	 */
	// TODO MIGHT WANT TO ASK FOR FILENAME HERE
	private static void saveGame(String saveFileDir, List<LevelModel> gameLevels) {
		System.out.println("saved to " + saveFileDir);
		FXConverters.configure(xstream);
		// BufferedOutputStream stdout = new BufferedOutputStream(System.out);
		// xstream.marshal(gameLevels, new PrettyPrintWriter(new
		// OutputStreamWriter(stdout)));
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
		// FXConverters.configure(xstream);
		System.out.println("number of tabs:" + levels.getTabPane().getTabs().size());
		System.out.println("number of itabs: " + levels.getITabs().size());
		List<LevelModel> levelModelList = new ArrayList<LevelModel>();
		for (ITab levelTab : levels.getITabs()) {
			System.out.println("level" + levelTab.toString());
			Map<ViewSprite, Sprite> spriteModels = ((GameAuthoringTab) levelTab).getMap();
			LevelModel newLM = new LevelModel(spriteModels);
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
	public List<LevelModel> parseAndLoadGame(File file) {
		return (List<LevelModel>) xstream.fromXML(file);
	}

	//TODO: TALK ABOUT STATIC IN GAMELOADER????
	public static Map<Level, List<ViewSprite>> makeLevelViewSpriteMap(List<LevelModel> gameLevels) {
		Map<Level, List<ViewSprite>> myViewSprites = new HashMap<Level, List<ViewSprite>>();

		gameLevels.forEach(lm -> {
			Level newLevel = new Level();
			newLevel.setLevelProperties(new LevelProperties());
			// TODO????FIGURE OUT ACTOR/USERCONTROLLED STUFF
			newLevel.setCurrentSpriteID(0);
			myViewSprites.put(newLevel, setLevelSprites(newLevel, lm.getMyMap()));
		});
		return myViewSprites;

	}

	private static List<ViewSprite> setLevelSprites(Level newLevel, Map<ViewSprite, Sprite> spriteList) {
		List<ViewSprite> levelViewSprites = new ArrayList<ViewSprite>();
		spriteList.keySet().forEach(vs -> {
			Sprite s = spriteList.get(vs);
			// System.out.println("SPRITE " + s.getX().doubleValue() + " " +
			// s.getY().doubleValue());
			// TODO: THIS NEEDS TO BE SOMEWHERE ELSE????
			// s.setAsUserControlled();
			vs.bindToSprite(s);

			levelViewSprites.add(vs);
			newLevel.addSprite(s);
		});
		return levelViewSprites;
	}
}
