package gameplayer;

import java.util.Map;

import gameElements.ISprite;
import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyEvent;
import level.Level;

public interface IEngine {


	void addLevel(Integer id, Level newLevel);

	void start();


	Map<Integer, ISprite> getSpriteMap();

	Level getCurrentLevel();

	ObservableValue<Number> getCurrentLevelID();

	ObservableList<Integer> getActiveSprites();


}
