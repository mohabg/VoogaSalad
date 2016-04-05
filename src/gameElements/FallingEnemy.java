package gameElements;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.image.Image;

/**
 * @author Tavo Loaiza
 *	Mortal that can only move downward
 */
public class FallingEnemy extends Observable {

	Sprite mortal;
	public FallingEnemy() {
		mortal = new Mortal();
	}
	public FallingEnemy(double x, double y) {
		mortal = new Mortal();
	}
	public FallingEnemy(double x, double y, double speed) {
		mortal = new Mortal();
	}

	//X componenet has to be
	public void setVector(double x, double y, double xVel, double yVel) {
		if(xVel!=0)
			System.err.println("Warning: xVel must "
					+ "equal 0 for type FalingEnemies");
		mortal.setVector(x, y, 0, yVel);
	}
	public boolean collides(Sprite other) {
		return mortal.collides(other);
	}
	public boolean equals(Object obj) {
		return mortal.equals(obj);
	}
	public Node getNode() {
		return mortal.getNode();
	}
	public void setNode(Node node) {
		mortal.setNode(node);
	}
	public void setImage(Image image) {
		mortal.setImage(image);
	}
	public Vector getVector() {
		return mortal.getVector();
	}
	public double getNextX() {
		return mortal.getNextX();
	}
	public double getNextY() {
		return mortal.getNextY();
	}
	public void setAngle(double angle) {
		mortal.setAngle(angle);
	}
	public void setPosition(double x, double y) {
		mortal.setPosition(x, y);
	}
	public void setVelocity(double x, double y) {
		mortal.setVelocity(x, y);
	}
	public boolean isVisible() {
		return mortal.isVisible();
	}
	public boolean isMoveable() {
		return mortal.isMoveable();
	}
	public boolean isColliable() {
		return mortal.isColliable();
	}
	public boolean intersects(Sprite other) {
		return mortal.intersects(other);
	}
	public void notifyObservers() {
		mortal.notifyObservers();
	}
	public void notifyObservers(Object arg) {
		mortal.notifyObservers(arg);
	}
	public void setVector(Vector vector) {
		mortal.setVector(vector);
	}
	public void setVisible(boolean visible) {
		mortal.setVisible(visible);
	}
	public void setMoveable(boolean moveable) {
		mortal.setMoveable(moveable);
	}
	public void setColliable(boolean colliable) {
		mortal.setColliable(colliable);
	}
	public void propChange() {
		mortal.propChange();
	}
	public void updateNextPos() {
		mortal.updateNextPos();
	}

}
