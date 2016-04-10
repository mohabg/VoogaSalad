package gameElements;

public class Bullet extends Attack{
	
	private MobileBehavior mySprite;
	private Sprite myProperties;
	private Integer numBullets;
	private Double rechargeTime;
	
	public Bullet(){
		myProperties = new Sprite();
	}
	public Bullet(MobileBehavior sprite){
		mySprite = sprite;
		myProperties = new Sprite();
		myProperties.setCoord(mySprite.getProperties().getX(), mySprite.getProperties().getY());
	}
	public Bullet(MobileBehavior sprite, Integer bullets, Double recharge){
		mySprite = sprite;
		myProperties = new Sprite();
		myProperties.setCoord(mySprite.getProperties().getX(), mySprite.getProperties().getY());
		numBullets=bullets;
		rechargeTime=recharge;
	}
	
	
	public Integer getNumBullets() {
		return numBullets;
	}
	public void setNumBullets(Integer numBullets) {
		this.numBullets = numBullets;
	}
	public Double getRechargeTime() {
		return rechargeTime;
	}
	public void setRechargeTime(Double rechargeTime) {
		this.rechargeTime = rechargeTime;
	}
	public Sprite getProperties(){
		return this.myProperties;
	}
	public MobileBehavior getSprite(){
		return this.mySprite;
	}
	
	@Override
	public void apply(Sprite spriteProperties) {
		getProperties().setX(getSprite().getProperties().getX());
		getProperties().setY(getSprite().getProperties().getY());
		getMovement().apply(myProperties);
	}
	@Override
	public boolean ready() {
		// TODO Auto-generated method stub
		return false;
	}

}
