package gameplayer;

import authoringEnvironment.RefObject;
import authoringEnvironment.ViewSprite;
import behaviors.Behavior;
import collisions.Collision;
import gameElements.Health;
import gameElements.ISprite;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameElements.SpriteMap;
import gameElements.SpriteProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpriteFactory {
	// do we want to use an interface??
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
	public Sprite makeSprite(double x, double y, RefObject myRef){
		return makeSprite(x, y, new Health(), new ArrayList<Collision>(), new HashMap<String, Behavior>(), myRef);
	}

	public Sprite makeSprite(double x, double y, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef) {
		ViewSprite vs = new ViewSprite(myRef.getMyRef());
		ISpriteProperties sp = vs.getMySpriteProperties();
		sp.setX(x);
		sp.setY(y);
		return createAndBindSprite(myHealth, myCollisions, myBehaviors, myRef, vs, sp);

	}
	public Sprite makeSprite(double x, double y, ISpriteProperties clone, RefObject myRef) {
		ViewSprite vs = new ViewSprite(myRef.getMyRef());
		double imageWidth = vs.getMySpriteProperties().getWidth();
		double imageHeight = vs.getMySpriteProperties().getHeight();
		clone.setWidth(imageWidth);
		clone.setHeight(imageHeight);
		return createAndBindSprite(new Health(), new ArrayList<Collision>(), new HashMap<String,Behavior>(), myRef, vs, clone);
	}
	
	public Sprite clone(ISprite sprite){
		ISpriteProperties clonedProperties = sprite.getSpriteProperties().getClone();
		List<Collision> clonedCollisions = new ArrayList<>();
		for(Collision col : sprite.getCollisions()){
			clonedCollisions.add(col.clone());
		}
		ViewSprite vs = new ViewSprite(sprite.getMyRef());
		return createAndBindSprite(sprite.getMyHealth().getClone(), clonedCollisions, sprite.getBehaviors(), 
				new RefObject(sprite.getMyRef()), vs, clonedProperties);
	}
	
	private Sprite createAndBindSprite(Health myHealth, List<Collision> myCollisions, Map<String, Behavior> myBehaviors,
			RefObject myRef, ViewSprite vs, ISpriteProperties sp) {
		Sprite s = new Sprite(sp, myHealth, myCollisions, myBehaviors, myRef);
		vs.bindToSprite(s);
		myViewSprites.put(spriteMap.getCurrentID()+1, vs);
		spriteMap.addSprite(s);
		return s;
	}
}