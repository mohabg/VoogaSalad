package gameElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class MobileBehavior extends Sprite{
	private Map<String, Behavior> myBehaviors;
	private Sprite myProperties;
	
	public MobileBehavior(Sprite properties){
		myProperties = properties;
	}
	

	public Sprite getProperties(){
		return myProperties;
	}
	public boolean isDead(){
		return getProperties().getHealth().isDead();
	}
}