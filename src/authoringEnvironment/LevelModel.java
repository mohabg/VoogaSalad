package authoringEnvironment;

/**
 * @author David Yan, Joe Jacob
 */
import gameElements.Sprite;
import level.Level;
import level.LevelProperties;

import java.util.*;

public class LevelModel {

	private Map<ViewSprite, Sprite> myMap;
	private LevelProperties myProperties;

	public LevelModel(Map<ViewSprite, Sprite> useMap) {
		myMap = useMap;
	}

	public LevelModel(Level l) {
		// TODO Auto-generated constructor stub
		AESpriteFactory sf= new AESpriteFactory();
		 Map<ViewSprite, Sprite> map = new HashMap<ViewSprite, Sprite>();
		l.getSpriteMap().values().forEach(sprite->
		{
			map.put(sf.makeViewSprite(sprite), sprite);
		});
		myMap = map;
		myProperties = l.getLevelProperties();
	}

	public Map<ViewSprite, Sprite> getMyMap() {
		return myMap;
	}
	public LevelProperties getMyProperties(){
		return myProperties;
	}

}
