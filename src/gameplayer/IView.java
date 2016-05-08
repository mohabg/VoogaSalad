
package gameplayer;

import java.util.List;
import java.util.Map;

import authoringEnvironment.ViewSprite;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

public interface IView {
	void setBackgroundList(Number id, StringProperty background);

	void selectLevelSprites(Number levelID);

	void setActiveSprites(List<Integer> activeSprites);

	void setBackground(Number levelID);

	void setLevelSprites(Number levelID, Map<Integer, Node> map);

	Map<Integer, Node> getViewSprites();

	Node getPane();

}
