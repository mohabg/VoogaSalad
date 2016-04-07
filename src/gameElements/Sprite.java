package gameElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Sprite extends Observable{
	private Map<String, Behavior> myBehaviors;
	private SpriteProperties myProperties;
	private Health myHealth;
	
	public Sprite(SpriteProperties properties){
		myProperties = properties;
		myHealth = new Health();
		myHealth.addObserver((Observer) this);
	}
	
	public double getHealth(){
		return myHealth.getHealth();
	}
	public SpriteProperties getProperties(){
		return myProperties;
	}
	public boolean isDead(){
		return myHealth.isDead();
	}
}
