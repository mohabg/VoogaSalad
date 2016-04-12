package gameElements;

public abstract class Defense extends Sprite implements Behavior{
	private Health myHealth;
	private ApplyBehaviorConditions behaviorConditions;
	
	public Defense(Health health){
		myHealth=health;
		behaviorConditions = new ApplyBehaviorConditions();
	}
	
	public boolean readytoDefend(Sprite sprite){
	behaviorConditions.setCurrentDuration(behaviorConditions.getCurrentDuration()-1);
	if (behaviorConditions.getCurrentDuration() <= 0){
	if (Math.random() < behaviorConditions.getShootingProbability()){
	if (behaviorConditions.getMaxDuration() > 0){ 
		return true;}
	}
	}
	return false;
	}
	public Health getHealth() {
		return this.myHealth;
	}
	public void setHealth(Health health) {
		this.myHealth = health;
	}
	
}
