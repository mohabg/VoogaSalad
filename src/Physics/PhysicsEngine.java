package Physics;

import gameElements.ISprite;
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

	public void animate(Level level) {
		for(ISprite sprite: level.getSpriteMap().getSprites()){
			updateSprite(sprite);
		}
	}

	// should this be private
	// update method should not update sprite positions
	public void updateSprite(ISprite sprite) {
		updateSpriteVelocity(sprite);
	// updateAngle(sprite, degree);
	}

	// should this be private
	public void updateSpriteAngle(Sprite sprite, DoubleProperty degree) {
		sprite.getSpriteProperties().setAngle((sprite.getSpriteProperties().getAngle() + degree.getValue()));
	}

	private void updateSpriteVelocity(ISprite sprite) {
		double elapsedTime = (this.currentTime - System.currentTimeMillis()) / 1000;
		updateXVelocity(sprite);
		updateYVelocity(sprite, elapsedTime);
	}

	private void updateXVelocity(ISprite sprite) {
		double newXVel= sprite.getSpriteProperties().getXVel() * getDrag().getValue();
		sprite.getSpriteProperties().setXVel(newXVel);

	}

	private void updateYVelocity(ISprite sprite, double elapsedTime) {
	    double newYVel;
		if(drag.doubleValue() == 0){
			newYVel = sprite.getSpriteProperties().getYVel() + this.gravity.doubleValue() * elapsedTime;
		}
		newYVel = sprite.getSpriteProperties().getYVel() * getDrag().getValue();
		sprite.getSpriteProperties().setYVel(newYVel);

	}

	public DoubleProperty getDrag() {
		return drag;
	}

	public void setDrag(DoubleProperty drag) {
		this.drag = drag;
	}
}
