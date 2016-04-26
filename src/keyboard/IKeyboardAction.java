package keyboard;

import gameElements.Sprite;

public interface IKeyboardAction {

	public enum KeyboardActions {
		MoveLeft,
		MoveRight,
		MoveUp,
		MoveDown,
		Shoot,
		SwitchWeapon,
		Reload,
		NewGame,
		Default
	}

    void enableKeyboardAction(Sprite currentSprite);
	void disableKeyboardAction(Sprite currentSprite);

}
