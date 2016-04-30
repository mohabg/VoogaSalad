package behaviors;

import authoringEnvironment.Settings;
import gameElements.ISpriteProperties;

public class SpawnMovement extends Movement {
	private double x;
	private double y;
	
	public SpawnMovement(){
		super();
	}
	public SpawnMovement(double value){
		super(value);
		x = Math.random() * Settings.getScreenWidth();
		y = 0;
	}

	@Override
	public void move(IActions actions) {
		// TODO Auto-generated method stub
		ISpriteProperties myProperties = actions.getSpriteProperties();
		double thisX = actions.getSpriteProperties().getX();
		double thisY = actions.getSpriteProperties().getY();
		myProperties.setX(x + getValue()); //add angle stuff
		myProperties.setY(y + getValue()); //add angle stuff
		
	}

}
