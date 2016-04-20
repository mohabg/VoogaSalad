package gameElements;

import java.util.HashMap;
import java.util.Map;

public class SpriteMap{
	
	private Map<Integer, Sprite> spriteMap;
	private int id;
	
	public SpriteMap(){
		spriteMap = new HashMap<Integer, Sprite>();
		id = 0;
	}
	
	public void addSprite(Sprite sprite){
		spriteMap.put(id++, sprite);
	}
	public int getLastSpriteID(){
		return id - 1;
	}
	public Map<Integer, Sprite> getSpriteMap(){
		return spriteMap;
	}
	public void setSpriteMap(Map<Integer, Sprite> spriteMap){
		this.spriteMap = spriteMap;
	}
	public Sprite get(int id){
		return spriteMap.get(id);
	}
	public void remove(int id){
		spriteMap.remove(id);
	}
}
