package behaviors;

import gameElements.ISpriteProperties;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Expands a pre-defined sprite by amounts specified by the instance variables expandY and expandX 
 */


public class MoveExpand extends Movement{
	
	private DoubleProperty expandY;
	private DoubleProperty expandX;
	
	public MoveExpand() {
		this(0, 0);
	}
	
	public MoveExpand(double expandY, double expandX) {
		super();
		this.expandY = new SimpleDoubleProperty(expandY);
		this.expandX = new SimpleDoubleProperty(expandX);
	}
	
	public void setExpandY(DoubleProperty expandY) {
		this.expandY = expandY;
	}

	public void setExpandX(DoubleProperty expandX) {
		this.expandX = expandX;
	}

	
	
	/**
	 * @param actions The method takes a sprite, which will then be expanded by altering the width and the height of the sprite
	 */
	
	@Override
	public void move(IActions actions) {
		ISpriteProperties properties = actions.getSpriteProperties();
        properties.setWidth((properties.getWidth() + getValue()));
        properties.setHeight((properties.getHeight() + getValue()));
		
	}
	

}

	
