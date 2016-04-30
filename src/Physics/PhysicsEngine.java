package Physics;

import gameElements.ISprite;
import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import level.Level;

public class PhysicsEngine {
	private DoubleProperty drag;

	
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
		for(ISprite sprite: level.getSpriteMap().getSprites()){
			updateSprite(sprite);
		}
	}

/*	// should this be private
	public void thrustSprite(Sprite sprite, DoubleProperty intensity) {
		sprite.getSpriteProperties().setMyXvelProperty(
				new SimpleDoubleProperty(Math.sin(sprite.getAngle().getValue() * intensity.getValue())));
		sprite.getSpriteProperties().setMyYvelProperty(
				new SimpleDoubleProperty(Math.cos(sprite.getAngle().getValue() * intensity.getValue())));

	}
*/
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
		updateXVelocity(sprite);
		updateYVelocity(sprite);
	}

	private void updateXVelocity(ISprite sprite) {
		//DoubleProperty newLocation = new SimpleDoubleProperty(sprite.getSpriteProperties().getMyXvel().getValue() + sprite.getX().getValue());
		
		double newXVel= sprite.getSpriteProperties().getXVel() * getDrag().getValue();
	//	sprite.setX(newLocation);
		sprite.getSpriteProperties().setXVel(newXVel);

	}

	private void updateYVelocity(ISprite sprite) {
	//	DoubleProperty newLocation = new SimpleDoubleProperty(sprite.getSpriteProperties().getMyYvel().getValue() + sprite.getY().getValue());
		double newYVel = sprite.getSpriteProperties().getYVel() * getDrag().getValue();
	//	sprite.setY(newLocation);
		sprite.getSpriteProperties().setYVel(newYVel);

	}

	public DoubleProperty getDrag() {
		return drag;
	}

	public void setDrag(DoubleProperty drag) {
		this.drag = drag;
	}
}
