package Physics;

import gameElements.ISprite;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import level.Level;

public class PhysicsEngine {
	private DoubleProperty drag;
	private DoubleProperty gravity;
	private double currentTime;
	
	public PhysicsEngine(double dragVal, double gravity) {
		this.drag = new SimpleDoubleProperty(dragVal);
		this.gravity = new SimpleDoubleProperty(gravity);
		currentTime = System.currentTimeMillis();
	}
	
	public PhysicsEngine(){
		drag=new SimpleDoubleProperty(1);
	}


	public void updateSprite(ISprite sprite) {
		if(sprite.getSpriteProperties().canMove()){
			updatePos(sprite);
			updateSpriteVelocity(sprite);
			// updateAngle(sprite, degree);
		}
	}
	private void updatePos(ISprite sprite){
		ISpriteProperties properties = sprite.getSpriteProperties();
    	properties.setX(properties.getX() + properties.getXVel());
    	properties.setY(properties.getY() + properties.getYVel());
    }
	
	private void updateSpriteAngle(Sprite sprite, DoubleProperty degree) {
		sprite.getSpriteProperties().setAngle((sprite.getSpriteProperties().getAngle() + degree.getValue()));
	}

	private void updateSpriteVelocity(ISprite sprite) {
		double elapsedTimeMillis = System.currentTimeMillis() -  this.currentTime ;
		double elapsedTime = elapsedTimeMillis / 1000;
		updateXVelocity(sprite);
		updateYVelocity(sprite, elapsedTime);
		this.currentTime = System.currentTimeMillis();
	}

	private void updateXVelocity(ISprite sprite) {
		double newXVel= sprite.getSpriteProperties().getXVel() * getDrag().getValue();
		sprite.getSpriteProperties().setXVel(newXVel);

	}

	private void updateYVelocity(ISprite sprite, double elapsedTime) {
	    double newYVel;
	    //double yDistance = 0.5 * this.gravity.doubleValue() * elapsedTime * elapsedTime;
	   // sprite.getSpriteProperties().setY(sprite.getSpriteProperties().getY() + yDistance);
		if(drag.doubleValue() == 0){
			newYVel = sprite.getSpriteProperties().getYVel() + this.gravity.doubleValue() * elapsedTime;
		}
		else{
			newYVel = sprite.getSpriteProperties().getYVel() * getDrag().getValue();
		}
		//System.out.println("new Y VELOCITY " + newYVel + " y DISTANCE " + yDistance);
			sprite.getSpriteProperties().setYVel(newYVel);
	}

	public DoubleProperty getDrag() {
		return drag;
	}

	public void setDrag(DoubleProperty drag) {
		this.drag = drag;
	}
}
