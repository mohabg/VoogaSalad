package gameplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import authoringEnvironment.LevelModel;
import exampledata.XStreamHandlers.FXConverters;

public class GameLoader {
	private XStream xstream;
	
	
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
	
	public void saveGame() {
		
	}
	
	public IScreen restartGame(File file) {
		return newGame(file);
	}
	
	private ArrayList<LevelModel> parseAndLoadGame(File file) {	
		return (ArrayList<LevelModel>) xstream.fromXML(file);
	}
}
