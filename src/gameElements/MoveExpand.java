package gameElements;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class MoveExpand extends Movement{
	
	private DoubleProperty expandY;
	private DoubleProperty expandX;
	
	public void setExpandY(DoubleProperty expandY) {
		this.expandY = expandY;
	}

	public void setExpandX(DoubleProperty expandX) {
		this.expandX = expandX;
	}

	public MoveExpand(Actor sprite) {
		super(sprite);
	}
	
	/**
	 * @param sprite The method takes a sprite, which will then be expanded by altering the width and the height of the sprite
	 */
	
	@Override
	public void move(Sprite sprite) {
		sprite.setWidth( toDoubleProperty(sprite.getWidth().add(expandX)));
		sprite.setHeight(toDoubleProperty(sprite.getWidth().add(expandY)));
		
	}
	

}

	
