package gameElements;

import authoringEnvironment.SpriteProperties;

import java.util.List;

public class Sprite {

    private SpriteProperties mySpriteProperties = new SpriteProperties();
	private Health myHealth;
	private List<Collision> myCollisions;
    private String myRef;


    public Sprite(String ref) {
        mySpriteProperties.setMyX(0);
        mySpriteProperties.setMyY(0);
        mySpriteProperties.setMyAngle(0);
        myRef = ref;
	}

	public Sprite(double x, double y) {
        mySpriteProperties.setMyX(0);
        mySpriteProperties.setMyY(0);
	}

	public Sprite(double x, double y, double angle) {
        mySpriteProperties.setMyX(0);
        mySpriteProperties.setMyY(0);
        mySpriteProperties.setMyAngle(0);

	}

	public boolean isDead() {
		return myHealth.isDead();
	}

	public double getWidth() {
		return mySpriteProperties.getMyWidth();
	}

	public void setWidth(double width) {
		mySpriteProperties.setMyWidth(width);
	}

	public double getHeight() {
		 return mySpriteProperties.getMyHeight();
	}

	public void setHeight(double height) {
		mySpriteProperties.setMyHeight(height);
	}

	public double getX() {
		return mySpriteProperties.getMyX();
	}

	public void setCoord(double x, double y) {
		setX(x);
		setY(y);
	}

	public void setX(double x) {
		mySpriteProperties.getMyX();
	}

	public double getY() {
		return mySpriteProperties.getMyY();
	}

	public void setY(double y) {
		mySpriteProperties.setMyY(y);
	}

	public double getAngle() {
		return mySpriteProperties.getMyAngle();
	}

	public double getDistance(Sprite otherVect) {
		return Math.sqrt(
				(Math.pow(mySpriteProperties.getMyX(), 2) - Math.pow(otherVect.getX(), 2)) + (Math.pow(mySpriteProperties.getMyY(), 2) - Math.pow(otherVect.getY(), 2)));
	}

	public void setAngle(double angle) {
		mySpriteProperties.setMyAngle(angle);
	}

	public Health getHealth() {
		return myHealth;
	}

	public void setHealth(Health myHealth) {
		this.myHealth = myHealth;
	}

	public void addCollision(Collision collision) {
		myCollisions.add(collision);
	}

	public List<Collision> getCollisions() {
		return myCollisions;
	}

    public String getMyRef(){
        return myRef;
    }
}