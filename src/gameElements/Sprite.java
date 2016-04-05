package gameElements;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polygon;

/**
 * @author Tavo Loaiza
 *	Basic game element
 */
public class Sprite extends Observable {

	private Node node;
	private Vector vector;
	private boolean visible;
	private boolean movable;
	private boolean colliable;

	public Sprite() {
		//Default init values
		node = new Polygon();
		vector = new Vector();
		visible = false;
		movable = false;
		colliable = false;
	}
	public Sprite(Image image, double x, double y){
		node = new ImageView(image);
		this.visible = true;
		vector = new Vector(x,y);
		movable = true;
		colliable = true;
	}

	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}

	public void setImage(Image image) {
		node = new ImageView(image);
		propChange();
	}

	public Vector getVector() {
		return vector;
	}

	public double getNextX(){
		if(!movable)
			return vector.getX();
		return vector.getxVel();
	}

	public double getNextY(){
		if(!movable)
			return vector.getY();
		return vector.getyVel();
	}

	public void setAngle(double angle){
		vector.setAngle(angle);
		if(node!=null)
			node.setRotate(angle);
		propChange();
	}

	public void setPosition(double x, double y){
		vector.setCoord(x, y);
		propChange();
	}

	public void setVelocity(double x, double y){
		vector.setVelocity(x, y);
		propChange();
	}

	public void setVector(Vector vector) {
		this.vector = vector;
		propChange();
	}
	public void setVector(double x, double y, double xVel, double yVel) {
		vector.setCoord(x, y);
		vector.setVelocity(xVel, yVel);
		propChange();
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
		node.setVisible(visible);
		propChange();
	}

	public boolean isMoveable() {
		return movable;
	}

	public void setMoveable(boolean moveable) {
		this.movable = moveable;
		propChange();
	}

	public boolean isColliable() {
		return colliable;
	}

	public void setColliable(boolean colliable) {
		this.colliable = colliable;
		propChange();
	}

	public void updateNextPos(){
		if(movable)
			vector.setCoord(vector.getxVel(), vector.getyVel());
		propChange();
	}

	public boolean intersects(Sprite other){
		if(node!=null && other.node!=null)
			return node.intersects(other.node.getBoundsInParent());
		return false;
	}

	public boolean collides(Sprite other){
		if(!colliable)
			return false;
		return intersects(other);
	}

	public void propChange(){
		 setChanged();
	     notifyObservers();
	}

}
