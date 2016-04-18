package gameplayer;

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
	Pane myPane;
	Map<Integer, ViewSprite> myViewSprites;

	public SpriteFactory(Pane myPane,Map<Integer, ViewSprite> myViewSprites ) {
		this.myPane = myPane; 
		this.myViewSprites = myViewSprites;
	}

	public Sprite makeSprite(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef, Integer ID) {
		
		Sprite s = new Sprite(myProperties, myHealth, myCollisions, myBehaviors, myRef);
		ViewSprite vs = new ViewSprite(myRef.getMyRef());
		vs.bindToSprite(s);
		myPane.getChildren().add(vs);
		myViewSprites.put(ID, vs);
		
		return s;

	}
}
