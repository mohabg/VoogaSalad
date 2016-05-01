package behaviors;

public class MoveTowards extends Movement{

	public MoveTowards(){
		super();
	}
	
	@Override
	public void move(IActions actions) {
		double thisX = actions.getSpriteProperties().getX();
		double thisY = actions.getSpriteProperties().getY();
		
	}

	@Override
	public Behavior getClone() {
		return new MoveTowards();
	}

}
