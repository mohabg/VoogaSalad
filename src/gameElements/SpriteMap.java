package gameElements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class SpriteMap{
	
	private Map<Integer, Sprite> spriteMap;
	private ObservableList<Integer> activeSprites;
	private int id;
	
	public SpriteMap(){
		spriteMap = new HashMap<Integer, Sprite>();
		activeSprites = FXCollections.observableList(new ArrayList<Integer>());
		id = 0;
	}
	
	public void addSprite(Sprite sprite){
		id++;
		spriteMap.put(id, sprite);
		activeSprites.add(id);
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
	
	public Collection<Sprite> getSprites(){
		return spriteMap.values();
	}
	
	public Set<Integer> getSpriteIDList(){
		return spriteMap.keySet();
	}
	
	public ObservableList<Integer> getActiveSprites(){
		return activeSprites;
	}
	
	public void setActiveSprites(List<Integer> sprites){
		activeSprites.setAll(sprites);
		
	}
}
