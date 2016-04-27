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
	private ObservableList<Integer> activeSpriteIDs;
	private int currentID;
	private int userControlledSpriteID;
	
	public SpriteMap(){
		spriteMap = new HashMap<Integer, Sprite>();
		activeSpriteIDs = FXCollections.observableList(new ArrayList<Integer>());
		currentID = 0;
		userControlledSpriteID = 0;
	}
	
	public void addSprite(Sprite sprite){
		spriteMap.put(++currentID, sprite);
		activeSpriteIDs.add(currentID);
	}
	public void put(Integer id, Sprite sprite){
		this.spriteMap.put(id, sprite);
	}
	public int getCurrentID(){
		return currentID;
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
		activeSpriteIDs.removeIf(item->item.equals(id));
	}
	
	public Collection<Sprite> getSprites(){
		return spriteMap.values();
	}
	
	public Set<Integer> getSpriteIDList(){
		return spriteMap.keySet();
	}
	
	public ObservableList<Integer> getActiveSprites(){
		return activeSpriteIDs;
	}
	
	public void setActiveSprites(List<Integer> sprites){
		activeSpriteIDs.setAll(sprites);
		
	}

	public int getUserControlledSpriteID() {
		return userControlledSpriteID;
	}

	public void setUserControlledSpriteID(int userControlledSpriteID) {
		this.userControlledSpriteID = userControlledSpriteID;
	}

	public Sprite getCurrentSprite() {
		return this.get(currentID);
	}

	public Sprite getUserControlledSprite() {
		return this.get(userControlledSpriteID);
	}
}
