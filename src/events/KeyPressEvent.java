package events;

import behaviors.IActions;
import collisions.CollisionChecker;
import collisions.CollisionHandler;
import javafx.scene.input.KeyCode;
import level.ILevelProperties;
import level.LevelProperties;

/**
 * 
 * @author gauravkumar
 *
 */
public class KeyPressEvent extends Event {
	
	public KeyPressEvent() {
		this(new CollisionChecker(), new CollisionHandler());
	}
	
	public KeyPressEvent(Trigger trigger, Executable executable) {
		super("", trigger, executable);
	}

	@Override
	public void doEvent(IActions action, ILevelProperties levProps) {
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
