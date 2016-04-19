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
import javafx.scene.layout.Pane;

public class SpriteFactory {
	private Pane myPane;
	private Map<Integer, ViewSprite> myViewSprites;
	private Map<Integer, Sprite> spriteMap;
	
	public SpriteFactory(){
		
	}
	
	public SpriteFactory(Pane myPane,Map<Integer, ViewSprite> myViewSprites, Map<Integer, Sprite> mySpriteMap ) {
		this.myPane = myPane; 
		this.myViewSprites = myViewSprites;
		this.spriteMap = mySpriteMap;
	}
	public Map<Integer, Sprite> getSpriteMap() {
		return spriteMap;
	}
	public void setSpriteMap(Map<Integer, Sprite> spriteMap) {
		this.spriteMap = spriteMap;
	}
	public Sprite makeSprite(double x, double y, RefObject myRef){
		return makeSprite(x, y, new Health(), new ArrayList<Collision>(), new HashMap<String, Behavior>(), myRef, 0);
	}
	public Sprite makeSprite(double x, double y, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef, Integer ID) {
		ViewSprite vs = new ViewSprite(myRef.getMyRef());
		SpriteProperties sp = vs.getMySpriteProperties();
		sp.setMyX(x);
		sp.setMyY(y);
		Sprite s = new Sprite(sp, myHealth, myCollisions, myBehaviors, myRef);
		vs.bindToSprite(s);
		myPane.getChildren().add(vs);
		myViewSprites.put(ID, vs);
		spriteMap.put(ID, s);
		
		return s;

	}
}
