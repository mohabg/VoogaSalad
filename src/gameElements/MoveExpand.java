package gameElements;

public class MoveExpand extends Movement{
	
	private double expandY;
	private double expandX;
	
	public void setExpandY(double expandY) {
		this.expandY = expandY;
	}

	public void setExpandX(double expandX) {
		this.expandX = expandX;
	}

	public MoveExpand(Actor sprite) {
		super(sprite);
	}

	@Override
	public void apply(Sprite sprite) {
		sprite.setWidth(expandX+sprite.getWidth());
		sprite.setHeight(expandY+sprite.getWidth());
		
	}

	@Override
	public boolean ready() {
		// TODO Auto-generated method stub
		return false;
	}

}

	
