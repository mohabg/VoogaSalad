package behaviors;

public class MoveTowards extends Movement{

	public MoveTowards(){
		
	}
	
	@Override
	public void move(IActions actions) {
		double thisX = actions.getSpriteProperties().getX();
		double thisY = actions.getSpriteProperties().getY();
		
	}

}
