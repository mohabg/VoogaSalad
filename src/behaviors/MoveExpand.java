package behaviors;

import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameElements.SpriteProperties;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;

/**
 * Expands a pre-defined sprite by amounts specified by the instance variables expandY and expandX 
 */


public class MoveExpand extends Movement{
	
	private DoubleProperty expandY;
	private DoubleProperty expandX;
	
	public void setExpandY(DoubleProperty expandY) {
		this.expandY = expandY;
	}

	public void setExpandX(DoubleProperty expandX) {
		this.expandX = expandX;
	}

	public MoveExpand() {
		super();
	}
	
	/**
	 * @param sprite The method takes a sprite, which will then be expanded by altering the width and the height of the sprite
	 */
	
	@Override
	public void move(IActions actions) {
		ISpriteProperties properties = actions.getSpriteProperties();
        properties.setWidth((properties.getWidth() + getValue()));
        properties.setHeight((properties.getHeight() + getValue()));
		
	}
	

}

	
