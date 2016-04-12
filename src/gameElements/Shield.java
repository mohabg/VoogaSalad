package gameElements;

public class Shield extends Defense {
	private Sprite myProperties;
	private Double shieldValue;
	private Double rechargeTime;

	public Shield(Health health, double recharge) {
		super(health);
		shieldValue = getHealth().getHealth();
		rechargeTime = recharge;
	}

	public Sprite getMyProperties() {
		return myProperties;
	}

	public void setMyProperties(Sprite myProperties) {
		this.myProperties = myProperties;
	}

	public Double getShieldValue() {
		return shieldValue;
	}

	public void setShieldValue(Double shieldValue) {
		this.shieldValue = shieldValue;
	}

	public Double getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(Double rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	public void decrementRechargeTime(Double decrement) {
		setRechargeTime(getRechargeTime() - decrement);
	}

	public void decrementShieldValue(Double decrement) {
		setShieldValue(getShieldValue() - decrement);
	}

	@Override
	public void apply(Sprite spriteProperties) {
		getMyProperties().setX(spriteProperties.getX());
		getMyProperties().setY(spriteProperties.getY());

	}

	@Override
	public boolean ready() {
		// TODO Auto-generated method stub
		return false;
	}

}
