package gameElements;

import javafx.beans.property.DoubleProperty;

public class Shield extends Defense{
	private DoubleProperty shieldValue;
	private DoubleProperty rechargeTime;

	public Shield(Health health, DoubleProperty recharge) {
		super(health);
		shieldValue = getHealth().getHealth();
		rechargeTime = recharge;
	}
	
	

	public DoubleProperty getShieldValue() {
		return shieldValue;
	}

	public void setShieldValue(DoubleProperty shieldValue) {
		this.shieldValue=shieldValue;
	}

	public DoubleProperty getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(DoubleProperty rechargeTime) {
		this.rechargeTime=rechargeTime;
	}
	public void decrementRechargeTime(DoubleProperty decrement){
		rechargeTime.subtract(decrement);
	}
	
	public void decrementShieldValue(DoubleProperty decrement){
		shieldValue.subtract(decrement);
	}
	
	@Override
	public void apply(Sprite sprite) {
		this.setCoord(sprite.getX(), sprite.getY());

	}

	@Override
	public boolean ready() {
		// TODO Auto-generated method stub
		return false;
	}

}
