package gameElements;

public class Bullet extends Attack{
	
	private Sprite mySprite;
	private SpriteProperties myProperties;
	private double numBullets;
    private double rechargeTime;

	public Bullet(double bullets, double recharge){
		myProperties = new SpriteProperties();
        numBullets = bullets;
        rechargeTime = recharge;
	}
	public Bullet(Sprite sprite){
		mySprite = sprite;
		myProperties = new SpriteProperties();
		myProperties.setCoord(mySprite.getProperties().getX(), mySprite.getProperties().getY());
	}
	@Override
	public void apply(Sprite sprite) {
		//Shoot Bullet
		
	}

}
