package gameElements;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Actor extends Sprite{
	private Map<KeyEvent, Behavior> myBehaviors;
	
	public Actor(){
        myBehaviors = new HashMap<>();

    }
    public void update(){
//        for all behaviors
//                apply(this));
    }
    
    public Behavior getBehavior(KeyEvent code){
    	return myBehaviors.get(code);
    }
}