package gameElements;

import javafx.beans.property.DoubleProperty;

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

	@Override
	public void move(Sprite sprite) {
        sprite.getSpriteProperties().setMyWidth((sprite.getWidth().doubleValue() + getValue()));
        sprite.getSpriteProperties().setMyHeight((sprite.getHeight().doubleValue() + getValue()));
		
	}
	

}

	
