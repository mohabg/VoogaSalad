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
	
	private Map<Integer, ISprite> spriteMap;
	private Map<String, List<ISprite>> refToSprites;
	private ObservableList<Integer> activeSpriteIDs;
	private int currentID;
	private int userControlledSpriteID;
	
	public SpriteMap(){
		spriteMap = new HashMap<Integer, ISprite>();
		refToSprites = new HashMap<>();
		activeSpriteIDs = FXCollections.observableList(new ArrayList<Integer>());
		currentID = 0;
		userControlledSpriteID = 0;
	}
	
	public void addSprite(ISprite sprite){
		addToSpriteMap(sprite);
		addToRefMap(sprite);
	}
	
	public Map<String, List<ISprite>> getRefToSpritesMap(){
		return this.refToSprites;
	}
	
	public List<ISprite> getSpritesForImage(String imageUrl){
		return this.refToSprites.get(imageUrl);
	}
	
	private void addToRefMap(ISprite sprite) {
		String imageUrl = sprite.getMyRef();
		if(this.refToSprites.containsKey(imageUrl)){
			this.refToSprites.get(imageUrl).add(sprite);
		}
		else{
			List<ISprite> spritesForImage = new ArrayList<>();
			spritesForImage.add(sprite);
			this.refToSprites.put(imageUrl, spritesForImage);
		}
	}

	private void addToSpriteMap(ISprite sprite) {
		spriteMap.put(++currentID, sprite);
		activeSpriteIDs.add(currentID);
	}
	
	public void put(Integer id, Sprite sprite){
		this.spriteMap.put(id, sprite);
	}
	
	public int getCurrentID(){
		return currentID;
	}
	
	public Map<Integer, ISprite> getSpriteMap(){
		return spriteMap;
	}
	
	public void setSpriteMap(Map<Integer, ISprite> spriteMap){
		this.spriteMap = spriteMap;
	}
	
	public ISprite get(int id){
		return spriteMap.get(id);
	}
	
	public void remove(int id){
		spriteMap.remove(id);
		activeSpriteIDs.removeIf(item->item.equals(id));
	}
	
	public Collection<ISprite> getSprites(){
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

	public ISprite getCurrentSprite() {
		return this.get(currentID);
	}

	public ISprite getUserControlledSprite() {
		return this.get(userControlledSpriteID);
	}
}
