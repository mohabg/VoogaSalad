package gameElements;

import gameElements.IKeyboardAction.KeyboardActions;

public class KeyboardActionFactory {
	private static int velocityX;
	private static int velocityY;
	private static int V_KEY_CONSTANT = 30;
	private static int H_KEY_CONSTANT = 20;

	public static IKeyboardAction buildKeyboardAction(KeyboardActions keyboardActionType) {

		
		switch (keyboardActionType) {
		case MoveDown:
			setVelocityY(-V_KEY_CONSTANT);
			setVelocityX(0);
			break;
		case MoveLeft:
			setVelocityX(-H_KEY_CONSTANT);
			setVelocityY(0);
			break;
		case MoveRight:
			setVelocityY(0);
			setVelocityX(H_KEY_CONSTANT);
			break;
		case MoveUp:
			setVelocityY(V_KEY_CONSTANT);
			setVelocityX(H_KEY_CONSTANT);	
			break;
		default:
			setVelocityY(0);
			setVelocityX(0);
			break;
		}
		return new IKeyboardAction() {
            private boolean once = false;
            
            @Override
            public void applyPressActionToRuntimeSprite(
                    RuntimeSpriteCharacteristics sprite) {
                if(once) return;
                once = true;
                sprite.v_x += vxnew;
                sprite.v_y -= vynew;
                
                if(sprite.v_y > V_TERMINAL_VELOCITY) sprite.v_y = V_TERMINAL_VELOCITY;
            
            }

            @Override
            public void applyReleaseActionToRuntimeSprite(
                    RuntimeSpriteCharacteristics sprite) {
                if(!once) return;
                once = false;
                if(!sprite.isCollidingHorizontally) sprite.v_x = 0;
                sprite.v_y -= vyreleasenew;
            }
        };
		
		
	}
	
	public static void setVelocityX(int vX){
		velocityX = vX;
	}

	public static void setVelocityY(int vY){
		velocityY=vY;
	}
}
