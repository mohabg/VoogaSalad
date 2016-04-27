package events;

import behaviors.IActions;

public class Event {
	
	private static String DEFAULT_EVENT_NAME = "unnamedEvent";
	
	private String name;
	private Trigger trigger;
	private Executable executable;
	
	public Event() {
		name = DEFAULT_EVENT_NAME;
	}
	
	public Event(String name, Trigger trigger, Executable executable) {
		this.name = name;
		this.trigger = trigger;
		this.executable = executable;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}
	
	public void setAction(Executable executable) {
		this.executable = executable;
	}
	
	public void doEvent(IActions action) {
		if (trigger.isTriggered())
			executable.execute(action);
	}

}
