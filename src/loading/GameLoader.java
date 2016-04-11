package loading;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import authoringEnvironment.LevelModel;
import authoringEnvironment.Model;
import authoringEnvironment.ViewSprite;
import exampledata.XStreamHandlers.FXConverters;
import javafx.scene.Node;

public class GameLoader {

	public GameLoader() {
		// TODO: maybe instance vars of level model list, etc
	}

	public List<LevelModel> parseAndLoadGame(File file) {
		XStream xstream = new XStream(new StaxDriver());
		FXConverters.configure(xstream);
		return (List<LevelModel>) xstream.fromXML(file);
	}

	public List<Node> getViewSprites(LevelModel lm) {

		return lm.getMyMap().keySet().stream().map(s-> (Node)s).collect(Collectors.toList());
	}

}
