package events;

import behaviors.IActions;
import javafx.scene.input.KeyCode;
import level.LevelProperties;

public class KeyPressEvent extends Event {
	
	//private KeyCode myCode;

	public KeyPressEvent() {
		super();
	}
	
	public KeyPressEvent(Trigger trigger, Executable executable) {
		//KeyPressTrigger press = (KeyPressTrigger) getTrigger();
		//myCode = press.getCode();
		setTrigger(trigger);
		setExecutable(executable);
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

	/*@Override
	public void execute(KeyEvent key, boolean enable, Sprite currentSprite) {
		System.out.println(key.getCode() + key.getCharacter());
		levelProperties.addScore(10);
		KeyboardActions action = levelProperties.getKeyboardAction(key.getCode());
		IKeyboardAction keyboardAction = keyboardActionMap.get(action);

		//Sprite currentSprite = spriteMap.get(spriteID);
		if (currentSprite == null) {
			return;
		}
		if (currentSprite.isUserControlled()) {
			Behavior behavior = currentSprite.getUserPressBehavior(key.getCode());
			System.out.println(key.getCode() + "keycode");
			System.out.println(behavior.toString());
			System.out.println("angle"+currentSprite.getAngle());
			System.out.println("xVel, x" + " " + currentSprite.getX() +" " + currentSprite.getSpriteProperties().getMyXvel());
			System.out.println("yVel, y" + " " + currentSprite.getY() + " " + currentSprite.getSpriteProperties().getMyYvel());
			if (behavior != null) {
				if (enable) {
					behavior.enable();
				} else {
					behavior.disable();
				}
			}

		} else {
			if (keyboardAction == null) {
				keyboardAction = KeyboardActionFactory.buildKeyboardAction(action);
				keyboardActionMap.put(action, keyboardAction);
			}

			KeyboardActionChecker keyboardActionChecker = new KeyboardActionChecker();

			if (keyboardActionChecker.checkKeyboardAction(action, currentSprite) && enable) {
				keyboardAction.enableKeyboardAction(currentSprite);
			} else {
				keyboardAction.disableKeyboardAction(currentSprite);
			}

		}
	}*/

}
