package gameElements;

import authoringEnvironment.RefObject;
import authoringEnvironment.SpriteProperties;
import javafx.beans.property.DoubleProperty;

import java.util.List;
import java.util.ArrayList;

public class Sprite {
    private SpriteProperties mySpriteProperties;
	private Health myHealth;
	private List<Collision> myCollisions;
    private RefObject myRef;


    public void setMyRef(RefObject myRef) {
        this.myRef = myRef;
    }

    public SpriteProperties getMySpriteProperties() {
        return mySpriteProperties;
    }

    public void setMySpriteProperties(SpriteProperties mySpriteProperties) {
        this.mySpriteProperties = mySpriteProperties;
    }

    public Health getMyHealth() {
        return myHealth;
    }

    public void setMyHealth(Health myHealth) {
        this.myHealth = myHealth;
    }

    public List<Collision> getMyCollisions() {
        return myCollisions;
    }

    public void setMyCollisions(List<Collision> myCollisions) {
        this.myCollisions = myCollisions;
    }



    public Sprite() {
        mySpriteProperties = new SpriteProperties();
        myHealth = new Health();
        myCollisions = new ArrayList<Collision>();
    }

    public Sprite(String ref) {
        this();
        myRef = new RefObject(ref);
        mySpriteProperties.setMyX(0);
        mySpriteProperties.setMyY(0);
        mySpriteProperties.setMyAngle(0);
        //myRef.setMyRef(ref);
	}

	public Sprite(double x, double y) {
        this();
        mySpriteProperties.setMyX(0);
        mySpriteProperties.setMyY(0);
	}

	public Sprite(double x, double y, double angle) {
        this();
        mySpriteProperties.setMyX(0);
        mySpriteProperties.setMyY(0);
        mySpriteProperties.setMyAngle(0);
	}

	public boolean isDead() {
		return myHealth.isDead();
	}

	public DoubleProperty getWidth() {
		return mySpriteProperties.getMyWidth();
	}

	public void setWidth(double width) {
		mySpriteProperties.setMyWidth(width);
	}

	public DoubleProperty getHeight() {
		 return mySpriteProperties.getMyHeight();
	}

	public void setHeight(double height) {
		mySpriteProperties.setMyHeight(height);
	}

	public DoubleProperty getX() {
		return mySpriteProperties.getMyX();
	}

	public void setCoord(double x, double y) {
		setX(x);
		setY(y);
	}

	public void setX(double x) {
		mySpriteProperties.getMyX();
	}

	public DoubleProperty getY() {
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
				(Math.pow(mySpriteProperties.getMyX().doubleValue(), 2) - Math.pow(otherVect.getX().doubleValue(), 2)) + (Math.pow(mySpriteProperties.getMyY().doubleValue(), 2) - Math.pow(otherVect.getY().doubleValue(), 2)));
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
        return myRef.getMyRef();
    }
}