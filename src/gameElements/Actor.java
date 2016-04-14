package gameElements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import authoringEnvironment.SpriteProperties;
import behaviors.Behavior;
import collisions.ActorCollision;
import collisions.Collision;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



public class Actor extends Sprite{
	
	private Map<String, Behavior> allBehaviors;
	private Map<KeyEvent, Behavior> userBehaviors;
	private Map<String, Behavior> automaticBehaviors;
	
	public Actor(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef, Map<KeyEvent, Behavior> myUserBehaviors) {
		super(myProperties, myHealth, myCollisions, myBehaviors, myRef);
		this.userBehaviors = myUserBehaviors;
		setUserControlledBehaviors();
		Collision actorCollision = new ActorCollision();
		this.addCollision(actorCollision);
	}


	private void setUserControlledBehaviors() {
		for(Behavior behavior : this.userBehaviors.values()){
			if(behavior instanceof Sprite){
				((Sprite) behavior).setAsUserControlled();
			}
		}
	}
	

    public void update(){
    	for(Behavior behavior: allBehaviors.values()){
    		if(behavior instanceof Sprite){
    			((Sprite) behavior).setAsUserControlled();
    		}
    		if(automaticBehaviors.containsKey(behavior.getClass().getName())){
    			behavior.apply(this);
    		}
    	}
    }
    
    public Behavior getBehavior(KeyEvent code){
    	return userBehaviors.get(code);
    }
    
    @Override
    public boolean isUserControlled(){
    	return true;
    }
}