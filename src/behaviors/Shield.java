package behaviors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import collisions.Collision;
import gameElements.ExecuteConditions;
import gameElements.Health;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameElements.SpriteProperties;
import gameplayer.SpriteFactory;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import level.LevelProperties;

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
		ISpriteProperties properties = actions.getSpriteProperties();
		actions.makeSprite(properties.getX(), 
								properties.getY(),
								properties.getClone(),
								this.getMyRef());
		//TODO: Shield sprite needs collisions
	}

	@Override
	public void stop(IActions actions, LevelProperties levProps) {
		// TODO Auto-generated method stub
		
	}
}
