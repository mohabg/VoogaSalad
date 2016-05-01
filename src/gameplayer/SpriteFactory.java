package gameplayer;

import authoringEnvironment.RefObject;
import authoringEnvironment.ViewSprite;
import behaviors.Behavior;
import behaviors.Gun;
import collisions.Collision;
import events.Executable;
import gameElements.Health;
import gameElements.ISprite;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameElements.SpriteMap;
import gameElements.SpriteProperties;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpriteFactory {
	
	private Map<Integer, ViewSprite> myViewSprites;
	private SpriteMap spriteMap;
	
	public SpriteFactory(Map<Integer, ViewSprite> myViewSprites, SpriteMap mySpriteMap ) {
		this.myViewSprites = myViewSprites;
		this.spriteMap = mySpriteMap;
	}

	public SpriteMap getSpriteMap() {
		return spriteMap;
	}

	public void setSpriteMap(SpriteMap spriteMap) {
		this.spriteMap = spriteMap;
	}
	public Map<Integer, ViewSprite> getMyViewSprites(){
		return this.myViewSprites;
	}
	
	public Sprite makeSprite(double x, double y, double angle, RefObject myRef) {
		ViewSprite vs = new ViewSprite(myRef.getMyRef());
		Sprite sprite = createAndBindSprite(new Health(), toCollisionListProperty(new ArrayList<Collision>()), 
									toBehaviorListProperty(new ArrayList<Behavior>()), myRef, vs, vs.getMySpriteProperties());
		sprite.getSpriteProperties().setX(x);
		sprite.getSpriteProperties().setY(y);
		sprite.getSpriteProperties().setAngle(angle);
		return sprite;
	}
	
	public Sprite clone(ISprite sprite){
		ViewSprite vs = new ViewSprite(sprite.getMyRef());
		ISpriteProperties clonedProperties = sprite.getSpriteProperties().getClone();
		List<Collision> clonedCollisions = new ArrayList<>();
		List<Behavior> clonedBehaviors = new ArrayList<>();
		
		cloneSpriteAttributes(sprite, clonedCollisions, clonedBehaviors);
		
		return this.createAndBindSprite(sprite.getMyHealth().getClone(), toCollisionListProperty(clonedCollisions),  
							toBehaviorListProperty(clonedBehaviors), new RefObject(sprite.getMyRef()), vs, clonedProperties);
	}

	private Sprite createAndBindSprite(Health myHealth, ListProperty<Collision> myCollisions, ListProperty<Behavior> myBehaviors,
			RefObject myRef, ViewSprite vs, ISpriteProperties sp) {
		Sprite s = new Sprite(sp, myHealth, myCollisions , myBehaviors, myRef);
		vs.bindToSprite(s);
		myViewSprites.put(spriteMap.getCurrentID()+1, vs);
		spriteMap.addSprite(s);
		return s;
	}
	private void cloneSpriteAttributes(ISprite sprite, List<Collision> clonedCollisions,
			List<Behavior> clonedBehaviors) {
		for(Collision col : sprite.getCollisions()){
			clonedCollisions.add(col.clone());
		}
		for(Behavior behavior : sprite.getBehaviors()){
			clonedBehaviors.add(behavior.getClone());
		}
	}
	private ListProperty<Collision> toCollisionListProperty(List<Collision> clonedCollisions) {
		ObservableList<Collision> ol = FXCollections.observableList(clonedCollisions);
		ListProperty<Collision> myCollisions = new SimpleListProperty<Collision>(ol);
		return myCollisions;
	}
	
	private ListProperty<Behavior> toBehaviorListProperty(List<Behavior> behaviors) {
		ObservableList<Behavior> bl = FXCollections.observableList(behaviors);
		ListProperty<Behavior> myBehaviors = new SimpleListProperty<Behavior>(bl);
		return myBehaviors;
	}
}