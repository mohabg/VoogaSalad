package behaviors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import collisions.Collision;
import gameElements.ApplyBehaviorConditions;
import gameElements.Health;
import gameElements.Sprite;
import gameElements.SpriteProperties;
import gameplayer.SpriteFactory;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * A sprite that acts as a shield to an actor--is usually used in the defense attributes of sprites 
 */

public class Shield extends Defense{
	
	public Shield(){
		this(new RefObject("pictures/gaming.png"), new Health(20));
	}
	
	public Shield(RefObject myRef, Health health){
		super(myRef, health);
	}

	@Override
	public void defend(IActions actions) {
		SpriteProperties properties = actions.getSpriteProperties();
		actions.makeSprite(properties.getMyX().doubleValue(), 
								properties.getMyY().doubleValue(),
								properties.getClone(),
								this.getMyRef());
		//TODO: Shield sprite needs collisions
	}
}
