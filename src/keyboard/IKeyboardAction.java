package keyboard;

import gameElements.Sprite;

public interface IKeyboardAction {

	public enum KeyboardActions {
		rotateLeft,
		rotateRight,
		thrustForward,
		thrustReverse,
		Attack,
		SwitchWeapon,
		Reload,
		NewGame,
		Default
	}

    void enableKeyboardAction(Sprite currentSprite);
	void disableKeyboardAction(Sprite currentSprite);

}
