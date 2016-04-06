package gameElements;

import javafx.scene.image.Image;

/**
 * @author Tavo Loaiza
 *	Damage-able and kill-able sprite
 */
public class Mortal extends Sprite {

	final static int HEALTH_DEF = 0;

	private int health;
	private boolean alive;

	public Mortal() {
		super();
		health = HEALTH_DEF;
		setAlive(true);
	}
	public Mortal(Image image, double x, double y) {
		super(image, x, y);
		health = HEALTH_DEF;
		setAlive(true);
	}
	public void setHealth(int health){
		this.health = health;
	}
	public int decHealth(){
		health--;
		if(health<1)
			kill();
		return health;
	}
	public int incHealth(){
		health++;
		propChange();
		return health;
	}
	public void kill(){
		setAlive(false);
		propChange();
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
