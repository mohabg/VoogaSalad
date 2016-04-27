package events;

import behaviors.IActions;
import level.LevelProperties;

public class KeyPressEvent implements Executable {

    public KeyPressEvent() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute(IActions action, LevelProperties levProps) {

    }

    @Override
    public void stop(IActions actions, LevelProperties levProps) {

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