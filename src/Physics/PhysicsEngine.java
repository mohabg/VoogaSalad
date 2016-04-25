package Physics;

import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import level.Level;

public class PhysicsEngine {
	private DoubleProperty drag;

	// 
	public PhysicsEngine(DoubleProperty dragVal) {
		drag = dragVal;
	}
	
	public PhysicsEngine(Double dragVal){
		this(new SimpleDoubleProperty(dragVal));
	}
	public PhysicsEngine(){
		drag=new SimpleDoubleProperty(1);
	}

	public void animate(Level level) {

	}

	// should this be private
	public void thrustSprite(Sprite sprite, DoubleProperty intensity) {
		sprite.getSpriteProperties().setMyXvelProperty(
				new SimpleDoubleProperty(Math.sin(sprite.getAngle().getValue() * intensity.getValue())));
		sprite.getSpriteProperties().setMyYvelProperty(
				new SimpleDoubleProperty(Math.cos(sprite.getAngle().getValue() * intensity.getValue())));

	}

	// should this be private
	// update method should not update sprite positions
	public void updateSprite(Sprite sprite, DoubleProperty degree) {
		sprite.getSpriteProperties().updatePos();
		updateSpriteVelocity(sprite);
	// updateAngle(sprite, degree);

	}

	// should this be private
	public void updateAngle(Sprite sprite, DoubleProperty degree) {
		sprite.getSpriteProperties().setMyAngle((sprite.getAngle().doubleValue() + degree.getValue()));
	}

	private void updateSpriteVelocity(Sprite sprite) {
		updateXVelocity(sprite);
		updateYVelocity(sprite);
	}

	private void updateXVelocity(Sprite sprite) {
		//DoubleProperty newLocation = new SimpleDoubleProperty(sprite.getSpriteProperties().getMyXvel().getValue() + sprite.getX().getValue());
		DoubleProperty newXVelocity = new SimpleDoubleProperty(
		sprite.getSpriteProperties().getMyXvel().getValue() * getDrag().getValue());
	//	sprite.setX(newLocation);
		sprite.getSpriteProperties().setMyXvelProperty(newXVelocity);

	}

	private void updateYVelocity(Sprite sprite) {
	//	DoubleProperty newLocation = new SimpleDoubleProperty(sprite.getSpriteProperties().getMyYvel().getValue() + sprite.getY().getValue());
		DoubleProperty newYVelocity = new SimpleDoubleProperty();
	//	sprite.setY(newLocation);
		sprite.getSpriteProperties().setMyYvelProperty(newYVelocity);

	}

	public DoubleProperty getDrag() {
		return drag;
	}

	public void setDrag(DoubleProperty drag) {
		this.drag = drag;
	}
}