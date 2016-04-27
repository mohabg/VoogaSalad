package events;

public interface Trigger {
	
	public enum triggerType{
		KEY_PRESS,
		COLLISION,
		GOAL
	}
	
	public boolean isTriggered();

}
