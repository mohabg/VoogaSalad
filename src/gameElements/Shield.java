package gameElements;

import javafx.beans.property.DoubleProperty;

public class Shield extends Defense{
	private Sprite myProperties;
	private DoubleProperty shieldValue;
	private DoubleProperty rechargeTime;


    public Shield(double shield, double recharge){
        shieldValue.set(shield); ;
        rechargeTime.set(recharge);
    }

	public Shield(){
		shieldValue.set(getHealth().getHealth());
	}
	public Sprite getMyProperties() {
		return myProperties;
	}
	public void setMyProperties(Sprite myProperties) {
		this.myProperties = myProperties;
	}
	public DoubleProperty getShieldValue() {
		return shieldValue;
	}
	public void setShieldValue(Double shieldValue) {
		this.shieldValue.set(shieldValue);
	}
	public DoubleProperty getRechargeTime() {
		return rechargeTime;
	}
	public void setRechargeTime(Double rechargeTime) {
		this.rechargeTime.set(rechargeTime);
	}
	
	public void decrementRechargeTime(Double decrement){
		setRechargeTime(getRechargeTime().doubleValue()-decrement);
	}
	
	public void decrementShieldValue(Double decrement){
		setShieldValue(getShieldValue().doubleValue()-decrement);
	}
	
	@Override
	public void apply(Sprite spriteProperties) {
		getMyProperties().setX(spriteProperties.getX().doubleValue());
		getMyProperties().setY(spriteProperties.getY().doubleValue());

	}

	@Override
	public boolean ready() {
		// TODO Auto-generated method stub
		return false;
	}

}
