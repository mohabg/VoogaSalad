package authoringEnvironment;

/**
 * @author David Yan, Joe Jacob
 */
import gameElements.Sprite;

import java.util.*;

public class LevelModel {

	private Map<ViewSprite, Sprite> myMap;

	public LevelModel(Map<ViewSprite, Sprite> useMap) {
		myMap = useMap;
	}

	public Map<ViewSprite, Sprite> getMyMap() {
		return myMap;
	}

}
