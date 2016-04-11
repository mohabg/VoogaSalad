package gameElements;

public abstract class Defense extends Sprite implements Behavior{
	private Movement movement;
	private double defenseValue;
	
	public Defense(){
		
	}
	public Defense(Movement movement){
		setMovement(movement);
		setDefenseValue(0);
	}
	public Defense(Movement movement, double value){
		setMovement(movement);
		setDefenseValue(value);
	}
	public Movement getMovement() {
		return movement;
	}
	public void setMovement(Movement movement) {
		this.movement = movement;
	}
	public double getDamage() {
		return this.defenseValue;
	}
	public void setDefenseValue(double value) {
		this.defenseValue = value;
	}
	
}
