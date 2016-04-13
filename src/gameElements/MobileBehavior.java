package gameElements;

import java.util.Map;

public class MobileBehavior extends Sprite{
	private Map<String, Behavior> myBehaviors;
	private Sprite myProperties;
	
	public MobileBehavior(Sprite properties){
        super("hi");
        myProperties = properties;
	}
	

	public Sprite getProperties(){
		return myProperties;
	}
	public boolean isDead(){
		return getProperties().getHealth().isDead();
	}
}