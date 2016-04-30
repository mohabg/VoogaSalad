package keyboard;

import gameElements.Sprite;
import keyboard.IKeyboardAction.KeyboardActions;

public class KeyboardActionFactory {

	public static IKeyboardAction buildKeyboardAction(KeyboardActions keyboardActionType) {
		switch (keyboardActionType) {
		case NewGame:
			return new IKeyboardAction() {
				@Override
				public void enableKeyboardAction(Sprite actor) {
					// TODO Auto-generated method stub
				}
				@Override
				public void disableKeyboardAction(Sprite actor) {
					// TODO Auto-generated method stub
				}
			};
		default:
			return new IKeyboardAction() {
				@Override
				public void enableKeyboardAction(Sprite actor) {
					// TODO Auto-generated method stub
				}
				@Override
				public void disableKeyboardAction(Sprite actor) {
					// TODO Auto-generated method stub
				}
			};
		}
	}
	
	
}