package authoringEnvironment;

/**
 * Created by davidyan on 4/7/16.
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
