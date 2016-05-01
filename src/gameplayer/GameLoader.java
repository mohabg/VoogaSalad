package gameplayer;

import XStreamHandlers.FXConverters;
import authoringEnvironment.AESpriteFactory;
import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;
import authoringEnvironment.mainWindow.GameAuthoringTab;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import events.Event;
import gameElements.Sprite;
import goals.GoalProperties;
import interfaces.ITabPane;
import javafx.beans.property.ListProperty;
import level.Level;
import level.LevelProperties;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
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
	static final String SAVED_FOLDER_DIRECTORY = System.getProperty("user.dir")
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



	/**
	 * saves a list of levelmodels to a default directory
	 *
	 * @param tabLevels
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
	 * @param name
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
            throw new SaveGameException();
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
		for (GameAuthoringTab levelTab : levels.getTabs()) {
			LevelModel newLM = levelTab.getLevelModel();
//			LevelModel newLM = new LevelModel(levelTab);
			newLM.setBackground(levelTab.getBackground().getValue());
			
			newLM.addSprites(levelTab.getList());
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

	static Level makeLevel(LevelModel lm, int id) {
		Level newLevel = new Level();
		LevelProperties lp = newLevel.getLevelProperties();

		setLevelProperties(lp,id,"level"+id);
		lp.setGoalList(lm.getMyGoals());
		//lp.setGoalProperties(lm.getMyGoals().stream().map(g -> new GoalProperties(g)).collect(Collectors.toList()));	
		lp.setNumGoals(lm.getNumGoals());
		newLevel.setEvents(lm.getMyEvents());
//			lp.setKeyMapping(lm.getMyKeyMap());
		return newLevel;
	}

	static Map<Integer, ViewSprite> setLevelSprites(Level newLevel, List<Sprite> list) {
		Map<Integer, ViewSprite> viewsprites = new HashMap<Integer, ViewSprite>();
		List<Sprite> visList = list;
		AESpriteFactory sf = new AESpriteFactory();
		list.forEach(s -> {
			newLevel.addSprite(s);
			viewsprites.put(newLevel.getCurrentSpriteID(), sf.makeViewSprite(s));
			if(s.isUserControlled()){
				s.setUserControlled(true);
				setUserControlledSpriteID(newLevel);
				//newLevel.getMyEventManager().setSpriteActions(s.getUserPressBehaviors());
			}
			else{
				s.setUserControlled(false);
			}
		});
		return viewsprites;
	}

	private static void setUserControlledSpriteID(Level newLevel) {
		newLevel.getLevelProperties().getSpriteMap().setUserControlledSpriteID(newLevel.getCurrentSpriteID());
	}
	

	private static void setLevelProperties(LevelProperties p, Integer levelID, String tabName){
		p.setLevelID(levelID);
		p.setLevelName(tabName);
//		p.setPreviousLevel(levelID-1);
//		p.setNextLevel(levelID+1);
	}
}
