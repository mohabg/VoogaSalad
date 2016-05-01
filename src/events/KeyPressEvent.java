package events;

import behaviors.IActions;
import collisions.CollisionChecker;
import collisions.CollisionHandler;
import javafx.scene.input.KeyCode;
import level.LevelProperties;

public class KeyPressEvent extends Event {
	
	//private KeyCode myCode;
	
	public KeyPressEvent() {
		this(new CollisionChecker(), new CollisionHandler());
	}
	
	public KeyPressEvent(Trigger trigger, Executable executable) {
		//KeyPressTrigger press = (KeyPressTrigger) getTrigger();
		//myCode = press.getCode();
		super("", trigger, executable);
		//setTrigger(trigger);
		//setExecutable(executable);
	}

	@Override
	public void doEvent(IActions action, LevelProperties levProps) {
		KeyPressTrigger press = (KeyPressTrigger) getTrigger();
		action.setSprite(levProps.getSpriteMap().getUserControlledSprite());
		if ( press.isTriggered()) {
			getExecutable().execute(action, levProps);
		}
		else{
			getExecutable().stop(action, levProps);
		}
	}

}
