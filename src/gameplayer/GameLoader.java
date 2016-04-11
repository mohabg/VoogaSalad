package gameplayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

	public void saveGame(List<LevelModel> gameLevels) {
		String xml = xstream.toXML(gameLevels);

		FileWriter fw;
		try {
			fw = new FileWriter(System.getProperty("user.dir") + "/SavedGameData/DefaultGames/my-file.xml");
			fw.write(xml);
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public IScreen restartGame(File file) {
		return newGame(file);
	}

	public ArrayList<LevelModel> parseAndLoadGame(File file) {
		return (ArrayList<LevelModel>) xstream.fromXML(file);
	}
}
