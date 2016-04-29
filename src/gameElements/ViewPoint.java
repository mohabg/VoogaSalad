package gameElements;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import level.Level;

public class ViewPoint {

	Rectangle rectangle;

	/* private DoubleProperty myWidth;
	private DoubleProperty myHeight;
	private DoubleProperty originXCoordinate;
	private DoubleProperty originYCoordinate;
	*/
	public ViewPoint(DoubleProperty myWidth, DoubleProperty myHeight, DoubleProperty upperXCoordinate,
			DoubleProperty upperYCoordinate) {
		super();
		rectangle= new Rectangle();
		rectangle.setBounds(upperXCoordinate.intValue(), upperYCoordinate.intValue(), myWidth.intValue(), myHeight.intValue());
		
		/*
		this.myWidth = myWidth;
		this.myHeight = myHeight;
		this.originXCoordinate = originXCoordinate;
		this.originYCoordinate = originYCoordinate;
		*/
	}
	
	public ViewPoint(DoubleProperty myWidth, DoubleProperty myHeight){
	//	DoubleProperty yCoordinate= new SimpleDoubleProperty(sprite.getX().doubleValue()-myWidth.doubleValue()/2);
	//	DoubleProperty xCoordinate= new SimpleDoubleProperty(sprite.getY().doubleValue()+myHeight.doubleValue()/2);
		this(myWidth, myHeight, new SimpleDoubleProperty(0-myWidth.doubleValue()/2), new SimpleDoubleProperty(0+myHeight.doubleValue()/2));
	}

	public double getMyWidth() {
		return rectangle.getWidth();
	}

	public double getMyHeight() {
		 return rectangle.getHeight();
	}

	public double getOriginXCoordinate() {
		return rectangle.getCenterX();
	}

	public double getOriginYCoordinate() {
		return rectangle.getCenterX();
	}

	
	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		
		this.rectangle = rectangle;
	}

	public void updateActiveSprites(Level level){
		List<Integer> activeSpriteList=new ArrayList<Integer>();
		for(Integer integer:level.getSpriteMap().getSpriteIDList()){
			ISprite sprite=level.getSpriteMap().get(integer);
			if( getRectangle().contains(sprite.getSpriteProperties().getX(), sprite.getSpriteProperties().getY())){
				activeSpriteList.add(integer);
			}
		}
		level.getSpriteMap().setActiveSprites(activeSpriteList);
	}
	
	public void moveViewPoint(double xDelta, double yDelta, Level level){
		int newXCoordinate= (int) (getRectangle().getCenterX() + xDelta);
		int newYCoordinate= (int) (getRectangle().getCenterY() + yDelta);
		getRectangle().setLocation(newXCoordinate, newYCoordinate);
		updateSpriteRelativePos(level.getSpriteMap().getSprites(), xDelta, yDelta);
		updateActiveSprites(level);
	}
	
	public void updateSpriteRelativePos(Collection<ISprite> spriteList, double xDelta, double yDelta){
		for(ISprite sprite : spriteList){
			sprite.getSpriteProperties().setX(sprite.getSpriteProperties().getMyRelativeX() - xDelta);
			sprite.getSpriteProperties().setY(sprite.getSpriteProperties().getMyRelativeY() - yDelta);
		}
	}
	
/*	public void updateViewPoint(Level level){
		Sprite userSprite = level.getCurrentSprite();
		Point point=new Point(userSprite.getX().intValue(), userSprite.getY().intValue());
		getRectangle().setLocation(point);
		updateActiveSprites(level);
	}
*/	
	
}
