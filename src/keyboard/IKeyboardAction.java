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
	};
	public void enableKeyboardAction(Sprite currentSprite);
	public void disableKeyboardAction(Sprite currentSprite);

}
