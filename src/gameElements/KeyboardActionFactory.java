package gameElements;

import gameElements.IKeyboardAction.KeyboardActions;

public class KeyboardActionFactory {


	public static IKeyboardAction buildKeyboardAction(KeyboardActions keyboardActionType) {

		
		switch (keyboardActionType) {
		case MoveDown:
			return new IKeyboardAction() {

				@Override
				public void enableKeyboardAction(Sprite sprite) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void disableKeyboardAction(Sprite sprite) {
					// TODO Auto-generated method stub
					
				}
	            
	        
			};
		case MoveLeft:
			return new IKeyboardAction() {

				@Override
				public void enableKeyboardAction(Sprite sprite) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void disableKeyboardAction(Sprite sprite) {
					// TODO Auto-generated method stub
					
				}
	            
	        
			};
		case MoveRight:
			return new IKeyboardAction() {

				@Override
				public void enableKeyboardAction(Sprite sprite) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void disableKeyboardAction(Sprite sprite) {
					// TODO Auto-generated method stub
					
				}
	            
	        
			};
		case MoveUp:
			return new IKeyboardAction() {

				@Override
				public void enableKeyboardAction(Sprite sprite) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void disableKeyboardAction(Sprite sprite) {
					// TODO Auto-generated method stub
					
				}
	            
	        
			};	
		default:
			return new IKeyboardAction() {

				@Override
				public void enableKeyboardAction(Sprite sprite) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void disableKeyboardAction(Sprite sprite) {
					// TODO Auto-generated method stub
					
				}
	            
	        
			};
		}
		
	}
	
	
}
