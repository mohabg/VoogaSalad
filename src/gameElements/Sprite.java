package gameElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Sprite extends Observable{
	private List<Behavior> behaviors;
	private SpriteProperties myProperties;
	
	public Sprite(SpriteProperties properties){
		behaviors = new ArrayList<>();
		myProperties = properties;
	}
	public void setBehaviors(List<Behavior> behaviors){
		this.behaviors = behaviors;
	}
	
}
