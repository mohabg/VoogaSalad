package gameElements;

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
	public void enableKeyboardAction(Sprite sprite);
	public void disableKeyboardAction(Sprite sprite);

}
