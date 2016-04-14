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

	@Override
	public void move(Sprite sprite) {
		sprite.setWidth( toDoubleProperty(sprite.getWidth().add(expandX)));
		sprite.setHeight(toDoubleProperty(sprite.getWidth().add(expandY)));
		
	}
	

}

	
