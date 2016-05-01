package Physics;

import gameElements.ISprite;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class PhysicsEngine {
	private DoubleProperty drag;
	private DoubleProperty gravity;
	private double currentTime;
	private BooleanProperty enableGravity;
	
	public PhysicsEngine(double dragVal, double gravity) {
		enableGravity = new SimpleBooleanProperty(false);
		this.drag = new SimpleDoubleProperty(dragVal);
		this.gravity = new SimpleDoubleProperty(gravity);
		this.enableGravity = new SimpleBooleanProperty();
		currentTime = System.currentTimeMillis();
	}
	
	public PhysicsEngine(){
		drag=new SimpleDoubleProperty(1);
	}


	public void updateSprite(ISprite sprite) {
		if(sprite.getSpriteProperties().canMove()){
			updatePos(sprite);
			updateSpriteVelocity(sprite);
		}
	}
	private void updatePos(ISprite sprite){
		ISpriteProperties properties = sprite.getSpriteProperties();
    	properties.setX(properties.getX() + properties.getXVel());
    	properties.setY(properties.getY() + properties.getYVel());
    }
	
	private void updateSpriteVelocity(ISprite sprite) {
		double elapsedTimeMillis = System.currentTimeMillis() -  this.currentTime ;
		double elapsedTime = elapsedTimeMillis / 1000;
		updateXVelocity(sprite);
		updateYVelocity(sprite, elapsedTime);
		this.currentTime = System.currentTimeMillis();
	}

	private void updateXVelocity(ISprite sprite) {
		if(!this.isGravityEnabled()){
			double newXVel= sprite.getSpriteProperties().getXVel() * getDrag().getValue();
			sprite.getSpriteProperties().setXVel(newXVel);
		}
	}

	private void updateYVelocity(ISprite sprite, double elapsedTime) {
	    double newYVel;
		if(this.isGravityEnabled()){
			newYVel = sprite.getSpriteProperties().getYVel() + this.gravity.doubleValue() * elapsedTime;
		}
		else{
			newYVel = sprite.getSpriteProperties().getYVel() * getDrag().getValue();
		}
			sprite.getSpriteProperties().setYVel(newYVel);
	}
	
	public DoubleProperty getDrag() {
		return drag;
	}

	public void setDrag(DoubleProperty drag) {
		this.drag = drag;
	}

	public boolean isGravityEnabled() {
		return enableGravity.get();
	}
	public void setEnableGravity(BooleanProperty booleanProperty) {
		this.enableGravity = booleanProperty;
		
	}
}
