package events;

import java.util.Map;

import behaviors.Behavior;
import behaviors.IActions;
import gameElements.Sprite;
import gameElements.SpriteMap;
import keyboard.IKeyboardAction;
import keyboard.KeyboardActionChecker;
import keyboard.KeyboardActionFactory;
import level.LevelProperties;
import keyboard.IKeyboardAction.KeyboardActions;
import javafx.scene.input.KeyEvent;

public class KeyPressEvent implements Executable {

	public KeyPressEvent() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(IActions action) {
		
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
