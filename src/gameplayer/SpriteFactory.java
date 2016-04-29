package gameplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import gameElements.SpriteProperties;
import authoringEnvironment.ViewSprite;
import behaviors.Behavior;
import collisions.Collision;
import gameElements.Health;
import gameElements.Sprite;
import gameElements.SpriteMap;
import javafx.scene.layout.Pane;

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
	public Sprite makeSprite(double x, double y, RefObject myRef){
		return makeSprite(x, y, new Health(), new ArrayList<Collision>(), new HashMap<String, Behavior>(), myRef);
	}
	public Sprite makeSprite(double x, double y, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef) {
		ViewSprite vs = new ViewSprite(myRef.getMyRef());
		SpriteProperties sp = vs.getMySpriteProperties();
		sp.setMyX(x);
		sp.setMyY(y);
		return createAndBindSprite(myHealth, myCollisions, myBehaviors, myRef, vs, sp);

	}
	public Sprite makeSprite(double x, double y, SpriteProperties clone, RefObject myRef) {
		ViewSprite vs = new ViewSprite(myRef.getMyRef());
		double imageWidth = vs.getMySpriteProperties().getMyWidth().doubleValue();
		double imageHeight = vs.getMySpriteProperties().getMyHeight().doubleValue();
		clone.setMyWidth(imageWidth);
		clone.setMyHeight(imageHeight);
		return createAndBindSprite(new Health(), new ArrayList<Collision>(), new HashMap<String,Behavior>(), myRef, vs, clone);
	}
	
	private Sprite createAndBindSprite(Health myHealth, List<Collision> myCollisions, Map<String, Behavior> myBehaviors,
			RefObject myRef, ViewSprite vs, SpriteProperties sp) {
		Sprite s = new Sprite(sp, myHealth, myCollisions, myBehaviors, myRef);
		vs.bindToSprite(s);
		myViewSprites.put(spriteMap.getCurrentID()+1, vs);
		spriteMap.addSprite(s);
		return s;
	}
}