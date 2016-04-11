package gameElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Actor extends Observable{
	private Map<String, Behavior> myBehaviors;
	private Sprite myProperties;
	
	public Actor(Sprite properties){
		myProperties = properties;
	}
	
	public Collision getCollision(){
		return getProperties().getCollision();
	}
	public Health getHealth(){
		return getProperties().getHealth();
	}
	public Sprite getProperties(){
		return myProperties;
	}
	public boolean isDead(){
		return getHealth().isDead();
	}
}