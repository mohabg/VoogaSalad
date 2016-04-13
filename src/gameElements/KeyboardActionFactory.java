package gameElements;

import gameElements.IKeyboardAction.KeyboardActions;

public class KeyboardActionFactory {


	public static IKeyboardAction buildKeyboardAction(KeyboardActions keyboardActionType) {

		
		switch (keyboardActionType) {
		
		case NewGame:
			return new IKeyboardAction() {

				@Override
				public void enableKeyboardAction(Actor actor) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void disableKeyboardAction(Actor actor) {
					// TODO Auto-generated method stub
					
				}
	            
	        
			};	
		
		default:
			return new IKeyboardAction() {

				@Override
				public void enableKeyboardAction(Actor actor) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void disableKeyboardAction(Actor actor) {
					// TODO Auto-generated method stub
					
				}
	            
	        
			};
		}
		
	}
	
	
}
