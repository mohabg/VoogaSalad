package authoringEnvironment;

/**
 * Created by davidyan on 4/7/16.
 */
import java.util.*;

public class LevelModel {

	private Map<ViewSprite, Model> myMap;

	public LevelModel(Map<ViewSprite, Model> useMap) {
		myMap = useMap;
	}

	public Map<ViewSprite, Model> getMyMap() {
		return myMap;
	}

}
