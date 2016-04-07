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
	private Collision myCollision;
	
	public Sprite(SpriteProperties properties){
		myProperties = properties;
		myHealth = new Health();
		myHealth.addObserver((Observer) this);
	}
	public void setCollision(Collision collision){
		myCollision = collision;
		collision.setSprite(this);
	}
	public Collision getCollision(){
		return myCollision;
	}
	public Health getHealth(){
		return myHealth;
	}
	public SpriteProperties getProperties(){
		return myProperties;
	}
	public boolean isDead(){
		return myHealth.isDead();
	}
}